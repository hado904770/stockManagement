package com.doan.stockmanagement.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doan.stockmanagement.common.CommonConstants;
import com.doan.stockmanagement.common.ResponseApi;
import com.doan.stockmanagement.dtos.GoodsCommonNoteDTO;
import com.doan.stockmanagement.entities.GoodsReceiptNote;
import com.doan.stockmanagement.service.GoodsReceiptNoteService;

@RequestMapping(value = CommonConstants.DOMAIN_GOODS_RECEIPT_NOTE)
@RestController
public class GoodsReceiptNoteController {

    @Autowired
    private GoodsReceiptNoteService goodsReceiptNoteService;

    @PostMapping(value = CommonConstants.REQUEST_GET_ALL)
    public ResponseApi<List<GoodsCommonNoteDTO>> getGoodsReceiptNote() {
        return goodsReceiptNoteService.getGoodsReceiptNote();
    }

    @PostMapping(value = CommonConstants.REQUEST_GET_ALL + "/{id}")
    public ResponseApi<GoodsCommonNoteDTO> getGoodsCommonNoteDTOById(@PathVariable(name = "id") Integer id) {
        return goodsReceiptNoteService.getGoodsReceiptNoteById(id);
    }

    @PostMapping(value = CommonConstants.REQUEST_SAVE)
    public ResponseApi<GoodsCommonNoteDTO> saveGoodsReceiptNote(@RequestBody GoodsReceiptNote goodsReceiptNote) {
        return goodsReceiptNoteService.saveGoodsReceiptNote(goodsReceiptNote);
    }

    @PostMapping(value = CommonConstants.REQUEST_DELETE + "/{id}")
    public ResponseApi<Object> deleteGoodsReceiptNote(@PathVariable(name = "id") Integer id) {
        return goodsReceiptNoteService.deleteGoodsReceiptNote(id);
    }

}
