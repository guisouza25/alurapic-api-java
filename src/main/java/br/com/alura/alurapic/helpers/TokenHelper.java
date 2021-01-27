package br.com.alura.alurapic.helpers;

import java.security.KeyPair;
import java.util.Date;

import javax.enterprise.context.RequestScoped;

import br.com.alura.alurapic.dto.UserLoggedDto;
import br.com.alura.alurapic.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.jackson.io.JacksonDeserializer;
import io.jsonwebtoken.lang.Maps;
import io.jsonwebtoken.security.Keys;

@RequestScoped
public class TokenHelper {


	private static final KeyPair KEY_PAIR = Keys.keyPairFor(SignatureAlgorithm.RS256);


	public String generateToken(UserLoggedDto userDto) {
		String token = Jwts.builder().setIssuer("localhost://3000")
						.claim("user", userDto)
						.setIssuedAt(new Date())
						.setExpiration(new Date(new Date().getTime() + 1800000))
						.signWith(KEY_PAIR.getPrivate()).compact();
		return token;
	}

	public static KeyPair getKeyPair() {
		return KEY_PAIR;
	}

	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Claims decodeJWT(String token) {
		return Jwts.parserBuilder()
				.deserializeJsonWith(new JacksonDeserializer(Maps.of("user", User.class).build())) 
				.setSigningKey(getKeyPair().getPrivate()).build()
				.parseClaimsJws(token.substring("Bearer".length()).trim()).getBody();
	}

}
