package br.com.alura.alurapic.parser;

import java.time.LocalDateTime;

import br.com.alura.alurapic.dto.PhotoDto;
import br.com.alura.alurapic.dto.PhotoUploadDto;
import br.com.alura.alurapic.model.Photo;
import br.com.alura.alurapic.model.User;

public class PhotoParser {
	
	public static PhotoParser get() {
		return new PhotoParser();
	}
	
	public PhotoDto photoDto(Photo photo) {
		PhotoDto dto = new PhotoDto();
		dto.setId(photo.getId());
		dto.setPostDate(photo.getPostDate());
		dto.setUrl(photo.getUrl());
		dto.setDescription(photo.getDescription());
		dto.setAllowComments(photo.getAllowComments());
		dto.setComments(photo.getComments());
		dto.setLikes(photo.getLikes());
		dto.setUserId(photo.getUser().getId());
		return dto;
	}
	
	
	/**
	 * Gets an entire new entity Photo from data input received by the application,
	 * setting the user who posted the photo from the token that has been sent.
	 * @param photoDto
	 * @param token
	 * @param image 
	 * @return Photo entity
	 * @throws Exception 
	 */
	public Photo photo(PhotoUploadDto photoDto, User user, String url) {
		Photo photo = new Photo();
		photo.setPostDate(LocalDateTime.now());
		photo.setUrl(url);
		photo.setDescription(photoDto.getDescription());
		photo.setAllowComments(Boolean.valueOf(photoDto.getAllowComments()));
		photo.setComments(Integer.valueOf(0));
		photo.setLikes(Integer.valueOf(0));
		photo.setUser(user);
		return photo;
	}
	
	

	
	
	
}

