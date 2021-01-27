package br.com.alura.alurapic.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.json.bind.annotation.JsonbDateFormat;

import br.com.alura.alurapic.model.User;

public class PhotoDto  implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@JsonbDateFormat("dd/MM/yyyy HH:mm")
	private LocalDateTime postDate;	
	
	
	private String url;
	private String description;
	private Boolean allowComments;
	private Integer comments;
	private Integer likes;
	private Integer userId;
	private User user;
	
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Integer getComments() {
		return comments;
	}
	public void setComments(Integer comments) {
		this.comments = comments;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public LocalDateTime getPostDate() {
		return postDate;
	}
	public void setPostDate(LocalDateTime postDate) {
		this.postDate = postDate;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Boolean getAllowComments() {
		return allowComments;
	}
	public void setAllowComments(Boolean allowComments) {
		this.allowComments = allowComments;
	}
	public Integer getLikes() {
		return likes;
	}
	public void setLikes(Integer likes) {
		this.likes = likes;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	
	
	
	
}
