package com.doan.stockmanagement.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.doan.stockmanagement.common.CommonUtils;
import com.doan.stockmanagement.common.Result;
import com.doan.stockmanagement.dtos.GoodsCommonNoteDTO;
import com.doan.stockmanagement.entities.GoodsReceiptNote;
import com.doan.stockmanagement.mapper.GoodsReceiptNoteMapper;
import com.doan.stockmanagement.repository.GoodsReceiptNoteRepository;
import com.doan.stockmanagement.service.GoodsReceiptNoteService;

@Service
public class GoodsReceiptNoteServiceImpl implements GoodsReceiptNoteService {

    private static Logger LOGGER = LoggerFactory.getLogger(GoodsReceiptNoteServiceImpl.class);

    @Autowired
    private GoodsReceiptNoteRepository goodsReceiptNoteRepository;

    @Autowired
    private GoodsReceiptNoteMapper goodsReceiptNoteMapper;

    @Override
    public ResponseEntity<Result<List<GoodsCommonNoteDTO>>> getGoodsReceiptNote() {
        ResponseEntity<Result<List<GoodsCommonNoteDTO>>> responseEntity = new ResponseEntity<>(HttpStatus.PROCESSING);

        try {
            List<GoodsReceiptNote> goodsReceiptNotes = goodsReceiptNoteRepository.findAll();
            List<GoodsCommonNoteDTO> goodsCommonNoteDTOs = goodsReceiptNoteMapper
                    .toGoodsCommonNoteDTOs(goodsReceiptNotes);

            responseEntity = CommonUtils.buildResponse(HttpStatus.OK, HttpStatus.OK.name(), goodsCommonNoteDTOs);
        } catch (Exception e) {
            LOGGER.error("ERROR getGoodsReceiptNote: ", e);
            responseEntity = CommonUtils.buildResponse(HttpStatus.BAD_REQUEST, e.getMessage(), new ArrayList<>());
        }

        return responseEntity;
    }

    @Override
    public ResponseEntity<Result<Object>> saveGoodsReceiptNote(GoodsReceiptNote goodsReceiptNote) {
        ResponseEntity<Result<Object>> responseEntity = new ResponseEntity<>(HttpStatus.PROCESSING);

        try {
            goodsReceiptNoteRepository.save(goodsReceiptNote);
            responseEntity = CommonUtils.buildResponse(HttpStatus.OK, HttpStatus.OK.name(), null);
        } catch (Exception e) {
            LOGGER.error("ERROR getGoodsReceiptNote: ", e);
            responseEntity = CommonUtils.buildResponse(HttpStatus.BAD_REQUEST, e.getMessage(), null);
        }

        return responseEntity;
    }

    @Override
    public ResponseEntity<Result<Object>> deleteGoodsReceiptNote(Integer id) {
        ResponseEntity<Result<Object>> responseEntity = new ResponseEntity<>(HttpStatus.PROCESSING);

        try {
            goodsReceiptNoteRepository.deleteById(id);
            responseEntity = CommonUtils.buildResponse(HttpStatus.OK, HttpStatus.OK.name(), null);
        } catch (Exception e) {
            LOGGER.error("ERROR deleteGoodsReceiptNote: ", e);
            responseEntity = CommonUtils.buildResponse(HttpStatus.BAD_REQUEST, e.getMessage(), null);
        }

        return responseEntity;
    }

}
