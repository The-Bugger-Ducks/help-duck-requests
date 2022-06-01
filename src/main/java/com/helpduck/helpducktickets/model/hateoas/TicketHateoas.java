package com.helpduck.helpducktickets.model.hateoas;

import com.helpduck.helpducktickets.entity.Comment;
import com.helpduck.helpducktickets.entity.Equipment;
import com.helpduck.helpducktickets.entity.Problem;
import com.helpduck.helpducktickets.entity.Solution;
import com.helpduck.helpducktickets.entity.Ticket;
import com.helpduck.helpducktickets.entity.User;
import com.helpduck.helpducktickets.enums.DepartmentEnum;
import com.helpduck.helpducktickets.enums.PriorityLevelEnum;
import com.helpduck.helpducktickets.enums.StatusEnum;

import org.springframework.data.annotation.Id;
import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class TicketHateoas extends RepresentationModel<TicketHateoas> {

	@Id
	private String id;
	private String title;
	private String description;
	private User user;
	private User support;
	private Problem problem;
	private Equipment equipment;
	private PriorityLevelEnum priorityLevel;
	private StatusEnum status;
	private DepartmentEnum department;
	private List<Comment> comments;
	private Solution solution;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private LocalDateTime concludedAt;

	public TicketHateoas(Ticket ticket) {
		id = ticket.getId();
		title = ticket.getTitle();
		description = ticket.getDescription();
		user = ticket.getUser();
		support = ticket.getSupport();
		problem = ticket.getProblem();
		equipment = ticket.getEquipment();
		priorityLevel = ticket.getPriorityLevel();
		status = ticket.getStatus();
		department = ticket.getDepartment();
		comments = ticket.getComments();
		solution = ticket.getSolution();
		createdAt = ticket.getCreatedAt();
		updatedAt = ticket.getUpdatedAt();
		concludedAt = ticket.getConcludedAt();
	}
}
