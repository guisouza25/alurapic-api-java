package br.com.alura.alurapic.dto;

import java.time.LocalDateTime;

import javax.json.bind.annotation.JsonbDateFormat;

public class CommentDto {
	
	@JsonbDateFormat("dd/MM/yyyy HH:mm")
	LocalDateTime date;
	
	String text;
	
	String userName;

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	
	
	
	
}
