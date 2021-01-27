package br.com.alura.alurapic.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
@Table(name = "user")
public class User extends PanacheEntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(unique = true, nullable = false, length = 30)
	private String name;
	
	@Column(nullable = false , length = 14)
	private String password;
	
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false, length = 40)
	private String fullName;
	
	private LocalDateTime joinDate;
	
	@OneToMany(mappedBy = "user")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Photo> photos = new ArrayList<Photo>();
	
	@OneToMany(mappedBy = "user")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Comment> commentsList;
	
	@OneToMany(mappedBy = "user")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Like> likeList;
	
	
	
	public List<Comment> getCommentsList() {
		return commentsList;
	}

	public void setCommentsList(List<Comment> commentsList) {
		this.commentsList = commentsList;
	}

	public List<Like> getLikeList() {
		return likeList;
	}

	public void setLikeList(List<Like> likeList) {
		this.likeList = likeList;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public LocalDateTime getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(LocalDateTime joinDate) {
		this.joinDate = joinDate;
	}

	public List<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}

	@Override
	public String toString() {
		return String.format(
				  "USER ID: %d\n"
				+ "USER NAME: %s\n"
				+ "USER PASSWORD: %s\n"
				+ "USER EMAIL: %s\n"
				+ "USER FULLNAME: %s\n"
				+ "USER JOINDATE: %tF\n",
				this.id, this.name, this.password, this.email, this.fullName, this.joinDate);
	}
}
