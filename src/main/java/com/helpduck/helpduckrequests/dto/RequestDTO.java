package com.helpduck.helpduckrequests.dto;

import java.time.LocalDateTime;

import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestDTO extends RepresentationModel<RequestDTO> {

  private String id;
  private String title;
  private String description;
  private LocalDateTime registrationDate;
}
