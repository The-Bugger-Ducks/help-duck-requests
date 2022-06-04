package com.helpduck.helpducktickets.controller;

import java.util.Optional;

import com.helpduck.helpducktickets.entity.Ticket;
import com.helpduck.helpducktickets.enums.PriorityLevelEnum;
import com.helpduck.helpducktickets.enums.StatusEnum;
import com.helpduck.helpducktickets.model.tickets.TicketLinkAdder;
import com.helpduck.helpducktickets.model.hateoas.TicketHateoas;
import com.helpduck.helpducktickets.model.tickets.TicketUpdater;
import com.helpduck.helpducktickets.repository.TicketRepository;
import com.helpduck.helpducktickets.service.TicketService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	ResponseEntity<Page<TicketHateoas>> responseTicketHateoas = new ResponseEntity<>(HttpStatus.NOT_FOUND);

	public void updateResponseTicketHateoas(Page<TicketHateoas> pageTicketHateoas) {
		if (!pageTicketHateoas.isEmpty()) {
			linkAdder.addLink(pageTicketHateoas);
			responseTicketHateoas = new ResponseEntity<Page<TicketHateoas>>(pageTicketHateoas, HttpStatus.FOUND);
		} else {
			responseTicketHateoas = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PreAuthorize("hasRole('support')")
	@GetMapping("/")
	public ResponseEntity<Page<TicketHateoas>> getTickets(
			@PageableDefault(sort = { "priorityLevel", "createdAt" }, direction = Direction.ASC) Pageable pageable) {

		Page<TicketHateoas> pageTicketHateoas = service.findAll(pageable);
		updateResponseTicketHateoas(pageTicketHateoas);
		return responseTicketHateoas;
	}

	@PreAuthorize("hasRole('client') or hasRole('support')")
	@GetMapping("/{ticketId}")
	public ResponseEntity<TicketHateoas> getTicket(@PathVariable String ticketId) {

		TicketHateoas ticketHateoas = service.findByIdHateoas(ticketId);
		if (ticketHateoas == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		linkAdder.addLink(ticketHateoas);
		return new ResponseEntity<TicketHateoas>(ticketHateoas, HttpStatus.FOUND);
	}

	@PreAuthorize("hasRole('client') or hasRole('support')")
	@GetMapping("/user/{id}")
	public ResponseEntity<Page<TicketHateoas>> getTicketByUserId(@PathVariable String id,
			@PageableDefault(sort = "createdAt", direction = Direction.ASC) Pageable pageable) {

		Page<TicketHateoas> pageTicketHateoas = service.findAllByUserIdService(pageable, id);
		updateResponseTicketHateoas(pageTicketHateoas);
		return responseTicketHateoas;
	}

	@PreAuthorize("hasRole('support')")
	@GetMapping("/support/{supportId}")
	public ResponseEntity<Page<TicketHateoas>> getTicketsBySupportId(@PathVariable String supportId,
			@PageableDefault(sort = { "priorityLevel", "createdAt" }, direction = Direction.ASC) Pageable pageable) {

		Page<TicketHateoas> pageTicketHateoas = service.findAllBySupportIdService(pageable, supportId);
		updateResponseTicketHateoas(pageTicketHateoas);
		return responseTicketHateoas;
	}

	@PreAuthorize("hasRole('client') or hasRole('support')")
	@GetMapping("/status/{statusToFind}")
	public ResponseEntity<Page<TicketHateoas>> getTicketsByStatus(@PathVariable StatusEnum statusToFind,
			@PageableDefault(sort = { "priorityLevel", "createdAt" }, direction = Direction.ASC) Pageable pageable) {

		Page<TicketHateoas> pageTicketHateoas = service.findAllByStatusService(pageable, statusToFind);
		updateResponseTicketHateoas(pageTicketHateoas);
		return responseTicketHateoas;
	}

	@PreAuthorize("hasRole('client')")
	@PostMapping("/create")
	public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket) {

		if (ticket.getId() != null) {
			return new ResponseEntity<Ticket>(HttpStatus.CONFLICT);
		}
		Ticket ticketInserted = service.create(ticket);
		return new ResponseEntity<Ticket>(ticketInserted, HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('support')")
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

	@PreAuthorize("hasRole('client') or hasRole('support')")
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

	@PreAuthorize("hasRole('client') or hasRole('support')")
	@GetMapping("/search")
	public ResponseEntity<Page<TicketHateoas>> getTicketsByTitle(Pageable pageable,
			@RequestParam Optional<String> ticketTitle, @RequestParam Optional<String> supportId,
			@RequestParam Optional<String> clientId, @RequestParam Optional<StatusEnum> status,
			@RequestParam Optional<PriorityLevelEnum> priority) {

		Page<TicketHateoas> pageTicketHateoas;

		if (ticketTitle.isPresent() && clientId.isPresent()) {
			pageTicketHateoas = service.findAllByTitleAndClientId(pageable, ticketTitle.get(), clientId.get());
		} else if (ticketTitle.isPresent() && supportId.isPresent()) {
			pageTicketHateoas = service.findAllByTitleAndSupportId(pageable, ticketTitle.get(), supportId.get());
		} else if (ticketTitle.isPresent() && status.isPresent()) {
			pageTicketHateoas = service.findAllByTitleAndFilterByStatus(pageable, ticketTitle.get(), status.get());
		} else if (ticketTitle.isPresent() && !status.isPresent() && !priority.isPresent() && !clientId.isPresent()
				&& !supportId.isPresent()) {
			pageTicketHateoas = service.findAllByTicketTitle(pageable, ticketTitle.get());
		} else if (ticketTitle.isPresent() && priority.isPresent()) {
			pageTicketHateoas = service.findAllByTitleAndPriorityLevel(pageable, ticketTitle.get(), priority.get());
		} else if (clientId.isPresent()) {
			pageTicketHateoas = service.findAllByUserIdService(pageable, clientId.get());
		} else if (supportId.isPresent()) {
			pageTicketHateoas = service.findAllBySupportIdService(pageable, supportId.get());
		} else {
			pageTicketHateoas = service.findAll(pageable);
		}

		if (pageTicketHateoas.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		linkAdder.addLink((pageTicketHateoas));
		return new ResponseEntity<Page<TicketHateoas>>(pageTicketHateoas, HttpStatus.FOUND);
	}

}
