package com.helpduck.helpducktickets.model.hateoas;

import java.time.LocalDateTime;

import com.helpduck.helpducktickets.entity.Ticket;

import org.springframework.data.annotation.Id;
import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TicketHateoas extends RepresentationModel<TicketHateoas> {

	@Id
	private String id;
	private String title;
	private String description;
	private String subject;
	private String ownerUserId;
	private String supportId;
	private List<String> tags;
	private String priorityLevel;
	private String status;
	private Boolean reserved;    
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public TicketHateoas() {
	}

  public TicketHateoas(Ticket ticket) {
    id = ticket.getId();
    title = ticket.getTitle();
    description = ticket.getDescription();
		subject = ticket.getSubject();
		ownerUserId = ticket.getOwnerUserId();
		supportId = ticket.getSupportId();
		tags = ticket.getTags();
		priorityLevel = ticket.getPriorityLevel();
		status = ticket.getStatus();
		reserved = ticket.getReserved();
    createdAt = ticket.getCreatedAt();
		updatedAt = ticket.getUpdatedAt();
  }
}
