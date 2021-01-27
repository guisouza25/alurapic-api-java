package br.com.alura.alurapic.parser;

import java.time.LocalDateTime;

import br.com.alura.alurapic.dto.CommentDto;
import br.com.alura.alurapic.model.Comment;
import br.com.alura.alurapic.model.Photo;
import br.com.alura.alurapic.model.User;

public class CommentParser {
	
	public static CommentParser get() {
		return new CommentParser();
	}
	
	public CommentDto commentDto(Comment comment) {
		CommentDto dto = new CommentDto();
		dto.setDate(comment.getCommentDate());
		dto.setText(comment.getComment());
		dto.setUserName(comment.getUser().getName());
		
		return dto;
	}

	public Comment comment(CommentDto commentDto, Integer photoId, User user) {
		Comment comment =
				new Comment(LocalDateTime.now(), commentDto.getText(), new Photo(photoId), user);
		
		return comment;
		
		
	}
	
	
	
	

	
	
	
}

