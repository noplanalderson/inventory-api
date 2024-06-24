package com.inventory.kelompok3.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.kelompok3.controllers.DeviceController;
import com.inventory.kelompok3.dto.reponse.BaseResponse;
import com.inventory.kelompok3.entities.DeviceEntity;
import com.inventory.kelompok3.repositories.DeviceRepository;
import com.inventory.kelompok3.services.DeviceService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.util.List;

@Service
public class DeviceImpl implements DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    private static final Logger logger = LoggerFactory.getLogger(DeviceController.class);
    
    @PersistenceContext
    private EntityManager entityManager;

    private String msg;

    private Boolean status = false;

    private Object returnObject = null;

    @Override
    public BaseResponse save(DeviceEntity request) {
        try {
            DeviceEntity newData = deviceRepository.save(request);
            this.status = true;
            this.msg = "Device disimpan!";
            this.returnObject = newData;
            logger.info(this.msg);
        } catch (Exception e) {
            this.status = false;
            this.msg = "Gagal menyimpan device! "+e.toString();
            logger.error(this.msg);
        }
        
        return new BaseResponse(this.status, this.msg, this.returnObject);
    }

    @Override
    public BaseResponse findAll() {
        List<DeviceEntity> deviceLists = deviceRepository.findAll();

        return new BaseResponse(true, "Data Berhasil Ditemukan", deviceLists);
    }

    @Override
    public BaseResponse findById(Long id) {
        DeviceEntity result = deviceRepository.findById(id).orElse(null);
        if(result != null) {
            this.status = true;
            this.msg = "Device ditemukan!";
            logger.info(this.msg);
        } 
        else
        {
            this.status = false;
            this.msg = "Device tidak ditemukan!";
            logger.error(this.msg);
        }
        this.returnObject = result;
        return new BaseResponse(this.status, this.msg, this.returnObject);
    }

    @Override
    public BaseResponse update(DeviceEntity request) {
        try {
            DeviceEntity updateData = deviceRepository.save(request);
            this.status = true;
            this.msg = "Device disimpan!";
            this.returnObject = updateData;
            logger.info(this.msg);
        } catch (Exception e) {
            this.status = false;
            this.msg = "Gagal menyimpan device! "+e.toString();
            logger.error(this.msg);
        }
        return new BaseResponse(this.status, this.msg, this.returnObject);
    }

    @Override
    public BaseResponse deleteById(Long id) {
        try {
            deviceRepository.deleteById(id);
            this.msg = "Device dihapus";
            this.status = true;
            logger.info(this.msg);
        } catch (Exception e) {
            this.status = false;
            this.msg = "Gagal menghapus device! "+e.toString();
            logger.error(this.msg);
        }
        return new BaseResponse(this.status, this.msg, null);
    }

    @SuppressWarnings("unchecked")
    @Override
    public BaseResponse findDeviceByGroup(Long groupId) {
        String query = "SELECT a.*, b.model_name, c.manu_name FROM tb_devices a INNER JOIN tb_dev_model b ON a.model_id = b.model_id INNER JOIN tb_manufacture c ON b.manu_id = c.manu_id INNER JOIN tb_device_group d ON c.group_id = d.group_id WHERE c.group_id = :groupId";
        Query nativeQuery = entityManager.createNativeQuery(query, DeviceEntity.class);
        nativeQuery.setParameter("groupId", groupId);
        try {
            List<DeviceEntity> devices = nativeQuery.getResultList();
            this.status = true;
            this.msg = "Device ditemukan!";
            this.returnObject = devices;
            logger.info(this.msg);
        } catch (Exception e) {
            this.status = false;
            this.msg = "Tidak ada device. "+toString();
            logger.error(this.msg);
        }
        return new BaseResponse(this.status, this.msg, this.returnObject);
    }
}

