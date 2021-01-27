package br.com.alura.alurapic.rest;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotAcceptableException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.alura.alurapic.dto.UserCredentialsDto;
import br.com.alura.alurapic.dto.UserLoggedDto;
import br.com.alura.alurapic.dto.UserSignupDto;
import br.com.alura.alurapic.helpers.TokenHelper;
import br.com.alura.alurapic.service.UserService;

@Path("/")
@RequestScoped
public class UserRest {
	
	@Inject
	TokenHelper tokenHelper;
	
	@Inject
	UserService service;
	
	@Inject
	Validator validator;
	
	@POST
	@Path("user/login")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response login(UserCredentialsDto credentials){
		
		
		try {
			UserLoggedDto userDto = service.login(credentials);
			
			String token = tokenHelper.generateToken(userDto);		
			
			return Response.status(Response.Status.OK)
					.header("Authorization", "Bearer " + token)
					.build();
			
		} catch (NoResultException e) {
			return Response.status(401).entity("Invalid username or password").build();
		}
	}
	
	@GET
	@Path("user/exists/{userName}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Boolean isUserNameTaken(@PathParam("userName") String userName){
		
		Boolean isTaken = 
				service.isUserNameTaken(userName);
		System.out.println("Buscando user name...");
		return isTaken ? true : false;
	}
	
	@POST
	@Path("user/signup")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response signup(UserSignupDto userDto){
		
		Set<ConstraintViolation<UserSignupDto>> errors = validator.validate( userDto );
		
		if(errors.isEmpty()) {
			service.signup(userDto);
		} else {
			List<String> listaErros = errors.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
			
			listaErros.forEach(i -> {
				System.out.println(i);
			});
			throw new NotAcceptableException(
				Response.status(Response.Status.NOT_ACCEPTABLE).entity(listaErros).build()
			);
		}
		return Response.status(Status.CREATED).build();
	}
	
	
}





