package br.com.alura.alurapic.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.ForbiddenException;

import br.com.alura.alurapic.dao.CommentDao;
import br.com.alura.alurapic.dao.PhotoDao;
import br.com.alura.alurapic.dto.CommentDto;
import br.com.alura.alurapic.helpers.TokenHelper;
import br.com.alura.alurapic.model.Comment;
import br.com.alura.alurapic.model.Photo;
import br.com.alura.alurapic.model.User;
import br.com.alura.alurapic.parser.CommentParser;

@RequestScoped
public class CommentService {
	
	@Inject
	CommentDao dao;
	
	@Inject
	PhotoDao photoDao;
	
	@Inject
	CommentDao commentDao;
	
	
	public List<CommentDto> listCommentsFromPhoto(Integer id) {
		return dao
				.listCommentsFromPhoto(id).stream()
				.map(CommentParser.get()::commentDto)
				.collect(Collectors.toList());
	}
	
	@Transactional(rollbackOn = Exception.class)
	public void addComment(CommentDto commentDto, Integer photoId, String token) {
		
		Photo photo = photoDao.findById(photoId);
		User user = TokenHelper.decodeJWT(token).get("user", User.class);
		
		if(photo.getAllowComments()) {
			Comment comment = CommentParser.get().comment(commentDto, photoId, user);
			commentDao.addCommentAndUpdatePhoto(comment, photo);
		} else {
			throw new ForbiddenException();
		}
	}
}
