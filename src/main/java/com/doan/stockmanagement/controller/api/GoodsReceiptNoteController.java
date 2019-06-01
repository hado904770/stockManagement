package com.doan.stockmanagement.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doan.stockmanagement.common.CommonConstants;
import com.doan.stockmanagement.common.Result;
import com.doan.stockmanagement.dtos.GoodsCommonNoteDTO;
import com.doan.stockmanagement.entities.GoodsReceiptNote;
import com.doan.stockmanagement.service.GoodsReceiptNoteService;

@RequestMapping(value = CommonConstants.DOMAIN_API + CommonConstants.DOMAIN_GOODS_RECEIPT_NOTE)
@RestController
public class GoodsReceiptNoteController {

    @Autowired
    private GoodsReceiptNoteService goodsReceiptNoteService;

    @PostMapping(value = CommonConstants.REQUEST_FILTER)
    public ResponseEntity<Result<List<GoodsCommonNoteDTO>>> getGoodsReceiptNote() {
        return goodsReceiptNoteService.getGoodsReceiptNote();
    }

    @PostMapping(value = CommonConstants.REQUEST_SAVE)
    public ResponseEntity<Result<Object>> saveGoodsReceiptNote(@RequestBody GoodsReceiptNote goodsReceiptNote) {
        return goodsReceiptNoteService.saveGoodsReceiptNote(goodsReceiptNote);
    }

    @PostMapping(value = CommonConstants.REQUEST_DELETE)
    public ResponseEntity<Result<Object>> deleteGoodsReceiptNote(@RequestBody GoodsReceiptNote goodsReceiptNote) {
        return goodsReceiptNoteService.deleteGoodsReceiptNote(goodsReceiptNote.getId());
    }

}
