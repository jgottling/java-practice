package com.spring.demo.usersMS.services;

import com.spring.demo.usersMS.dtos.CarOwnerDTO;
import org.springframework.stereotype.Service;

@Service
public interface CarOwnerService {

  public CarOwnerDTO getCarOwnerById(long id);
}
