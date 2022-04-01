package com.helpduck.helpducktickets.service;

import com.helpduck.helpducktickets.entity.Ticket;
import com.helpduck.helpducktickets.model.hateoas.TicketHateoas;
import com.helpduck.helpducktickets.repository.TicketRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TicketService {

  @Autowired
  private TicketRepository repository;

  @Transactional(readOnly = true)
  public Page<TicketHateoas> findAll(Pageable pageable) {
    Page<Ticket> ticket = repository.findAll(pageable);
    Page<TicketHateoas> page = ticket.map(x -> new TicketHateoas(x));
    return page;
  }

  @Transactional(readOnly = true)
  public TicketHateoas findById(String id) {
    Ticket ticket = repository.findById(id).get();
    TicketHateoas ticketHateoas= new TicketHateoas(ticket);
    return ticketHateoas;
  }
}
