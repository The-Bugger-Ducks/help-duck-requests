package com.helpduck.helpduckrequests.service;

import com.helpduck.helpduckrequests.entity.Request;
import com.helpduck.helpduckrequests.model.hateoas.RequestHateoas;
import com.helpduck.helpduckrequests.repository.RequestRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RequestService {

  @Autowired
  private RequestRepository repository;

  @Transactional(readOnly = true)
  public Page<RequestHateoas> findAll(Pageable pageable) {
    Page<Request> request = repository.findAll(pageable);
    Page<RequestHateoas> page = request.map(x -> new RequestHateoas(x));
    return page;
  }

  @Transactional(readOnly = true)
  public RequestHateoas findById(String id) {
    Request request = repository.findById(id).get();
    RequestHateoas requestHateoas= new RequestHateoas(request);
    return requestHateoas;
  }
}
