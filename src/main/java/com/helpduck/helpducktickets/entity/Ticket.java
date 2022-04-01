package com.helpduck.helpducktickets.entity;

import java.util.Calendar;
import java.util.List;

import com.helpduck.helpducktickets.enums.PriorityLevelEnum;
import com.helpduck.helpducktickets.enums.StatusEnum;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document()
public class Ticket {

	@Id
	private String id;
	private String title;
	private String description;
	private String user;
	private String support;
	private List<String> tags;
	private PriorityLevelEnum priorityLevel;
	private StatusEnum status;
	private Boolean reserved;    
	private Calendar createdAt;
	private Calendar updatedAt;

	public Ticket(
			String id,
			String title,
			String description,
			String user,
			String support,
			List<String> tags,
			PriorityLevelEnum priorityLevel,
			StatusEnum status,
			Boolean reserved,    
			Calendar createdAt,
			Calendar updatedAt) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.user = user;
		this.support = support;
		this.tags = tags;
		this.priorityLevel = priorityLevel;
		this.status = status;
		this.reserved = reserved;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
}