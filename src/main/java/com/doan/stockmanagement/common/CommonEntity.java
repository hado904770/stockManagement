package com.doan.stockmanagement.common;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@MappedSuperclass
public class CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    
    @CreationTimestamp
    @JsonFormat(shape = Shape.STRING, pattern = "dd/MM/yyyy")
    @Column(name = "CREATED_TIME")
    private LocalDate createdTime;

    @UpdateTimestamp
    @JsonFormat(shape = Shape.STRING, pattern = "dd/MM/yyyy")
    @Column(name = "UPDATED_TIME")
    private LocalDate updatedTime;

}
