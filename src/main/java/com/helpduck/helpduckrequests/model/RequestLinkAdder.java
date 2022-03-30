package com.helpduck.helpduckrequests.model;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.helpduck.helpduckrequests.controller.RequestController;
import com.helpduck.helpduckrequests.model.hateoas.RequestHateoas;

@Component
public class RequestLinkAdder implements LinkAdder<RequestHateoas> {

	@Override
	public void addLink(Page<RequestHateoas> requests) {
		for (RequestHateoas request : requests) {
			String id = request.getId();
			Link linkToItself = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(RequestController.class)
							.getRequest(id))
					.withSelfRel();
			request.add(linkToItself);
		}
	}

	@Override
	public void addLink(RequestHateoas request) {
		Link linkToAllRequests = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(RequestController.class)
						.getRequests(null))
				.withRel("requests");
		request.add(linkToAllRequests);
	}
}