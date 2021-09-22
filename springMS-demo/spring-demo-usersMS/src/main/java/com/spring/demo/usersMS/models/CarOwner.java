package com.spring.demo.usersMS.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarOwner {

  private long idNumber;
  private long carId;
  private boolean isActive;
}
