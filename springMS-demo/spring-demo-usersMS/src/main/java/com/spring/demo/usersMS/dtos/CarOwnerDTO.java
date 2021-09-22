package com.spring.demo.usersMS.dtos;

import com.spring.demo.usersMS.models.CarOwner;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CarOwnerDTO {

  private long idNumber;
  private long carId;
  private boolean isActive;

  public static CarOwnerDTO getOwnerDTOfromModel(CarOwner ownerModel) {
    return CarOwnerDTO.builder()
        .idNumber(ownerModel.getIdNumber())
        .carId(ownerModel.getCarId())
        .isActive(ownerModel.isActive())
        .build();
  }
}
