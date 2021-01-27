package br.com.alura.alurapic.service;

import java.time.LocalDateTime;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import javax.validation.Validator;

import br.com.alura.alurapic.dao.UserDao;
import br.com.alura.alurapic.dto.UserCredentialsDto;
import br.com.alura.alurapic.dto.UserLoggedDto;
import br.com.alura.alurapic.dto.UserSignupDto;
import br.com.alura.alurapic.model.User;
import br.com.alura.alurapic.parser.UserParser;

@RequestScoped
public class UserService {
	
	@Inject
	UserDao dao;
	
	@Inject
	Validator validator;
	
	public UserLoggedDto login(UserCredentialsDto credentials) {
		User userDb = dao.findUserCredentials(credentials);
		return UserParser.get().userLoggedDto(userDb);
	}

	public Boolean isUserNameTaken(String userName) {
		 try{
			 dao.findUserName(userName);
			 return true;
		 } catch(NoResultException e) {
			 return false;
		 }
	}
	
	@Transactional(rollbackOn = Exception.class)
	public void signup(UserSignupDto userDto) {
		
		User user = UserParser.get().user(userDto);
		user.setJoinDate(LocalDateTime.now());
		dao.gravar(user);
	}
	
}
