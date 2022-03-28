package com.helpduck.helpduckrequests.model;

import com.helpduck.helpduckrequests.entity.Request;

public class RequestUpdater {
	private NullStringVerifier verifier = new NullStringVerifier();

	public void update(Request request, Request updatedRequest) {
		if (!verifier.verify(updatedRequest.getTitle())) {
			request.setTitle(updatedRequest.getTitle());
		}

		if (!verifier.verify(updatedRequest.getDescription())) {
			request.setDescription(updatedRequest.getDescription());
		}
	}
}
