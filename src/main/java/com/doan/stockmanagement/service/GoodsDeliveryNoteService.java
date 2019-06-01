package com.doan.stockmanagement.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.doan.stockmanagement.common.Result;
import com.doan.stockmanagement.dtos.GoodsCommonNoteDTO;
import com.doan.stockmanagement.entities.GoodsDeliveryNote;

public interface GoodsDeliveryNoteService {

    ResponseEntity<Result<List<GoodsCommonNoteDTO>>> getGoodsDeliveryNote();

    ResponseEntity<Result<Object>> saveGoodsDeliveryNote(GoodsDeliveryNote goodsDeliveryNote);

    ResponseEntity<Result<Object>> deleteGoodsDeliveryNote(Integer id);

}
