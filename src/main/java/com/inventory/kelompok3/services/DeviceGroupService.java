package com.inventory.kelompok3.services;

import com.inventory.kelompok3.dto.reponse.BaseResponse;
import com.inventory.kelompok3.entities.DeviceGroupEntity;

public interface DeviceGroupService {

  BaseResponse save(DeviceGroupEntity request);

  BaseResponse findAll();

  BaseResponse findById(Long groupId);

  BaseResponse update(DeviceGroupEntity request);

  BaseResponse deleteById(Long groupId);
}
