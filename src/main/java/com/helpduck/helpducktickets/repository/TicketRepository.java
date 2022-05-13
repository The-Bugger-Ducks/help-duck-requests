package com.helpduck.helpducktickets.repository;

import com.helpduck.helpducktickets.entity.Ticket;
import com.helpduck.helpducktickets.enums.StatusEnum;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface TicketRepository extends MongoRepository<Ticket, String> {
  @Query("{'user.id': ?0}")
  Page<Ticket> findAllByUserId(Pageable pageable, String id);

  @Query("{'support.id': ?0}")
  Page<Ticket> findAllBySupportById(Pageable pageable, String id);

  Page<Ticket> findAllByStatus(Pageable pageable, StatusEnum status);

  @Query("{'priorityLevel': 'low'}")
  Page<Ticket> filterAllByLowPriority(Pageable pageable, StatusEnum priority);

  @Query("{'priorityLevel': 'medium'}")
  Page<Ticket> filterAllByMediumPriority(Pageable pageable, StatusEnum priority);

  @Query("{'priorityLevel': 'high'}")
  Page<Ticket> filterAllByHighPriority(Pageable pageable, StatusEnum priority);

  @Query("{'status': 'done'}")
  Page<Ticket> filterAllByDoneStatus(Pageable pageable, StatusEnum status);

  @Query("{'status': 'awaiting'}")
  Page<Ticket> filterAllByAwaitingStatus(Pageable pageable, StatusEnum status);

  @Query("{'status': 'underAnalysis'}")
  Page<Ticket> filterAllByUnderAnalysisStatus(Pageable pageable, StatusEnum status);

  //@Query("{'tickets.createdAt': ?0}")
  //Page<Ticket> findTicketsByDate (Pageable pageable, Sort sort /*,String filter*/);

  //@Query("{'tickets.priorityLevel': ?0}")
  //Page<Ticket> findTicketsByPriorityLevel (Pageable pageable, Sort sort);
}