package com.inventory.kelompok3.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.kelompok3.controllers.ManufactureController;
import com.inventory.kelompok3.dto.reponse.BaseResponse;
import com.inventory.kelompok3.entities.ManufactureEntity;
import com.inventory.kelompok3.repositories.ManufactureRepository;
import com.inventory.kelompok3.services.ManufactureService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class ManufactureImpl implements ManufactureService {

    @Autowired
    private ManufactureRepository manuRepository;

    private static final Logger logger = LoggerFactory.getLogger(ManufactureController.class);
    
    @PersistenceContext
    private EntityManager entityManager;

    private String msg;

    private Boolean status = false;

    private Object returnObject = null;

    @Override
    public BaseResponse save(ManufactureEntity request) {
        try {
            ManufactureEntity newData = manuRepository.save(request);
            this.status = true;
            this.msg = "Manufaktur disimpan!";
            this.returnObject = newData;
            logger.info(this.msg);
        } catch (Exception e) {
            this.status = false;
            this.msg = "Gagal menyimpan manufaktur! "+e.toString();
            logger.error(this.msg);
        }
        
        return new BaseResponse(this.status, this.msg, this.returnObject);
    }

    @Override
    public BaseResponse findAll() {
        List<ManufactureEntity> manuLists = manuRepository.findAll();

        return new BaseResponse(true, "Data Berhasil Ditemukan", manuLists);
    }

    @Override
    public BaseResponse findById(Long id) {
        ManufactureEntity result = manuRepository.findById(id).orElse(null);
        if(result != null) {
            this.status = true;
            this.msg = "Manufaktur ditemukan!";
            logger.info(this.msg);
        } 
        else
        {
            this.status = false;
            this.msg = "Manufaktur tidak ditemukan!";
            logger.error(this.msg);
        }
        this.returnObject = result;
        return new BaseResponse(this.status, this.msg, this.returnObject);
    }

    @Override
    public BaseResponse update(ManufactureEntity request) {
        try {
            ManufactureEntity updateData = manuRepository.save(request);
            this.status = true;
            this.msg = "Manufaktur disimpan!";
            this.returnObject = updateData;
            logger.info(this.msg);
        } catch (Exception e) {
            this.status = false;
            this.msg = "Gagal menyimpan manufaktur! "+e.toString();
            logger.error(this.msg);
        }
        return new BaseResponse(this.status, this.msg, this.returnObject);
    }

    @Override
    public BaseResponse deleteById(Long id) {
        try {
            manuRepository.deleteById(id);
            this.msg = "Manufaktur dihapus";
            this.status = true;
            logger.info(this.msg);
        } catch (Exception e) {
            this.status = false;
            this.msg = "Gagal menghapus manufaktur! "+e.toString();
            logger.error(this.msg);
        }
        return new BaseResponse(this.status, this.msg, null);
    }
    
}
