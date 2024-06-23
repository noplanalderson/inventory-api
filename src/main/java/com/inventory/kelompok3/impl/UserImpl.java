package com.inventory.kelompok3.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.kelompok3.controllers.UserController;
import com.inventory.kelompok3.dto.reponse.BaseResponse;
import com.inventory.kelompok3.entities.UserEntity;
import com.inventory.kelompok3.repositories.UserRepository;
import com.inventory.kelompok3.services.UserService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Service
public class UserImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    
    @PersistenceContext
    private EntityManager entityManager;

    private String msg;

    private Boolean status = false;

    private Object returnObject = null;

    @Override
    public BaseResponse save(UserEntity request) {
        try {
            UserEntity newData = userRepository.save(request);
            this.status = true;
            this.msg = "User disimpan!";
            this.returnObject = newData;
            logger.info(this.msg);
        } catch (Exception e) {
            this.msg = "Gagal menyimpan user! "+e.toString();
            logger.error(this.msg);
        }
        
        return new BaseResponse(this.status, this.msg, this.returnObject);
    }

    @Override
    public BaseResponse findAll() {
        List<UserEntity> deviceLists = userRepository.findAll();

        return new BaseResponse(true, "Data Berhasil Ditemukan", deviceLists);
    }

    @Override
    public BaseResponse findById(Long id) {
        UserEntity result = userRepository.findById(id).orElse(null);
        if(result != null) {
            this.status = true;
            this.msg = "User ditemukan!";
            logger.info(this.msg);
        } 
        else
        {
            this.msg = "User tidak ditemukan!";
            logger.error(this.msg);
        }
        this.returnObject = result;
        return new BaseResponse(this.status, this.msg, this.returnObject);
    }

    @Override
    public BaseResponse update(UserEntity request) {
        try {
            UserEntity updateData = userRepository.save(request);
            this.status = true;
            this.msg = "User disimpan!";
            this.returnObject = updateData;
            logger.info(this.msg);
        } catch (Exception e) {
            this.msg = "Gagal menyimpan user! "+e.toString();
            logger.error(this.msg);
        }
        return new BaseResponse(this.status, this.msg, this.returnObject);
    }

    @Override
    public BaseResponse deleteById(Long id) {
        try {
            userRepository.deleteById(id);
            this.msg = "User dihapus";
            this.status = true;
            logger.info(this.msg);
        } catch (Exception e) {
            this.msg = "Gagal menghapus user! "+e.toString();
            logger.error(this.msg);
        }
        return new BaseResponse(this.status, this.msg, null);
    }

    public BaseResponse findByUsername(String userName) {
        String query = "SELECT * FROM tb_user WHERE user_name = :userName AND userStatus = '_1'";
        Query nativeQuery = entityManager.createNativeQuery(query, UserEntity.class);
        nativeQuery.setParameter("userName", userName);
        try {
            Object users = nativeQuery.getSingleResult();
            this.status = true;
            this.msg = "Username ditemukan!";
            this.returnObject = users;
            logger.info(this.msg);
        } catch (Exception e) {
            this.returnObject = null;
            this.msg = "Username tidak terdaftar. "+toString();
            logger.error(this.msg);
        }
        return new BaseResponse(this.status, this.msg, this.returnObject);
    }
}
