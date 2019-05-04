package com.doan.stockmanagement.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.doan.stockmanagement.dtos.ProductDTO;
import com.doan.stockmanagement.entities.Product;

@Mapper
public interface ProductMapper {

    @Mappings({
        @Mapping(target="code", source="product.code"),
        @Mapping(target="name", source="product.name"),
        @Mapping(target="mfg", source="product.mfg"),
        @Mapping(target="exp", source="product.exp"),
        @Mapping(target="warehouse", source="product.warehouse"),
        @Mapping(target="provider", source="product.provider"),
        @Mapping(target="customer", source="product.customer"),
        @Mapping(target="goodsReceiptNote", source="product.goodsReceiptNote"),
        @Mapping(target="goodsDeliveryNote", source="product.goodsDeliveryNote")
     })
    ProductDTO toProductDTO(Product product);

    List<ProductDTO> toProductDTOs(List<Product> product);

}
