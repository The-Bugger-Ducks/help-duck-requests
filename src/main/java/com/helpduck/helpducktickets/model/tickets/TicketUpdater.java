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

		if (!verifier.verify(updatedTicket.getPriorityLevel())) {
			ticket.setPriorityLevel(updatedTicket.getPriorityLevel());
		}

		if (!verifier.verify(updatedTicket.getStatus())) {
			ticket.setStatus(updatedTicket.getStatus());
		}

		if (!verifier.verify(updatedTicket.getEquipment())) {
			ticket.setEquipment(updatedTicket.getEquipment());
		}

		if (!verifier.verify(updatedTicket.getUser())) {
			ticket.setUser(updatedTicket.getUser());
		}

		if (!verifier.verify(updatedTicket.getSupport())) {
			ticket.setSupport(updatedTicket.getSupport());
		}

		if (!verifier.verify(updatedTicket.getTags())) {
			ticket.setTags(updatedTicket.getTags());
		}

		if (!verifier.verify(updatedTicket.getDepartment())) {
			ticket.setDepartment(updatedTicket.getDepartment());
		}

		ticket.setUpdatedAt(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
	}
}
