package com.helpduck.helpduckrequests.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.bson.codecs.jsr310.LocalDateTimeCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.helpduck.helpduckrequests.entity.Request;
import com.helpduck.helpduckrequests.model.RequestLinkAdder;
import com.helpduck.helpduckrequests.model.RequestUpdater;
import com.helpduck.helpduckrequests.repository.RequestRepository;

@RestController
@RequestMapping("/requests")
public class RequestController {
	@Autowired
	private RequestRepository repository;
	@Autowired
	private RequestLinkAdder linkAdder;

	MongoTemplate mongoTemplate;

	 @GetMapping("/")
	 public ResponseEntity<List<Request>> getRequests() {
	 	List<Request> requests = repository.findAll();
	 	ResponseEntity<List<Request>> response = new ResponseEntity<>(HttpStatus.NOT_FOUND);

	 	if (!requests.isEmpty()) {
	 		response = new ResponseEntity<List<Request>>(requests, HttpStatus.FOUND);
	 	}
	 	return response;
	 }

	 @GetMapping("/{id}")
	 public ResponseEntity<Request> getRequest(@PathVariable String id) {
	 	ResponseEntity<Request> response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		 
	 	Optional<Request> requestOptional = repository.findById(id);
	 	if (!requestOptional.isEmpty()) {
	 		Request request = requestOptional.get();
	 		linkAdder.addLink(request);
	 		response = new ResponseEntity<Request>(request, HttpStatus.FOUND);
	 	}
	 	return response;
	 }

	@PostMapping("/create")
	public ResponseEntity<Request> createRequest(@RequestBody Request request) {
		HttpStatus status = HttpStatus.CONFLICT;

		if (request.getId() == null) {
			request.setRegistrationDate(LocalDateTime.now());
			Request requestInserted = repository.insert(request);
			status = HttpStatus.CREATED;
			return new ResponseEntity<Request>(requestInserted, status);
		}
		return new ResponseEntity<Request>(status);
	}

	 @PutMapping("/update")
	 public ResponseEntity<HttpStatus> updateRequest(@RequestBody Request updatedRequest) {
	 	Optional<Request> requestOptional = repository.findById(updatedRequest.getId());
	 	HttpStatus status = HttpStatus.BAD_REQUEST;

	 	if (!requestOptional.isEmpty()) {
	 		Request request = requestOptional.get();
	 		RequestUpdater updater = new RequestUpdater();
	 		updater.update(request, updatedRequest);
	 		repository.save(request);
	 		status = HttpStatus.OK;
	 	}
	 	return new ResponseEntity<HttpStatus>(status);
	 }

	 @DeleteMapping("/delete")
	 public ResponseEntity<HttpStatus> deleteRequest(@RequestBody Request requestToDelete) {
	 	// se só precisa do ID pra excluir, pq pedir um tipo "request"?
	 	Optional<Request> requestOptional = repository.findById(requestToDelete.getId());
	 	HttpStatus status = HttpStatus.BAD_REQUEST;

	 	if (!requestOptional.isEmpty()) {
	 		repository.deleteById(requestToDelete.getId());
	 		status = HttpStatus.OK;
	 	}
	 	return new ResponseEntity<HttpStatus>(status);
	 }
}