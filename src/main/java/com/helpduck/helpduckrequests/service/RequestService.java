package com.helpduck.helpduckrequests.service;

import java.util.List;

import com.helpduck.helpduckrequests.dto.RequestDTO;
import com.helpduck.helpduckrequests.entity.Request;

public interface RequestService {
  public List<Request> getAllRequests();

  public Request getRequestById(String id);

  public Request createRequest(RequestDTO requestcreteDTO);

  public Request updateRequest(RequestDTO requestUpdateDTO, String id);

  public Request deleteRequestById(String id);
}
