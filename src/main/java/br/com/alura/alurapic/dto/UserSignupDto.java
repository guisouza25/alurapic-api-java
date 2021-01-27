package br.com.alura.alurapic.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import br.com.alura.alurapic.validators.LowerCase;
import br.com.alura.alurapic.validators.UserNameTaken;

public class UserSignupDto {
	
	@UserNameTaken(message = "Username already taken. Choose a different user name")
	@LowerCase(message = "Name must be lower case")
	@NotBlank(message = "Name is required")
	@Length(min = 2, message = "Name minimum lenth must be 2")
	@Length(max = 30, message = "Name maximum lenth must be 30")
	private String name;
	
	@NotBlank(message = "Password is required")
	@Length(min = 8, message = "Password minimum lenth must be 8")
	@Length(max = 14, message = "Password maximum lenth must be 14")
	private String password;
	
	@NotBlank(message = "Email is required")
	@Email(message = "Invalid email")
	private String email;
	
	@NotBlank(message = "Full name is required")
	@Length(min = 2, message = "Full name minimum lenth must be 2")
	@Length(max = 40, message = "Full name maximum lenth must be 30")
	private String fullName;
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}	
}






