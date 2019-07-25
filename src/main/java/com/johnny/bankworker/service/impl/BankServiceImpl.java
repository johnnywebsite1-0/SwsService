package com.johnny.bankworker.service.impl;

import com.johnny.bankworker.constant.ResponseDataConstant;
import com.johnny.bankworker.dto.BankDTO;
import com.johnny.bankworker.entity.BankEntity;
import com.johnny.bankworker.manager.UnifiedResponseManager;
import com.johnny.bankworker.mapper.BankMapper;
import com.johnny.bankworker.service.BaseService;
import com.johnny.bankworker.vo.BankVO;
import com.johnny.bankworker.vo.UnifiedResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BankServiceImpl implements BaseService<BankDTO, BankVO, BankEntity> {
    @Autowired
    private BankMapper myMapper;
    private Logger logger = LogManager.getLogger(BankServiceImpl.class);

    @Override
    public UnifiedResponse findList(int pageNumber, int pageSize) {
        try {
            int startIndex = (pageNumber - 1) * pageSize;
            List<BankVO> modelList = new ArrayList<>();
            int totalCount = myMapper.searchTotalCount();
            if(totalCount == 0){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            List<BankEntity> entityList =  myMapper.searchList(startIndex, pageSize);
            for (BankEntity entity : entityList) {
                modelList.add(convertEntityToVo(entity));
            }
            return UnifiedResponseManager.buildSearchSuccessResponse(totalCount, modelList);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse findList(int bankID, int branchID, int pageNumber, int pageSize) {
        return null;
    }

    @Override
    public UnifiedResponse find(int id) {
        try {
            BankEntity entity =  myMapper.searchByID(id);
            BankVO model = convertEntityToVo(entity);
            return UnifiedResponseManager.buildSearchSuccessResponse(model != null ? 1 : 0, model);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse find(int bankID, int branchID, int id) {
        return null;
    }

    @Override
    public UnifiedResponse existCheck(String name) {
        try {
            BankEntity entity =  myMapper.searchByName(name);
            return UnifiedResponseManager.buildSearchSuccessResponse(entity != null ? 1 : 0,entity != null);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse existCheck(int bankID, int branchID, String name) {
        return null;
    }

    @Override
    public UnifiedResponse add(BankDTO dto) {
        try {
            BankEntity entity = convertDtoToEntity(dto);
            int affectRow = myMapper.insert(entity);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse change(BankDTO dto) {
        try {
            BankEntity entity = convertDtoToEntity(dto);
            int affectRow = myMapper.update(entity);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse delete(BankDTO dto) {
        try {
            BankEntity entity = convertDtoToEntity(dto);
            int affectRow = myMapper.delete(entity);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse recovery(BankDTO dto) {
        try {
            BankEntity entity = convertDtoToEntity(dto);
            int affectRow = myMapper.recovery(entity);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public BankVO convertEntityToVo(BankEntity entity) {
        if(entity == null){
            return null;
        }

        BankVO model = new BankVO();
        model.setBankID(entity.getBankID());
        model.setBankName(entity.getBankName());
        model.setBankLogo(entity.getBankLogo());
        model.setDeleteFlag(entity.getDeleteFlag());
        model.setCreateTime(entity.getCreateTime());
        model.setCreateUser(entity.getCreateUser());
        model.setUpdateTime(entity.getUpdateTime());
        model.setUpdateUser(entity.getCreateUser());
        return model;
    }

    @Override
    public BankEntity convertDtoToEntity(BankDTO dto) {
        BankEntity entity = new BankEntity();
        if(dto.getBankID() != null){
            entity.setBankID(dto.getBankID());
        }
        entity.setBankName(dto.getBankName());
        entity.setBankLogo(dto.getBankLogo());
        entity.setDeleteFlag(dto.getDeleteFlag());
        entity.setCreateUser(dto.getLoginUser());
        entity.setUpdateUser(dto.getLoginUser());
        return entity;
    }
}
