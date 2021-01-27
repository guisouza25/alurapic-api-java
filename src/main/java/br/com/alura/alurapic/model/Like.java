package br.com.alura.alurapic.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(
		name = "likes",
		uniqueConstraints = { @UniqueConstraint(columnNames = { "photo_id", "user_id" }) }
		)
public class Like {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	LocalDateTime likeDate;
	
	@ManyToOne
	@JoinColumn(name = "photo_id", nullable = false)
	Photo photo;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	User user;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getLikeDate() {
		return likeDate;
	}

	public void setLikeDate(LocalDateTime likeDate) {
		this.likeDate = likeDate;
	}

	public Photo getPhoto() {
		return photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}
