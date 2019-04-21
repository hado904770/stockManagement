package com.doan.stockmanagement.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.doan.stockmanagement.common.CommonEntity;

@Entity
@Table(name = "GOODS_DELIVERY_NOTE")
public class GoodsDeliveryNote extends CommonEntity {
    
    @NotNull
    @Column(name = "CONTENT")
    private String content;
    
    @OneToMany(mappedBy = "goodsDeliveryNote", cascade = CascadeType.ALL)
    private List<Product> products;
    
}
