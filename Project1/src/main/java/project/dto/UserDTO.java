package project.dto;

import project.domain.User;

public class UserDTO {
	private Long id;
	private String email;
	private String name;
	private String surname;
	
	public UserDTO(User user){
		this.id = user.getId();
		this.email=user.getEmail();
		this.name = user.getName();
		this.surname = user.getSurname();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
}
