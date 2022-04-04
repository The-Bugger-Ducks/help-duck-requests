package com.helpduck.helpducktickets.model;

public class NullStringVerifier {

	public boolean verify(String data) {
		boolean isNull = true;
		if (!(data == null)) {
			if (!data.isBlank()) {
				isNull = false;
			}
		}
		return isNull;
	}
}