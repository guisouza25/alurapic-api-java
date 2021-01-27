package br.com.alura.alurapic.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
@Table(name = "photo")
public class Photo extends PanacheEntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	private LocalDateTime postDate;

	@Lob
	@Column(nullable = false)
	private String url;
	
	@Column(length = 250)
	private String description;
	
	@Column(nullable = false)
	private Boolean allowComments;
	
	@Column(nullable = false)
	private Integer comments;
	
	@Column(nullable = false)
	private Integer likes;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@OneToMany(mappedBy = "photo")
	@OnDelete(action = OnDeleteAction.CASCADE)
	//@LazyCollection(LazyCollectionOption.FALSE)
	private List<Comment> commentsList;
	
	@OneToMany(mappedBy = "photo")
	@OnDelete(action = OnDeleteAction.CASCADE)
	//@LazyCollection(LazyCollectionOption.FALSE)
	private List<Like> likeList;

	public Photo() {
	}
	
	public Photo(Integer id) {
		this.id = id;
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

	public Integer getComments() {
		return comments;
	}

	public void setComments(Integer comments) {
		this.comments = comments;
	}

	public Integer getLikes() {
		return likes;
	}

	public void setLikes(Integer likes) {
		this.likes = likes;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

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
	
	
	



}
