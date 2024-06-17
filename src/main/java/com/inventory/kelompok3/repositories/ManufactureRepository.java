package com.inventory.kelompok3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventory.kelompok3.entities.ManufactureEntity;

@Repository
public interface ManufactureRepository extends JpaRepository<ManufactureEntity, Long> {
}
