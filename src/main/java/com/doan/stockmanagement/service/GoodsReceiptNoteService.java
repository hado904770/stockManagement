package com.doan.stockmanagement.service;

import java.util.List;

import com.doan.stockmanagement.common.ResponseApi;
import com.doan.stockmanagement.entities.GoodsReceiptNote;

public interface GoodsReceiptNoteService {

    ResponseApi<List<GoodsReceiptNote>> getGoodsReceiptNote();

    ResponseApi<GoodsReceiptNote> saveGoodsReceiptNote(GoodsReceiptNote goodsReceiptNote);

    ResponseApi<Object> deleteGoodsReceiptNote(Integer id);

}
