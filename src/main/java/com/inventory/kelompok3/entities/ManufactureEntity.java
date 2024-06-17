package com.inventory.kelompok3.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_manufacture")
public class ManufactureEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long manuId;
  
    @Column(nullable = false)
    private Long groupId;
  
    @Column(nullable = false, length = 255)
    private String manuName;
  
    // ManyToOne relationship with TbDeviceGroup
    @ManyToOne
    @JoinColumn(name = "groupId", nullable = false, insertable = false, updatable = false)
    private DeviceGroupEntity group;
}