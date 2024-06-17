package com.inventory.kelompok3.services;

import org.springframework.stereotype.Service;
import com.inventory.kelompok3.dto.reponse.BaseResponse;
import com.inventory.kelompok3.entities.ManufactureEntity;

@Service
public interface ManufactureService {

    BaseResponse save(ManufactureEntity request);

    BaseResponse findAll();

    BaseResponse findById(Long manuId);

    BaseResponse update(ManufactureEntity request);

    BaseResponse deleteById(Long manuId);
}
