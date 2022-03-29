package com.helpduck.helpduckrequests.service;

import java.util.List;

import com.helpduck.helpduckrequests.dto.RequestDTO;
import com.helpduck.helpduckrequests.entity.Request;
import com.helpduck.helpduckrequests.repository.RequestRepository;
import com.mongodb.MongoException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestServiceImpl implements RequestService {
  
  @Autowired
  private RequestRepository requestRepository;
  
  @Override
  public List<Request> getAllRequests() {
    return requestRepository.findAll();
  }

  @Override
  public Request getRequestById(String id) {

    if (requestRepository.findById(id).isPresent())
      return requestRepository.findById(id).get();
    else
      throw new MongoException("Record not Found");
  }

  @Override
  public Request createRequest(RequestDTO requestDTO) {
    Request request = new Request(requestDTO.getId(), requestDTO.getTitle(), requestDTO.getDescription(), requestDTO.getRegistrationDate());

    return requestRepository.save(request);
  }

  @Override
  public Request updateRequest(RequestDTO requestDTO, String id) {
    if (requestRepository.findById(id).isPresent()) {
      Request existingRequest = requestRepository.findById(id).get();

      existingRequest.setTitle(requestDTO.getTitle());
      existingRequest.setDescription(requestDTO.getDescription());

      return requestRepository.save(existingRequest);
    } else
      throw new MongoException("Record not found");
  }

  @Override
  public Request deleteRequestById(String id) {
    if (requestRepository.findById(id).isPresent()) {
      Request request = requestRepository.findById(id).get();
      requestRepository.delete(request);
      return request;
    } else
      throw new MongoException("Record not found");
  }

}
