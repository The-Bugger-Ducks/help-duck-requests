package com.helpduck.helpducktickets.repository;

import com.helpduck.helpducktickets.entity.Ticket;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface TicketRepository extends MongoRepository<Ticket, String> {
  @Query("{'user.id': ?0}")
  Page<Ticket> findAllByUserId(Pageable pageable, String id);

  @Query("{'support.id': ?0}")
  Page<Ticket> findAllBySupportId(Pageable pageable, String id);

  @Query("{'support': null}")
  Page<Ticket> findAllNullSupport(Pageable pageable);
}