package com.spring.demo.usersMS.repos;

import com.spring.demo.usersMS.models.CarOwner;
import java.util.Random;

public class CarOwnerRepo {

  public CarOwner getCarOwnerById(long ownerId) {
    CarOwner carOwner = new CarOwner();
    carOwner.setCarId(ownerId);
    carOwner.setIdNumber(new Random(1000).nextLong());
    return carOwner;
  }
}
