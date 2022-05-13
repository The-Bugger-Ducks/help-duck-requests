package com.helpduck.helpducktickets.service;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Optional;

import com.helpduck.helpducktickets.enums.StatusEnum;
import com.helpduck.helpducktickets.entity.Comment;
import com.helpduck.helpducktickets.entity.Ticket;
import com.helpduck.helpducktickets.model.hateoas.TicketHateoas;
import com.helpduck.helpducktickets.repository.TicketRepository;

import org.springframework.data.mongodb.core.aggregation.ArrayOperators.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.mongodb.core.aggregation.AggregationExpression;
import org.springframework.data.mongodb.repository.Query;

//public class FilterService {

//    @Transactional(readOnly = true)
//    public Page<TicketHateoas> filteredTickets
//}

/*{

    @Autowired
  private TicketRepository repository;

    @Transactional(readOnly = true)
        public Page<TicketHateoas> sortTicketsByDate(Pageable pageable, Sort sort) {
        Page<Ticket> tickets = repository.findTicketsByDate(pageable, sort);
        Page<TicketHateoas> ticketsHateoas = tickets.map(x -> new TicketHateoas(x));

    return ticketsHateoas;
        }
        //Page<TicketHateoas> filter = repository.findAllTicketsToFilter()
        //(Sort.by(Sort.Direction.ASC, "createdAt"));
    //return filter;

    //Query query = new Query();
   // query.with(Sort.by(Sort.Direction.ASC, "createdAt"));
    //Page<Ticket> chamadosFiltrados = findAllTicketsToFilter(pageable).find(query);
    //List<User> users = mongoTemplate.find(query,User.class);
    
   // Page <Ticket> chamadosFiltrados = findAllTicketsToFilter;
} */
