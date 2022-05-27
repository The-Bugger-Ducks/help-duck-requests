package com.helpduck.helpducktickets.repository;

import com.helpduck.helpducktickets.entity.Ticket;
import com.helpduck.helpducktickets.enums.StatusEnum;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface TicketRepository extends MongoRepository<Ticket, String> {
  @Query("{'user.id': ?0}")
  Page<Ticket> findAllByUserId(Pageable pageable, String id);

  @Query("{'support.id': ?0}")
  Page<Ticket> findAllBySupportById(Pageable pageable, String id);

  Page<Ticket> findAllByStatus(Pageable pageable, StatusEnum status);
  
  @Query("{'ticket.title': {$regex: ?0 }})")
  Page<Ticket> findAllByTitle(Pageable pageable, String title);
}