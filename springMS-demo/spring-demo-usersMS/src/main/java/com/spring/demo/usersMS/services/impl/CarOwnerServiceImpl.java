package com.spring.demo.usersMS.services.impl;

import com.spring.demo.usersMS.dtos.CarOwnerDTO;
import com.spring.demo.usersMS.repos.CarOwnerRepo;
import com.spring.demo.usersMS.services.CarOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarOwnerServiceImpl implements CarOwnerService {

  @Autowired CarOwnerRepo carOwnerRepo;

  @Override
  public CarOwnerDTO getCarOwnerById(long id) {
    return CarOwnerDTO.getOwnerDTOfromModel(carOwnerRepo.getCarOwnerById(id));
  }
}
