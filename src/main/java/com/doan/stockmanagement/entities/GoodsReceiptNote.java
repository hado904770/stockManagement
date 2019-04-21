package com.doan.stockmanagement.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.doan.stockmanagement.common.CommonEntity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "GOODS_RECEIPT_NOTE")
public class GoodsReceiptNote extends CommonEntity {
    
    @NotNull
    @Column(name = "CONTENT")
    private String content;
    
    @OneToMany(mappedBy = "goodsReceiptNote", cascade = CascadeType.ALL)
    private List<Product> products;
    
}
