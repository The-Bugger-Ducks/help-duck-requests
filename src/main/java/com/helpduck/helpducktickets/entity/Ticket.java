package com.helpduck.helpducktickets.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.helpduck.helpducktickets.enums.DepartmentEnum;
import com.helpduck.helpducktickets.enums.PriorityLevelEnum;
import com.helpduck.helpducktickets.enums.StatusEnum;
import com.mongodb.lang.Nullable;

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
	@Nullable
	private Problem problem;
	private Equipment equipment;

	@Enumerated(EnumType.ORDINAL)
	private PriorityLevelEnum priorityLevel;

	@Enumerated(EnumType.ORDINAL)
	private StatusEnum status;
	@Enumerated(EnumType.ORDINAL)
	private DepartmentEnum department;

	private User user;
	private User support;

	private List<Comment> comments;

	private Solution solution;

	private LocalDateTime reservedAt;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private LocalDateTime concludedAt;
}