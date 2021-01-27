package br.com.alura.alurapic.dao;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import br.com.alura.alurapic.model.Photo;


@RequestScoped
public class PhotoDao {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Transactional
	public List<Photo> listPhotos(String userName, Integer page) {
		
//		String consulta = 
//				"SELECT p.* " 
//				+ "FROM photo p "
//				+ "JOIN user u ON p.user_id = u.id "
//				+ "WHERE name = :userName ORDER BY p.postDate DESC ";
		
		
		Integer maxRows = 6;
		Integer from = 0;
		
		if(page != null) {
			from = (page - 1) * maxRows;
//			String limitQuery = String.format("LIMIT %s, %s", from, maxRows);
//			consulta = consulta + limitQuery;
		}
		
		String consulta = 
				"SELECT p FROM Photo p "
				+ "JOIN FETCH p.user "
				+ "WHERE p.user.name = :userName "
				+ "ORDER BY p.postDate DESC";
		
		List<Photo> lista = manager
				.createQuery(consulta, Photo.class)
				.setParameter("userName", userName)
				.setFirstResult(from)
				.setMaxResults(maxRows)
				.getResultList();
		return lista;
	}
	
	@Transactional(rollbackOn = Exception.class)
	public void gravar(Photo photo) {
		manager.persist(photo);
	}
	
	@Transactional
	public Photo findById(Integer id) {
		return manager
				.createQuery("SELECT p FROM Photo p JOIN FETCH p.user WHERE p.id = :id", Photo.class)
				.setParameter("id", id)
				.getSingleResult();
	}
	
	@Transactional
	public Boolean removePhoto(Integer id) {
		return Photo.deleteById(id);
	}
	
	@Transactional
	public void likeById(Integer photoId, Integer userId) {
		
		String query = "INSERT INTO likes (likeDate, photo_id, user_id) VALUES (current_timestamp(), :id, :userId)";
		Photo photo = Photo.findById(photoId);
		manager
			.createNativeQuery(query)
			.setParameter("id", photoId)
			.setParameter("userId", userId)
			.executeUpdate();
		
		photo.setLikes(photo.getLikes() + 1);
	
		
	}
	
}
