package com.spring.demo.usersMS.repos;

import com.spring.demo.usersMS.models.CarOwner;
import java.util.Random;
import org.springframework.stereotype.Repository;

@Repository
public class CarOwnerRepo {

  public CarOwner getCarOwnerById(long ownerId) {
    return new CarOwner(ownerId, new Random(1000).nextLong(), true);
  }
}
