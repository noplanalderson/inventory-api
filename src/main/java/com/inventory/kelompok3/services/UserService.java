package com.inventory.kelompok3.services;

import org.springframework.stereotype.Service;
import com.inventory.kelompok3.dto.reponse.BaseResponse;
import com.inventory.kelompok3.entities.UserEntity;

@Service
public interface UserService {
  BaseResponse save(UserEntity request);

  BaseResponse findAll();

  BaseResponse findById(Long userId);

  BaseResponse update(UserEntity request);

  BaseResponse deleteById(Long userId);
}
