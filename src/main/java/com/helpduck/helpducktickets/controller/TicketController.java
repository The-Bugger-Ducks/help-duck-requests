package com.helpduck.helpducktickets.controller;

import java.util.Calendar;
import java.util.Optional;

import com.helpduck.helpducktickets.entity.Ticket;
import com.helpduck.helpducktickets.model.TicketLinkAdder;
import com.helpduck.helpducktickets.model.TicketUpdater;
import com.helpduck.helpducktickets.model.hateoas.TicketHateoas;
import com.helpduck.helpducktickets.repository.TicketRepository;
import com.helpduck.helpducktickets.service.TicketService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tickets")
public class TicketController {
	@Autowired
	private TicketRepository repository;
	@Autowired
  private TicketService service;
	@Autowired
	TicketLinkAdder linkAdder;

	MongoTemplate mongoTemplate;

	@GetMapping("/")
	public ResponseEntity<Page<TicketHateoas>> getTickets(Pageable pageable) {
		Page<TicketHateoas> pageTicketHateoas = service.findAll(pageable);
		ResponseEntity<Page<TicketHateoas>> response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		if (!pageTicketHateoas.isEmpty()) {
			linkAdder.addLink(pageTicketHateoas);
			response = new ResponseEntity<Page<TicketHateoas>>(pageTicketHateoas, HttpStatus.FOUND);
		}
		return response;
	}

	@GetMapping("/{id}")
	public ResponseEntity<TicketHateoas> getTicket(@PathVariable String id) {
		ResponseEntity<TicketHateoas> response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		TicketHateoas ticketHateoas = service.findById(id);

		if (ticketHateoas != null) {
			linkAdder.addLink(ticketHateoas);
			response = new ResponseEntity<TicketHateoas>(ticketHateoas, HttpStatus.FOUND);
		}
		return response;
	}

	@PostMapping("/create")
	public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket) {
		HttpStatus status = HttpStatus.CONFLICT;

		if (ticket.getId() == null) {
			ticket.setCreatedAt(Calendar.getInstance());
			ticket.setUpdatedAt(Calendar.getInstance());
			ticket.setReserved(false);
			Ticket ticketInserted = repository.insert(ticket);
			status = HttpStatus.CREATED;
			return new ResponseEntity<Ticket>(ticketInserted, status);
		}
		return new ResponseEntity<Ticket>(status);
	}

	@PutMapping("/update")
	public ResponseEntity<HttpStatus> updateTicket(@RequestBody Ticket updatedTicket) {
		Optional<Ticket> ticketOptional = repository.findById(updatedTicket.getId());
		HttpStatus status = HttpStatus.BAD_REQUEST;

		if (!ticketOptional.isEmpty()) {
			Ticket ticket = ticketOptional.get();
			TicketUpdater updater = new TicketUpdater();
			updater.update(ticket, updatedTicket);
			repository.save(ticket);
			status = HttpStatus.OK;
		}
		return new ResponseEntity<HttpStatus>(status);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<HttpStatus> deleteTicket(@RequestBody Ticket ticketToDelete) {
		Optional<Ticket> ticketOptional = repository.findById(ticketToDelete.getId());
		HttpStatus status = HttpStatus.BAD_REQUEST;

		if (!ticketOptional.isEmpty()) {
			repository.deleteById(ticketToDelete.getId());
			status = HttpStatus.OK;
		}
		return new ResponseEntity<HttpStatus>(status);
	}
}
