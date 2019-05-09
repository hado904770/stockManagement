package com.doan.stockmanagement.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.doan.stockmanagement.dtos.GoodsCommonNoteDTO;
import com.doan.stockmanagement.entities.GoodsReceiptNote;

@Mapper
public interface GoodsReceiptNoteMapper {
    
    @Mappings({
        @Mapping(target="content", source="goodsReceiptNote.content"),
        @Mapping(target="products", source="goodsReceiptNote.products")
     })
    GoodsCommonNoteDTO toGoodsCommonNoteDTO(GoodsReceiptNote goodsReceiptNote);
    
    List<GoodsCommonNoteDTO> toGoodsCommonNoteDTOs(List<GoodsReceiptNote> goodsReceiptNote);
}
