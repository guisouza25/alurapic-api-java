package br.com.alura.alurapic.filters;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import br.com.alura.alurapic.helpers.TokenHelper;
import io.jsonwebtoken.Jwts;

@Provider
@Authorize
@Priority(Priorities.AUTHENTICATION)
public class AuthorizeFilter implements ContainerRequestFilter {
	

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
    	
        String authorizationHeader = 
        		requestContext.getHeaderString("Authorization");
        try {
            String token = authorizationHeader
            		.substring("Bearer".length()).trim();
            
            Jwts.parserBuilder()
            	.setSigningKey(TokenHelper.getKeyPair().getPrivate())
            	.build()
            	.parseClaimsJws(token);
            
        } catch (Exception e) {
            requestContext
            	.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }

    }
    
    
}