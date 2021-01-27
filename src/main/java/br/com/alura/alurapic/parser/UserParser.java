package br.com.alura.alurapic.parser;

import br.com.alura.alurapic.dto.UserLoggedDto;
import br.com.alura.alurapic.dto.UserSignupDto;
import br.com.alura.alurapic.model.User;

public class UserParser {
	
	public static UserParser get() {
		return new UserParser();
	}
	
	public UserLoggedDto userLoggedDto(User user) {
		UserLoggedDto dto = new UserLoggedDto();
		dto.setId(user.getId());
		dto.setName(user.getName());
		dto.setEmail(user.getEmail());
		
		return dto;
	}
	
	public User user(UserSignupDto dto) {
		User user = new User();
		user.setName(dto.getName());
		user.setFullName(dto.getFullName());
		user.setEmail(dto.getEmail());
		user.setPassword(dto.getPassword());
		
		return user;
	}
}
