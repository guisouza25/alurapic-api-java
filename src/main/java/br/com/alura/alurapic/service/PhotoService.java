package br.com.alura.alurapic.service;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.ForbiddenException;

import br.com.alura.alurapic.dao.PhotoDao;
import br.com.alura.alurapic.dto.PhotoDto;
import br.com.alura.alurapic.dto.PhotoUploadDto;
import br.com.alura.alurapic.helpers.ImageHelper;
import br.com.alura.alurapic.helpers.TokenHelper;
import br.com.alura.alurapic.model.Photo;
import br.com.alura.alurapic.model.User;
import br.com.alura.alurapic.parser.PhotoParser;
import javaxt.io.Image;

@RequestScoped
public class PhotoService {
	
	@Inject
	PhotoDao dao;
	@Inject
	Validator validator;
	@Inject
	ImageHelper imageHelper;
	
	
	public List<PhotoDto> listar(String userName, Integer page) {
		return dao
			.listPhotos(userName, page).stream()
			.map(PhotoParser.get()::photoDto)
			.collect(Collectors.toList());
	}
	
	public PhotoDto findById(Integer id) {
		return PhotoParser.get().photoDto(dao.findById(id));		
	}
	
	@Transactional(rollbackOn = Exception.class)
	public List<String> gravar(PhotoUploadDto photoDto, User user)  {
		
		Set<ConstraintViolation<PhotoUploadDto>> errors = validator.validate( photoDto );
		List<String> listaErros = errors.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
		
		try {
			
			Image imageResized = imageHelper.rotateAndResizeImage(photoDto.getPhoto());
			//BufferedImageWrapper image = imageHelper.getImageFromInputStream(photoDto.getPhoto());
			String url = imageHelper.encodeImageToBase64(imageResized, photoDto.getPhotoType());
			
			
			Photo photo = PhotoParser.get().photo(photoDto, user, url);
			
			if(listaErros.isEmpty()) {
				dao.gravar(photo);
			}
			
		} catch (IllegalArgumentException | IOException | NullPointerException e) {
			e.printStackTrace();
			listaErros.add("Upload a valid image file");
		}
		return listaErros;
	}

	public void removePhoto(Integer photoId, String token) {
		
		User user = TokenHelper.decodeJWT(token).get("user", User.class);
		Photo photo = dao.findById(photoId);
		
		if(photo.getUser().getId() == user.getId()) {
			dao.removePhoto(photoId);
		} else {
			throw new ForbiddenException();
		}
	}

	public Boolean likeById(Integer photoId, Integer userId) {
		
		try {
			dao.likeById(photoId, userId);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	
		
	

		

		
		
				 
		
		

	
	
	
	

	


	
	
	
}
