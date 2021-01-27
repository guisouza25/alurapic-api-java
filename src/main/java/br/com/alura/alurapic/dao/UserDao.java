package br.com.alura.alurapic.dao;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import br.com.alura.alurapic.dto.UserCredentialsDto;
import br.com.alura.alurapic.model.User;

@RequestScoped
public class UserDao {
	
	@PersistenceContext
	private EntityManager manager;
	
	public User findUserCredentials(UserCredentialsDto credentials){
		String name = credentials.getName();
		String password = credentials.getPassword();
		
		return manager
			.createQuery("SELECT u FROM User u WHERE u.name = :name and u.password = :password", User.class)
			.setParameter("name", name)
			.setParameter("password", password)
			.getSingleResult();
	}

	public void findUserName(String userName) {
		manager
			.createQuery("SELECT u FROM User u WHERE name = :userName")
			.setParameter("userName", userName)
			.getSingleResult();
			
	}
	
	@Transactional
	public void gravar(User user) {
		manager.persist(user);
		
	}
	
}
	
	