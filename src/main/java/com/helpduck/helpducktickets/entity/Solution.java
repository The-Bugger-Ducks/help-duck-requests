package com.helpduck.helpducktickets.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Document()
public class Solution extends RepresentationModel<Solution> {

  @Id
  private String id;
  private String idTicket;
  private String titleProblem;

  // tags related to issues that the solution is valid for
  private List<String> problemTags;

  private Comment solutionComment;

  private int upVote;
  private int downVote;

  private LocalDateTime createdAt;
}
