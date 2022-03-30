package com.helpduck.helpduckrequests.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document()
public class Request {

	@Id
	private String id;
	private String title;
	private String description;
	private LocalDateTime registrationDate;

	public Request(
			String id,
			String title,
			String description,
			LocalDateTime registrationDate) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.registrationDate = registrationDate;
	}
}