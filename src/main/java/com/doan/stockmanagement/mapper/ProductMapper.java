package com.doan.stockmanagement.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.doan.stockmanagement.entity.Product;
import com.doan.stockmanagement.entity.dto.ProductDTO;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mappings({ @Mapping(target = "productStatusDTO", source = "product.productStatus") })
    ProductDTO productToProductDTO(Product product);

    List<ProductDTO> toFindAllProductDTO(List<Product> product);

}
