package com.inventory.kelompok3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventory.kelompok3.entities.DeviceGroupEntity;

@Repository
public interface DeviceGroupRepository extends JpaRepository<DeviceGroupEntity, Long> {
}
