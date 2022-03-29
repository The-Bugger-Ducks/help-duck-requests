package com.helpduck.helpduckrequests.model;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.helpduck.helpduckrequests.controller.RequestController;
import com.helpduck.helpduckrequests.entity.Request;

@Component
public class RequestLinkAdder implements LinkAdder<Request> {

	@Override
	public void addLink(List<Request> list) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addLink(Request object) {
		// TODO Auto-generated method stub
		
	}

	// @Override
	// public void addLink(List<Request> requests) {
	// 	for (Request request : requests) {
	// 		String id = request.getId();
	// 		Link linkToItself = WebMvcLinkBuilder
	// 				.linkTo(WebMvcLinkBuilder
	// 						.methodOn(RequestController.class)
	// 						.getRequest(id))
	// 				.withSelfRel();
	// 		request.add(linkToItself);
	// 	}
	// }

	// @Override
	// public void addLink(Request request) {
	// 	Link linkToAllRequests = WebMvcLinkBuilder
	// 			.linkTo(WebMvcLinkBuilder
	// 					.methodOn(RequestController.class)
	// 					.getRequests())
	// 			.withRel("requests");
	// 	request.add(linkToAllRequests);
	// }
}