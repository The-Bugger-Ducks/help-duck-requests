package com.helpduck.helpducktickets.interfaces;

import com.helpduck.helpducktickets.entity.Solution;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "solutionTicket", url = "http://localhost:8082/solutions")
public interface SolutionRequests {

  @RequestMapping(method = RequestMethod.GET, value = "/ticket/{ticketId}")
  Solution returnSolution(@PathVariable("ticketId") String ticketId);
}
