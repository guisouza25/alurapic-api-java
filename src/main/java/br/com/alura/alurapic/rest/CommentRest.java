package br.com.alura.alurapic.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.ws.rs.Consumes;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.alura.alurapic.dao.CommentDao;
import br.com.alura.alurapic.dto.CommentDto;
import br.com.alura.alurapic.filters.Authorize;
import br.com.alura.alurapic.service.CommentService;

@Path("/")
@RequestScoped
public class CommentRest {
	
	@Inject
	CommentService service;
	
	@Inject
	CommentDao dao;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("photos/comments")
	public void listar() {
		dao.list();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("photos/{photoId}/comments")
	public Response listCommentsFromPhoto(
			@PathParam("photoId") Integer id) {
		
		List<CommentDto> lista = service.listCommentsFromPhoto(id);
		
		if(lista.isEmpty()) {
			return Response
					.status(Status.NO_CONTENT)
					.entity("Comments not found for this photo").build();
		} else {
			return Response
					.status(Status.OK)
					.entity(lista).build();
		}
	}
	
	@POST
	@Authorize
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("photos/{photoId}/comments")
	public Response addComment(
			CommentDto commentDto,
			@PathParam("photoId") Integer photoId,
			@HeaderParam("Authorization") String token) {
		
		try {
			service.addComment(commentDto, photoId, token);
			return Response
						.status(Response.Status.CREATED)
						.build();
			
		} catch (NoResultException e) {
			return Response
						.status(Status.NO_CONTENT)
						.entity("Photo Not Found").build();
		}
		catch (ForbiddenException e) {
			return Response
						.status(Status.FORBIDDEN)
						.entity("Comments not permitted").build();
		}
		catch (Exception e) {
			e.printStackTrace();
			return Response
					.status(Status.UNAUTHORIZED)
					.entity("Unauthorized").build();
		}
	}
			
	
	
}


