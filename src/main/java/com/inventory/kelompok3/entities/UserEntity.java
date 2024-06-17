package com.inventory.kelompok3.entities;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@EnableJpaRepositories 
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="userId")
    private Long userId;
  
    @Column(nullable = false, length = 50)
    private String userName;
  
    @Column(nullable = false, length = 255)
    private String userPassword;
  
    @Column(nullable = false, length = 255)
    private String userPicture;
  
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserLevel userLevel;
  
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserStatus userStatus;
  
    public enum UserLevel {
      admin, user
    }
  
    public enum UserStatus {
      _0, _1
    }
}