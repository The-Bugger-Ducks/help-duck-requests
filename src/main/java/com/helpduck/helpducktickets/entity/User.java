package com.helpduck.helpducktickets.entity;

import lombok.Data;

@Data
public class User {
  private String id;
  private String firstName;
  private String lastName;
  private String email;
}
