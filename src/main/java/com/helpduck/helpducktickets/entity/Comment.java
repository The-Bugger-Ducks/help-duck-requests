package com.helpduck.helpducktickets.entity;

import lombok.Data;

@Data
public class Comment {
  User ownerComment;
  String comment;
}
