package com.helpduck.helpducktickets.model;

import java.util.Calendar;

import com.helpduck.helpducktickets.entity.Ticket;

public class TicketUpdater {
	private NullStringVerifier verifier = new NullStringVerifier();

	public void update(Ticket ticket, Ticket updatedTicket) {
		if (!verifier.verify(updatedTicket.getTitle())) {
			ticket.setTitle(updatedTicket.getTitle());
		}

		if (!verifier.verify(updatedTicket.getDescription())) {
			ticket.setDescription(updatedTicket.getDescription());
		}
		
		
		ticket.setUpdatedAt(Calendar.getInstance());
	}
}
