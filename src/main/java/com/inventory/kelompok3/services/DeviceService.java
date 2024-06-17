package com.inventory.kelompok3.services;

import org.springframework.stereotype.Service;
import com.inventory.kelompok3.dto.reponse.BaseResponse;
import com.inventory.kelompok3.entities.DeviceEntity;

@Service
public interface DeviceService {

  BaseResponse save(DeviceEntity request);

  BaseResponse findAll();

  BaseResponse findById(Long deviceId);

  BaseResponse update(DeviceEntity request);

  BaseResponse deleteById(Long deviceId);

  BaseResponse findDeviceByGroup(Long groupId);
}
