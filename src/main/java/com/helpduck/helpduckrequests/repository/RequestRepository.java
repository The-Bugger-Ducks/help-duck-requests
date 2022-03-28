package com.helpduck.helpduckrequests.repository;

import com.helpduck.helpduckrequests.entity.Request;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request, Long> {
}