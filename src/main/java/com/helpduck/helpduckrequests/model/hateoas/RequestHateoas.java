package com.helpduck.helpduckrequests.model.hateoas;

import java.time.LocalDateTime;

import com.helpduck.helpduckrequests.entity.Request;

import org.springframework.data.annotation.Id;
import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestHateoas extends RepresentationModel<RequestHateoas>{
  @Id
	private String id;
	private String title;
	private String description;
	private LocalDateTime registrationDate;

  public RequestHateoas() {
	}

	public RequestHateoas(
			String id,
			String title,
			String description,
			LocalDateTime registrationDate) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.registrationDate = registrationDate;
	}

  public RequestHateoas(Request request) {
    id = request.getId();
    title = request.getTitle();
    description = request.getDescription();
    registrationDate = request.getRegistrationDate();
  }
}
