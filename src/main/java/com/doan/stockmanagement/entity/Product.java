package com.doan.stockmanagement.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "product")
public class Product {

    private Integer id;
    private String name;
    private String unit;

}
