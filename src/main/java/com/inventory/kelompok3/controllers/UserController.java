package com.inventory.kelompok3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import com.inventory.kelompok3.dto.reponse.BaseResponse;
import com.inventory.kelompok3.entities.UserEntity;
import com.inventory.kelompok3.impl.UserImpl;

@RestController
@RequestMapping("/users")
@Api(value = "Sistem Informasi dan Manajemen Data Center")
@OpenAPIDefinition(info = @Info(title = "SIMDC", version = "1.0", description = "Sistem Informasi dan Manajemen Data Center"))
@SecurityScheme(name = "restapi_simdc", scheme = "basic", type = SecuritySchemeType.HTTP)
public class UserController {

    @Autowired
    private UserImpl userService;

    @PostMapping
    @ApiOperation(value = "Simpan User", response = BaseResponse.class)
    public BaseResponse save(@ModelAttribute UserEntity request) {
        return userService.save(request);
    }

    @GetMapping
    @ApiOperation(value = "Semua User", response = BaseResponse.class)
    public BaseResponse findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "User Berdasarkan ID", response = BaseResponse.class)
    public BaseResponse findById(@PathVariable("id") Long id) {
        return userService.findById(id);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update Data User", response = BaseResponse.class)
    public BaseResponse update(@PathVariable("id") Long userId, @ModelAttribute UserEntity request) {
        request.setUserId(userId);
        return userService.update(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Hapus User", response = BaseResponse.class)
    public BaseResponse deleteById(@PathVariable("id") Long id) {
        return userService.deleteById(id);
    }

}