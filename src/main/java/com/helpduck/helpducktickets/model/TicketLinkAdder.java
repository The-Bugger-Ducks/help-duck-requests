package com.helpduck.helpducktickets.model;

import com.helpduck.helpducktickets.controller.TicketController;
import com.helpduck.helpducktickets.model.hateoas.TicketHateoas;

import org.springframework.data.domain.Page;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class TicketLinkAdder implements LinkAdder<TicketHateoas> {

	@Override
	public void addLink(Page<TicketHateoas> tickets) {
		for (TicketHateoas ticket : tickets) {
			String id = ticket.getId();
			Link linkToItself = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(TicketController.class)
							.getTicket(id))
					.withSelfRel();
			ticket.add(linkToItself);
		}
	}

	@Override
	public void addLink(TicketHateoas ticket) {
		Link linkToAlltickets = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(TicketController.class)
						.getTickets(null))
				.withRel("tickets");
		ticket.add(linkToAlltickets);
	}
}