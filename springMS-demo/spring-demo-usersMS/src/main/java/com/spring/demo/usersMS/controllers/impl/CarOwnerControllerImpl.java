package com.spring.demo.usersMS.controllers.impl;

import com.spring.demo.usersMS.controllers.CarOwnerController;
import com.spring.demo.usersMS.dtos.CarOwnerDTO;
import com.spring.demo.usersMS.services.CarOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/owners")
public class CarOwnerControllerImpl implements CarOwnerController {
  @Autowired CarOwnerService carOwnerService;

  @Override
  @GetMapping(value = "/{ownerId}", produces = "application/json")
  public CarOwnerDTO getOwnerById(@PathVariable long ownerId) {
    return carOwnerService.getCarOwnerById(ownerId);
  }
}
