package com.helpduck.helpducktickets.model.tickets;

import java.time.LocalDateTime;
import java.time.ZoneId;

import com.helpduck.helpducktickets.entity.Ticket;
import com.helpduck.helpducktickets.model.NullStringVerifier;

public class TicketUpdater {
	private NullStringVerifier verifier = new NullStringVerifier();

	public void update(Ticket ticket, Ticket updatedTicket) {
		if (!verifier.verify(updatedTicket.getTitle())) {
			ticket.setTitle(updatedTicket.getTitle());
		}

		if (!verifier.verify(updatedTicket.getDescription())) {
			ticket.setDescription(updatedTicket.getDescription());
		}
		
		
		ticket.setUpdatedAt(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
	}
}
