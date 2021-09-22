package com.spring.demo.usersMS.controllers;

import com.spring.demo.usersMS.dtos.CarOwnerDTO;

public interface CarOwnerController {

  public CarOwnerDTO getOwnerById(long ownerId);
}
