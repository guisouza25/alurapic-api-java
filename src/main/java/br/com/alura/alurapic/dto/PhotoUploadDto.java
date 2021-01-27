package br.com.alura.alurapic.dto;

import java.io.InputStream;

import javax.validation.constraints.NotNull;
import javax.ws.rs.FormParam;

import org.hibernate.validator.constraints.Length;

public class PhotoUploadDto {
	
	@FormParam("photo")
	private InputStream photo;
	
	@FormParam("photoType")
	private String photoType;
	
	@FormParam("description")
	@Length(max = 250, message = "Photo description max lenght is 250")
	private String description;
	
	@FormParam("allowComments")
	@NotNull(message = "Field allow comments is required")
	private String allowComments;

	
	
	public InputStream getPhoto() {
		return photo;
	}

	public void setPhoto(InputStream photo) {
		this.photo = photo;
	}

	public String getPhotoType() {
		return photoType;
	}

	public void setPhotoType(String photoType) {
		this.photoType = photoType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAllowComments() {
		return allowComments;
	}

	public void setAllowComments(String allowComments) {
		this.allowComments = allowComments;
	}
	
	
	
	
	
	
	
	
	
}
