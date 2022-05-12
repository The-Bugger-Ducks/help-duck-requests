package com.helpduck.helpducktickets.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.helpduck.helpducktickets.enums.PriorityLevelEnum;
import com.helpduck.helpducktickets.enums.StatusEnum;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document()
public class Ticket {

	@Id
	private String id;
	private String title;
	private String description;
	private List<String> tags;
	private Equipment equipment;
	private PriorityLevelEnum priorityLevel;
	private StatusEnum status;

	private User user;
	private User support;

	private List<Comment> comments;

	private Solution solution;

	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}