package com.doan.stockmanagement.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.doan.stockmanagement.dtos.ProductDTO;
import com.doan.stockmanagement.entities.Product;

@Mapper
public interface ProductMapper {

    List<ProductDTO> toProductDTOs(List<Product> product);

}
