package com.doan.stockmanagement.service;

import java.util.List;

import com.doan.stockmanagement.common.ResponseApi;
import com.doan.stockmanagement.entities.GoodsDeliveryNote;

public interface GoodsDeliveryNoteService {
    
    ResponseApi<List<GoodsDeliveryNote>> getGoodsDeliveryNote();

    ResponseApi<GoodsDeliveryNote> saveGoodsDeliveryNote(GoodsDeliveryNote goodsDeliveryNote);

    ResponseApi<Object> deleteGoodsDeliveryNote(Integer id);
    
}