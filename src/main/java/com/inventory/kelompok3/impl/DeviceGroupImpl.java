package com.inventory.kelompok3.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.kelompok3.controllers.DeviceGroupController;
import com.inventory.kelompok3.dto.reponse.BaseResponse;
import com.inventory.kelompok3.entities.DeviceGroupEntity;
import com.inventory.kelompok3.repositories.DeviceGroupRepository;
import com.inventory.kelompok3.services.DeviceGroupService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class DeviceGroupImpl implements DeviceGroupService {

    @Autowired
    private DeviceGroupRepository devGroupRepository;

    private static final Logger logger = LoggerFactory.getLogger(DeviceGroupController.class);
    
    @PersistenceContext
    private EntityManager entityManager;

    private String msg;

    private Boolean status = false;

    private Object returnObject = null;

    @Override
    public BaseResponse save(DeviceGroupEntity request) {
        try {
            DeviceGroupEntity newData = devGroupRepository.save(request);
            this.status = true;
            this.msg = "Grup device disimpan!";
            this.returnObject = newData;
            logger.info(this.msg);
        } catch (Exception e) {
            this.status = false;
            this.msg = "Gagal menyimpan grup! "+e.toString();
            logger.error(this.msg);
        }
        
        return new BaseResponse(this.status, this.msg, this.returnObject);
    }

    @Override
    public BaseResponse findAll() {
        List<DeviceGroupEntity> groupLists = devGroupRepository.findAll();

        return new BaseResponse(true, "Data Berhasil Ditemukan", groupLists);
    }

    @Override
    public BaseResponse findById(Long id) {
        DeviceGroupEntity result = devGroupRepository.findById(id).orElse(null);
        if(result != null) {
            this.status = true;
            this.msg = "Grup device ditemukan!";
            logger.info(this.msg);
        } 
        else
        {
            this.status = false;
            this.msg = "Grup device tidak ada!";
            logger.error(this.msg);
        }
        this.returnObject = result;
        return new BaseResponse(this.status, this.msg, this.returnObject);
    }

    @Override
    public BaseResponse update(DeviceGroupEntity request) {
        try {
            DeviceGroupEntity updateData = devGroupRepository.save(request);
            this.status = true;
            this.msg = "Grup device disimpan!";
            this.returnObject = updateData;
            logger.info(this.msg);
        } catch (Exception e) {
            this.status = false;
            this.msg = "Gagal menyimpan grup! "+e.toString();
            logger.error(this.msg);
        }
        return new BaseResponse(this.status, this.msg, this.returnObject);
    }

    @Override
    public BaseResponse deleteById(Long id) {
        try {
            devGroupRepository.deleteById(id);
            this.msg = "Grup device dihapus";
            this.status = true;
            logger.info(this.msg);
        } catch (Exception e) {
            this.status = false;
            this.msg = "Gagal menghapus grup! "+e.toString();
            logger.error(this.msg);
        }
        return new BaseResponse(this.status, this.msg, null);
    }
}
