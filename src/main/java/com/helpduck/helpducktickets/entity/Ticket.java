package com.helpduck.helpducktickets.entity;

import java.time.LocalDateTime;
import java.util.List;

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
	private String subject;
	private String ownerUserId;
	private String supportId;
	private List<String> tags;
	private String priorityLevel;
	private String status;
	private Boolean reserved;    
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public Ticket(
			String id,
			String title,
			String description,
			String subject,
			String ownerUserId,
			String supportId,
			List<String> tags,
			String priorityLevel,
			String status,
			Boolean reserved,    
			LocalDateTime createdAt,
			LocalDateTime updatedAt) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.subject = subject;
		this.ownerUserId = ownerUserId;
		this.supportId = supportId;
		this.tags = tags;
		this.priorityLevel = priorityLevel;
		this.status = status;
		this.reserved = reserved;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
}