package com.inventory.kelompok3.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_devices",uniqueConstraints={@UniqueConstraint(columnNames={"serialNumber"})})
public class DeviceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deviceId;
  
    @Column(nullable = false)
    private Long userId;
  
    @Column(nullable = false)
    private Long modelId;
  
    @Column(nullable = false, length = 100)
    private String serialNumber;
  
    @Column(columnDefinition = "float(6,2)")
    private Float storageCap;
  
    @Column(columnDefinition = "float(6,2)")
    private Float memoryCap;
  
    @Column(length = 100)
    private String processor;

    @Column
    private Integer cores;
  
    @Column(nullable = false, length = 100)
    private String location;
  
    @Column
    private Integer rackNumber;
  
    @Column(nullable = false, length = 255)
    private String hostname;
  
    // ManyToOne relationship with TbUser
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false, insertable = false, updatable = false)
    private UserEntity user;
  
    // ManyToOne relationship with TbDevModel
    @ManyToOne
    @JoinColumn(name = "modelId", nullable = false, insertable = false, updatable = false)
    private DevModelEntity model;
}