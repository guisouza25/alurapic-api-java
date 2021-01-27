package br.com.alura.alurapic.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
@Table(name = "comment")
public class Comment extends PanacheEntityBase {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	@Column(nullable = false)
	LocalDateTime commentDate;
	
	String comment;
	
	@ManyToOne
	@JoinColumn(name = "photo_id", nullable = false)
	Photo photo;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	User user;
	
	//CONSTUCTORS
	
	public Comment() {
	}
	
	public Comment(LocalDateTime commentDate, String comment, Photo photo, User user) {
		this.commentDate = commentDate;
		this.comment = comment;
		this.photo = photo;
		this.user = user;
	}

	//GETTERS AND SETTERS

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(LocalDateTime commentDate) {
		this.commentDate = commentDate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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
	
	@Override
	public String toString() {
		return String.format(
				  "COMMENT ID: %d\n"
				+ "COMMENT DATE: %tF\n"
				+ "COMMENT COMMENT: %s\n",
				this.id, this.commentDate, this.comment);
	}
	
	
}
