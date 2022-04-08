package com.helpduck.helpducktickets.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import com.helpduck.helpducktickets.entity.Comment;
import com.helpduck.helpducktickets.entity.Ticket;
import com.helpduck.helpducktickets.entity.User;
import com.helpduck.helpducktickets.enums.StatusEnum;
import com.helpduck.helpducktickets.model.tickets.TicketLinkAdder;
import com.helpduck.helpducktickets.repository.TicketRepository;
import com.helpduck.helpducktickets.service.TicketService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/helpUser")
public class HelpUserController {

  @Autowired
  private TicketRepository repository;
  @Autowired
  private TicketService service;
  @Autowired
  TicketLinkAdder linkAdder;

  @PutMapping("/reserveTicket/{ticketId}")
  public ResponseEntity<Ticket> reserveTicketBySupport(@PathVariable String ticketId, @RequestBody User support) {

    Ticket ticketFound = service.findById(ticketId);
    if (ticketFound == null) {
      return new ResponseEntity<Ticket>(HttpStatus.NOT_FOUND);
    } else if (ticketFound.getSupport() != null) {
      return new ResponseEntity<Ticket>(HttpStatus.CONFLICT);
    }

    ticketFound.setSupport(support);
    ticketFound.setStatus(StatusEnum.underAnalysis);
    ticketFound.setUpdatedAt(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
    repository.save(ticketFound);
    return new ResponseEntity<Ticket>(HttpStatus.OK);
  }

  @PutMapping("/updateComment/{ticketId}")
  public ResponseEntity<Ticket> awswerTicketComment(@PathVariable String ticketId, @RequestBody Comment newComment) {

    Ticket ticket = service.findById(ticketId);
    if (ticket == null) {
      return new ResponseEntity<Ticket>(HttpStatus.NOT_FOUND);
    }

    List<Comment> commentsList = ticket.getComments();
    commentsList.add(newComment);
    ticket.setComments(commentsList);

    ticket.setUpdatedAt(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
    repository.save(ticket);
    return new ResponseEntity<Ticket>(HttpStatus.OK);
  }
}
