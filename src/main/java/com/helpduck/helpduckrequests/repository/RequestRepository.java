package com.helpduck.helpduckrequests.repository;

import com.helpduck.helpduckrequests.entity.Request;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RequestRepository extends MongoRepository<Request, String> {
}