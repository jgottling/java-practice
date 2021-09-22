package com.spring.demo.usersMS.models;

import javax.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class CarOwner {

  private long idNumber;
  private long carId;
  private boolean isActive;
}
