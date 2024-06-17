package com.inventory.kelompok3.services;

import org.springframework.stereotype.Service;
import com.inventory.kelompok3.dto.reponse.BaseResponse;
import com.inventory.kelompok3.entities.DevModelEntity;

@Service
public interface DevModelService {
  BaseResponse save(DevModelEntity request);

  BaseResponse findAll();

  BaseResponse findById(Long modelId);

  BaseResponse update(DevModelEntity request);

  BaseResponse deleteById(Long modelId);
}
