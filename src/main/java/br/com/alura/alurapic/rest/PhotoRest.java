package br.com.alura.alurapic.rest;


import java.io.IOException;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import br.com.alura.alurapic.dto.PhotoDto;
import br.com.alura.alurapic.dto.PhotoUploadDto;
import br.com.alura.alurapic.filters.Authorize;
import br.com.alura.alurapic.helpers.TokenHelper;
import br.com.alura.alurapic.model.User;
import br.com.alura.alurapic.service.PhotoService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;



@Path("/")
@RequestScoped
public class PhotoRest {
	
	@Inject
	PhotoService service;
	
	@GET
	@Path("{userName}/photos")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listPhotos(
			@PathParam("userName") String userName,
			@QueryParam("page") Integer page){
		
		
		//System.out.println("token send by de application\ntoken: " + token );
		
		return Response
					.status(Response.Status.OK)
					.entity(service.listar(userName, page)).build();
	}
	
	@GET
	@Path("photos/{photoId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findById(
			@PathParam("photoId") Integer id){
		
		try {
			PhotoDto photoDto = service.findById(id);	
			return Response
					.status(Response.Status.OK)
					.entity(photoDto).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response
					.status(Status.NOT_FOUND)
					.entity("Can`t find photo").build();
		}
	}
	
	@POST
	@Authorize
	@Path("photos/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadPhoto(
			@MultipartForm PhotoUploadDto photoDto,
			@HeaderParam("Authorization") String token) throws IOException {
		
		try {
			User user = TokenHelper.decodeJWT(token).get("user", User.class);
			List<String> listaErros = service.gravar(photoDto, user);
			
			if(listaErros.isEmpty()) {	
				return Response
							.status(Response.Status.CREATED)
							.build();	
			} else {
				return Response
							.status(Status.NOT_ACCEPTABLE)
							.entity(listaErros).build();
			}
			
		} catch (UnsupportedJwtException | MalformedJwtException | SignatureException | ExpiredJwtException | IllegalArgumentException e) {
			e.printStackTrace();
			return Response
					.status(Status.UNAUTHORIZED)
					.entity("A problem with user authentication occurred. Try login again").build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response
					.status(Status.INTERNAL_SERVER_ERROR)
					.entity("An error ocurred").build();
		}
	}
	
	@DELETE
	@Authorize
	@Path("/photos/{photoId}") 
		public Response removePhoto(
				@PathParam("photoId") Integer photoId,
				@HeaderParam("Authorization") String token) {
				
			try {
				service.removePhoto(photoId, token);
				return Response
						.status(Response.Status.OK)
						.build();
				
			} catch (NoResultException e) {
				return Response
						.status(Status.NOT_FOUND)
						.entity("Photo does not exist").build();
				
			} catch (ForbiddenException e) {
				return Response
						.status(Status.FORBIDDEN)
						.entity("User can not delete this photo").build();
			}
	}
	
	@POST
	@Authorize
	@Path("/photos/{photoId}/like") 
		public Response like(
				@PathParam("photoId") Integer id,
				@HeaderParam("Authorization") String token) {
			
			User user = TokenHelper.decodeJWT(token).get("user", User.class);
			
			if(service.likeById(id, user.getId())) {
				return Response
						.status(Status.CREATED)
						.build();
			} else {
				return Response
						.status(Status.NOT_MODIFIED)
						.build();
			}
			
		}
	
	 
}





