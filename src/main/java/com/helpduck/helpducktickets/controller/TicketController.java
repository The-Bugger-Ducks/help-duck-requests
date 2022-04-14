package com.helpduck.helpducktickets.controller;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import com.helpduck.helpducktickets.entity.Ticket;
import com.helpduck.helpducktickets.enums.StatusEnum;
import com.helpduck.helpducktickets.model.tickets.TicketLinkAdder;
import com.helpduck.helpducktickets.model.hateoas.TicketHateoas;
import com.helpduck.helpducktickets.model.tickets.TicketUpdater;
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

	@GetMapping()
	public Map<String, Object> statusAPI() {

		Map<String, Object> rtn = new LinkedHashMap<>();
		rtn.put("message", "Tickets microservice online :]");
		return rtn;
	}

	@GetMapping("/")
	public ResponseEntity<Page<TicketHateoas>> getTickets(Pageable pageable) {

		ResponseEntity<Page<TicketHateoas>> response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		Page<TicketHateoas> pageTicketHateoas = service.findAll(pageable);
		if (!pageTicketHateoas.isEmpty()) {
			linkAdder.addLink(pageTicketHateoas);
			response = new ResponseEntity<Page<TicketHateoas>>(pageTicketHateoas, HttpStatus.FOUND);
		}
		return response;
	}

	@GetMapping("/{ticketId}")
	public ResponseEntity<TicketHateoas> getTicket(@PathVariable String ticketId) {

		TicketHateoas ticketHateoas = service.findByIdHateoas(ticketId);
		if (ticketHateoas == null) {
			new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		linkAdder.addLink(ticketHateoas);
		return new ResponseEntity<TicketHateoas>(ticketHateoas, HttpStatus.FOUND);
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<Page<TicketHateoas>> getTicketByUserId(@PathVariable String id, Pageable pageable) {
		ResponseEntity<Page<TicketHateoas>> response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		Page<TicketHateoas> ticketHateoas = service.findAllByUserIdService(pageable, id);
		if (ticketHateoas != null) {
			linkAdder.addLink(ticketHateoas);
			response = new ResponseEntity<Page<TicketHateoas>>(ticketHateoas, HttpStatus.FOUND);
		}
		return response;
	}

	@GetMapping("/support/{supportId}")
	public ResponseEntity<Page<TicketHateoas>> getTicketsBySupportId(Pageable pageable, @PathVariable String supportId) {

		ResponseEntity<Page<TicketHateoas>> response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		Page<TicketHateoas> pageTicketHateoas = service.findAllBySupportIdService(pageable, supportId);
		if (!pageTicketHateoas.isEmpty()) {
			linkAdder.addLink(pageTicketHateoas);
			response = new ResponseEntity<Page<TicketHateoas>>(pageTicketHateoas, HttpStatus.FOUND);
		}
		return response;
	}

	@GetMapping("/status/{statusToFind}")
	public ResponseEntity<Page<TicketHateoas>> getTicketsByStatus(Pageable pageable,
			@PathVariable StatusEnum statusToFind) {

		ResponseEntity<Page<TicketHateoas>> response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		Page<TicketHateoas> pageTicketHateoas = service.findAllByStatusService(pageable, statusToFind);
		if (!pageTicketHateoas.isEmpty()) {
			linkAdder.addLink(pageTicketHateoas);
			response = new ResponseEntity<Page<TicketHateoas>>(pageTicketHateoas, HttpStatus.FOUND);
		}
		return response;
	}

	@PostMapping("/create")
	public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket) {

		if (ticket.getId() != null) {
			return new ResponseEntity<Ticket>(HttpStatus.CONFLICT);
		}

		Ticket ticketInserted = service.create(ticket);
		return new ResponseEntity<Ticket>(ticketInserted, HttpStatus.CREATED);
	}

	@PutMapping("/update")
	public ResponseEntity<HttpStatus> updateTicket(@RequestBody Ticket updatedTicket) {

		HttpStatus status = HttpStatus.BAD_REQUEST;
		Optional<Ticket> ticketOptional = repository.findById(updatedTicket.getId());

		if (!ticketOptional.isEmpty()) {
			Ticket ticket = ticketOptional.get();
			TicketUpdater updater = new TicketUpdater();
			updater.update(ticket, updatedTicket);
			repository.save(ticket);
			status = HttpStatus.OK;
		}
		return new ResponseEntity<HttpStatus>(status);
	}

	@DeleteMapping("/delete/{ticketId}")
	public ResponseEntity<HttpStatus> deleteTicket(@PathVariable String ticketId) {

		HttpStatus status = HttpStatus.BAD_REQUEST;
		Optional<Ticket> ticketOptional = repository.findById(ticketId);

		if (!ticketOptional.isEmpty()) {
			repository.deleteById(ticketId);
			status = HttpStatus.OK;
		}
		return new ResponseEntity<HttpStatus>(status);
	}
}
