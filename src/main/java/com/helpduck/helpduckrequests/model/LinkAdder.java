package com.helpduck.helpduckrequests.model;

import java.util.List;

import org.springframework.data.domain.Page;

public interface LinkAdder<T> {
	public void addLink(Page<T> list);

	public void addLink(T object);
}