package com.doan.stockmanagement.service;

import java.util.List;

import com.doan.stockmanagement.common.ResponseApi;
import com.doan.stockmanagement.dtos.GoodsCommonNoteDTO;
import com.doan.stockmanagement.entities.GoodsReceiptNote;

public interface GoodsReceiptNoteService {

    ResponseApi<List<GoodsCommonNoteDTO>> getGoodsReceiptNote();
    
    ResponseApi<GoodsCommonNoteDTO> getGoodsReceiptNoteById(Integer id);

    ResponseApi<GoodsCommonNoteDTO> saveGoodsReceiptNote(GoodsReceiptNote goodsReceiptNote);

    ResponseApi<Object> deleteGoodsReceiptNote(Integer id);

}
