package com.doan.stockmanagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @Column(name = "unit")
    private String unit;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "unit_price")
    private Float unitPrice;

    @Column(name = "into_money")
    private Double intoMoney;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_status_id")
    private ProductStatus product_status_id;

}
