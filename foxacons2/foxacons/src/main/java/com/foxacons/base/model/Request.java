package com.foxacons.base.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Request {
   private String adminEmail;
   private String adminPassword;
}
