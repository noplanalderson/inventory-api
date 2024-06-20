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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

import com.inventory.kelompok3.dto.reponse.BaseResponse;
import com.inventory.kelompok3.entities.DeviceEntity;
import com.inventory.kelompok3.impl.DeviceImpl;

@RestController
@RequestMapping("/devices")
@Api(value = "Sistem Informasi dan Manajemen Data Center")
@OpenAPIDefinition(info = @Info(title = "SIMDC", version = "1.0", description = "Sistem Informasi dan Manajemen Data Center"))
@SecurityScheme(name = "restapi_simdc", scheme = "basic", type = SecuritySchemeType.HTTP)
public class DeviceController {

    @Autowired
    private DeviceImpl deviceService;

    @Operation(security = @SecurityRequirement(name = "restapi_simdc"))
    @PostMapping
    @ApiOperation(value = "Simpan Device", response = BaseResponse.class)
    public BaseResponse save(@ModelAttribute DeviceEntity request) {
        return deviceService.save(request);
    }

    @Operation(security = @SecurityRequirement(name = "restapi_simdc"))
    @GetMapping
    @ApiOperation(value = "Semua Device", response = BaseResponse.class)
    public BaseResponse findAll() {
        return deviceService.findAll();
    }

    @Operation(security = @SecurityRequirement(name = "restapi_simdc"))
    @GetMapping("/group/{id}")
    @ApiOperation(value = "Device Berdasarkan Grup", response = BaseResponse.class)
    public BaseResponse findDeviceByGroup(@PathVariable("id") Long groupId)
    {
        return deviceService.findDeviceByGroup(groupId);
    }

    @Operation(security = @SecurityRequirement(name = "restapi_simdc"))
    @GetMapping("/{id}")
    @ApiOperation(value = "Device Berdasarkan ID", response = BaseResponse.class)
    public BaseResponse findById(@PathVariable("id") Long id) {
        return deviceService.findById(id);
    }

    @Operation(security = @SecurityRequirement(name = "restapi_simdc"))
    @PutMapping("/{id}")
    @ApiOperation(value = "Update Data Device", response = BaseResponse.class)
    public BaseResponse update(@PathVariable("id") Long deviceId, @ModelAttribute DeviceEntity request) {
        request.setDeviceId(deviceId);
        return deviceService.update(request);
    }

    @Operation(security = @SecurityRequirement(name = "restapi_simdc"))
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Hapus Device", response = BaseResponse.class)
    public BaseResponse deleteById(@PathVariable("id") Long id) {
        return deviceService.deleteById(id);
    }

}