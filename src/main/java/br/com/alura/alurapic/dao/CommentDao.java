package br.com.alura.alurapic.dao;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.Session;

import br.com.alura.alurapic.model.Comment;
import br.com.alura.alurapic.model.Photo;
import br.com.alura.alurapic.model.User;

@RequestScoped
public class CommentDao {
	
	@PersistenceContext
	private EntityManager manager;
	
	@PersistenceContext
	private Session session;
	
	public List<Photo> list() {
		return manager.createQuery("SELECT p FROM Photo p", Photo.class).getResultList();
	}
	
	@Transactional
	public List<Comment> listCommentsFromPhoto(Integer id) {
		
		//return Comment.list("photo", Sort.by("commentDate").descending(), new Photo(id));
		
		String query =
				"SELECT c FROM Comment c "
				+ "WHERE c.photo.id = :id "
				+ "ORDER BY c.commentDate DESC";
		
		return manager
					.createQuery(query, Comment.class)
					.setParameter("id", id)
					.getResultList();

	}
	
	@Transactional(rollbackOn = Exception.class)
	public void addCommentAndUpdatePhoto(Comment comment, Photo photo) {
		manager.persist(comment);
		Integer comments = (int) (long) Comment.count("photo", new Photo(photo.getId()));
		photo.setComments(comments);
		
	}
	
}
	
	