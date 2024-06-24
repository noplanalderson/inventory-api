package com.inventory.kelompok3.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.kelompok3.controllers.DevModelController;
import com.inventory.kelompok3.dto.reponse.BaseResponse;
import com.inventory.kelompok3.entities.DevModelEntity;
import com.inventory.kelompok3.repositories.DevModelRepository;
import com.inventory.kelompok3.services.DevModelService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class DevModelImpl implements DevModelService {
    @Autowired
    private DevModelRepository devModelRepository;

    private static final Logger logger = LoggerFactory.getLogger(DevModelController.class);
    
    @PersistenceContext
    private EntityManager entityManager;

    private String msg;

    private Boolean status = false;

    private Object returnObject = null;

    @Override
    public BaseResponse save(DevModelEntity request) {
        try {
            DevModelEntity newData = devModelRepository.save(request);
            this.status = true;
            this.msg = "Model device disimpan!";
            this.returnObject = newData;
            logger.info(this.msg);
        } catch (Exception e) {
            this.status = false;
            this.msg = "Gagal menyimpan model device! "+e.toString();
            logger.error(this.msg);
        }
        
        return new BaseResponse(this.status, this.msg, this.returnObject);
    }

    @Override
    public BaseResponse findAll() {
        List<DevModelEntity> modelLists = devModelRepository.findAll();

        return new BaseResponse(true, "Data Berhasil Ditemukan", modelLists);
    }

    @Override
    public BaseResponse findById(Long id) {
        DevModelEntity result = devModelRepository.findById(id).orElse(null);
        if(result != null) {
            this.status = true;
            this.msg = "Model device ditemukan!";
            logger.info(this.msg);
        } 
        else
        {
            this.status = false;
            this.msg = "Model device tidak ditemukan!";
            logger.error(this.msg);
        }
        this.returnObject = result;
        return new BaseResponse(this.status, this.msg, this.returnObject);
    }

    @Override
    public BaseResponse update(DevModelEntity request) {
        try {
            DevModelEntity updateData = devModelRepository.save(request);
            this.status = true;
            this.msg = "Model device disimpan!";
            this.returnObject = updateData;
            logger.info(this.msg);
        } catch (Exception e) {
            this.status = false;
            this.msg = "Gagal menyimpan model device! "+e.toString();
            logger.error(this.msg);
        }
        return new BaseResponse(this.status, this.msg, this.returnObject);
    }

    @Override
    public BaseResponse deleteById(Long id) {
        try {
            devModelRepository.deleteById(id);
            this.msg = "Model device dihapus";
            this.status = true;
            logger.info(this.msg);
        } catch (Exception e) {
            this.status = false;
            this.msg = "Gagal menghapus model device! "+e.toString();
            logger.error(this.msg);
        }
        return new BaseResponse(this.status, this.msg, null);
    }
}
