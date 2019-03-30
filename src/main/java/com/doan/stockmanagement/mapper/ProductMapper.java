package com.doan.stockmanagement.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.doan.stockmanagement.entity.Product;
import com.doan.stockmanagement.entity.dto.ProductDTO;

@Mapper
public interface ProductMapper {

    // ProductMapper MAPPER = Mappers.getMapper(ProductMapper.class);

    List<ProductDTO> toFindAllProductDTO(List<Product> products);

    ProductDTO toInsertProductDTO(Product product);

    ProductDTO toUpdateProductDTO(Product product);

}
