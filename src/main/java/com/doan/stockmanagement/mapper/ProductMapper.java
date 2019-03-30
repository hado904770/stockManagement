package com.doan.stockmanagement.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.doan.stockmanagement.entity.Product;
import com.doan.stockmanagement.entity.ProductStatus;
import com.doan.stockmanagement.entity.dto.ProductDTO;
import com.doan.stockmanagement.entity.dto.ProductStatusDTO;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    List<ProductDTO> toFindAllProductDTO(List<Product> products);

    ProductDTO toSaveProductDTO(Product product);

}
