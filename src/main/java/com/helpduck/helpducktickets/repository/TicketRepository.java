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

  // filters HomePage
  @Query("{'title': {$regex: ?0, '$options' : 'i'}})")
  Page<Ticket> findAllByTitle(Pageable pageable, String title);

  @Query("{$and: [{'title': { $regex:?0, '$options' : 'i' }}, {'user.id': ?1 }] }")
  Page<Ticket> findAllByTitleAndClientId(Pageable pageable, String title, String clientId);

  @Query("{$and: [{'title': { $regex:?0, '$options' : 'i' }}, {'support.id': ?1 }] }")
  Page<Ticket> findAllByTitleAndSupportId(Pageable pageable, String title, String supportId);

  @Query("{$and: [{'status': ?0 }, {'support.id': ?1 }] }")
  Page<Ticket> findAllByStatusAndSupportId(Pageable pageable, StatusEnum status, String supportId);

  @Query("{$and: [{'status': ?0 }, {'user.id': ?1 }] }")
  Page<Ticket> findAllByStatusAndClientId(Pageable pageable, StatusEnum status, String clientId);

  @Query("{$and: [{'title': { $regex:?0, '$options' : 'i' }}, {'status': ?1 }] }")
  Page<Ticket> findAllByTitleAndFilterByStatus(Pageable pageable, String title, StatusEnum status);
}