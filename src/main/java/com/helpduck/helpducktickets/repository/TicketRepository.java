package com.helpduck.helpducktickets.repository;

import com.helpduck.helpducktickets.entity.Ticket;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TicketRepository extends MongoRepository<Ticket, String> {
}