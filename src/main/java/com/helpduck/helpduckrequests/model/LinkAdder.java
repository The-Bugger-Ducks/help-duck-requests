package com.helpduck.helpduckrequests.model;

import java.util.List;

public interface LinkAdder<T> {
	public void addLink(List<T> list);

	public void addLink(T object);
}