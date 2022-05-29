package com.helpduck.helpducktickets.model;

import java.time.LocalDateTime;
import java.util.List;

import com.helpduck.helpducktickets.entity.Comment;
import com.helpduck.helpducktickets.entity.Equipment;
import com.helpduck.helpducktickets.entity.Problem;
import com.helpduck.helpducktickets.entity.User;
import com.helpduck.helpducktickets.enums.DepartmentEnum;
import com.helpduck.helpducktickets.enums.PriorityLevelEnum;
import com.helpduck.helpducktickets.enums.StatusEnum;

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

	public boolean verify(Equipment equipment) {
		boolean isNull = true;
		if (!(equipment == null)) {
			isNull = false;
		}
		return isNull;
	}

	public boolean verify(User user) {
		boolean isNull = true;
		if (!(user == null)) {
			isNull = false;
		}
		return isNull;
	}

	public boolean verify(Comment comment) {
		boolean isNull = true;
		if (!(comment == null)) {
			isNull = false;
		}
		return isNull;
	}

	public boolean verify(List<String> list) {
		boolean isNull = true;
		if (!(list == null)) {
			isNull = false;
		}
		return isNull;
	}

	public boolean verify(LocalDateTime dateTime) {
		boolean isNull = true;
		if (!(dateTime == null)) {
			isNull = false;
		}
		return isNull;
	}

	public boolean verify(StatusEnum status) {
		boolean isNull = true;
		if (!(status == null)) {
			isNull = false;
		}
		return isNull;
	}

	public boolean verify(PriorityLevelEnum priority) {
		boolean isNull = true;
		if (!(priority == null)) {
			isNull = false;
		}
		return isNull;
	}

	public boolean verify(DepartmentEnum department) {
		boolean isNull = true;
		if (!(department == null)) {
			isNull = false;
		}
		return isNull;
	}

	public boolean verify(Problem problem) {
		boolean isNull = true;
		if (!(problem == null)) {
			isNull = false;
		}
		return isNull;
	}
}