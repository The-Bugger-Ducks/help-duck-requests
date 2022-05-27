package com.helpduck.helpducktickets.entity;

import com.helpduck.helpducktickets.enums.RequestingDepartmentEnum;
import com.helpduck.helpducktickets.enums.RoleEnum;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document()
public class User {
  @Id
  private String id;

  private String firstName;
  private String lastName;

  private String email;
  private String password;

  private RoleEnum role;
  private RequestingDepartmentEnum requestingDepartment;
}
