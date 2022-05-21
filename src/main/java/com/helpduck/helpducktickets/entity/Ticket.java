package com.helpduck.helpducktickets.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.helpduck.helpducktickets.enums.PriorityLevelEnum;
import com.helpduck.helpducktickets.enums.StatusEnum;

import org.hibernate.annotations.Formula;
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

	@Enumerated(EnumType.ORDINAL) // The default
	private PriorityLevelEnum priorityLevel;

	@Enumerated(EnumType.ORDINAL) // The default
	@Formula("case status when 'awaiting' then 0 when 'underAnalysis' then 1 .when done then 2 end")
	private StatusEnum status;

	private User user;
	private User support;

	private List<Comment> comments;

	private Solution solution;

	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private LocalDateTime concludedAt;
}