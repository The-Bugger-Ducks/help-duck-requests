package com.helpduck.helpducktickets.repository;

import com.helpduck.helpducktickets.entity.Ticket;
import com.helpduck.helpducktickets.enums.PriorityLevelEnum;
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

  // filters HomePage
  @Query("{'title': {$regex: ?0, '$options' : 'i'}})")
  Page<Ticket> findAllByTitle(Pageable pageable, String title);

  @Query("{'title': { $regex:?0, '$options' : 'i' }}, $and: [{'user.id': ?1 }] }")
  Page<Ticket> findAllByTitleAndClientId(Pageable pageable, String title, String clientId);

  @Query("{'title': { $regex:?0, '$options' : 'i' }}, $and: [{'support.id': ?1 }] }")
  Page<Ticket> findAllByTitleAndSupportId(Pageable pageable, String title, String supportId);

  @Query("{'title': { $regex:?0, '$options' : 'i' }}, $and: [{'status': ?1 }] }")
  Page<Ticket> findAllByTitleAndFilterByStatus(Pageable pageable, String title, StatusEnum status);

  @Query("{'title': { $regex:?0, '$options' : 'i' }}, $and: [{'priorityLevel': ?1 }] }")
  Page<Ticket> findAllByTitleAndPriorityLevel(Pageable pageable, String title, PriorityLevelEnum priority);
}