package com.spring.demo.usersMS.controllers;

import com.spring.demo.usersMS.dtos.CarOwnerDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/owners")
public interface CarOwnerController {

  @GetMapping(value = "/{ownerId}", produces = "application / json")
  public CarOwnerDTO getOwnerById(@PathVariable long ownerId);
}
