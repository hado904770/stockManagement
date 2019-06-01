package com.doan.stockmanagement.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.doan.stockmanagement.common.Result;
import com.doan.stockmanagement.dtos.GoodsCommonNoteDTO;
import com.doan.stockmanagement.entities.GoodsReceiptNote;

public interface GoodsReceiptNoteService {

    ResponseEntity<Result<List<GoodsCommonNoteDTO>>> getGoodsReceiptNote();

    ResponseEntity<Result<Object>> saveGoodsReceiptNote(GoodsReceiptNote goodsReceiptNote);

    ResponseEntity<Result<Object>> deleteGoodsReceiptNote(Integer id);

}
