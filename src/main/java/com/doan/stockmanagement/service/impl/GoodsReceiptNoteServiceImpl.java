package com.doan.stockmanagement.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.doan.stockmanagement.common.CommonUtils;
import com.doan.stockmanagement.common.ResponseApi;
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
    public ResponseApi<List<GoodsCommonNoteDTO>> getGoodsReceiptNote() {
        ResponseApi<List<GoodsCommonNoteDTO>> responseApi = new ResponseApi<>();

        try {
            List<GoodsReceiptNote> goodsReceiptNotes = goodsReceiptNoteRepository.findAll();
            List<GoodsCommonNoteDTO> goodsCommonNoteDTOs = goodsReceiptNoteMapper.toGoodsCommonNoteDTOs(goodsReceiptNotes);
            
            responseApi = CommonUtils.buildResponse(HttpStatus.OK.value(),
                    HttpStatus.OK.name(),
                    goodsCommonNoteDTOs);
        } catch (Exception e) {
            LOGGER.error("ERROR getGoodsReceiptNote: ", e);
            responseApi = CommonUtils.buildResponse(HttpStatus.BAD_REQUEST.value(),
                    e.getMessage(),
                    new ArrayList<>());
        }

        return responseApi;
    }

    @Override
    public ResponseApi<GoodsCommonNoteDTO> saveGoodsReceiptNote(GoodsReceiptNote goodsReceiptNote) {
        ResponseApi<GoodsCommonNoteDTO> responseApi = new ResponseApi<>();

        try {
            
            GoodsReceiptNote g = goodsReceiptNoteRepository.save(goodsReceiptNote);
            GoodsCommonNoteDTO goodsCommonNoteDTO = goodsReceiptNoteMapper.toGoodsCommonNoteDTO(g);
            
            responseApi = CommonUtils.buildResponse(HttpStatus.OK.value(),
                    HttpStatus.OK.name(),
                    goodsCommonNoteDTO);
        } catch (Exception e) {
            LOGGER.error("ERROR getGoodsReceiptNote: ", e);
            responseApi = CommonUtils.buildResponse(HttpStatus.BAD_REQUEST.value(),
                    e.getMessage(),
                    new GoodsCommonNoteDTO());
        }

        return responseApi;
    }

    @Override
    public ResponseApi<Object> deleteGoodsReceiptNote(Integer id) {
        ResponseApi<Object> responseApi = new ResponseApi<>();

        try {
            goodsReceiptNoteRepository.deleteById(id);
            responseApi = CommonUtils.buildResponse(HttpStatus.OK.value(),
                    HttpStatus.OK.name(),
                    null);
        } catch (Exception e) {
            LOGGER.error("ERROR deleteGoodsReceiptNote: ", e);
            responseApi = CommonUtils.buildResponse(HttpStatus.BAD_REQUEST.value(),
                    e.getMessage(),
                    null);
        }

        return responseApi;
    }

}
