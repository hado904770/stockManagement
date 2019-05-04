package com.doan.stockmanagement.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.doan.stockmanagement.common.CommonEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "PRODUCT")
public class Product extends CommonEntity {

    @NotNull
    @Column(name = "CODE")
    private String code;

    @NotNull
    @Column(name = "NAME")
    private String name;
    
    @NotNull
    @JsonFormat(shape = Shape.STRING, pattern = "dd/MM/yyyy")
    @Column(name = "MFG")
    private LocalDate mfg; // Manufacturing date

    @NotNull
    @JsonFormat(shape = Shape.STRING, pattern = "dd/MM/yyyy")
    @Column(name = "EXP")
    private LocalDate exp; // Expiry date

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "WAREHOUSE_ID")
    private Warehouse warehouse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROVIDER_ID")
    private Provider provider;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GOODS_RECEIPT_NOTE_ID")
    private GoodsReceiptNote goodsReceiptNote;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GOODS_DELIVERY_NOTE")
    private GoodsDeliveryNote goodsDeliveryNote;
}
