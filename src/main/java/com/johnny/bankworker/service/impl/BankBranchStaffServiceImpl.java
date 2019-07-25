package com.johnny.bankworker.service.impl;

import com.johnny.bankworker.constant.ResponseDataConstant;
import com.johnny.bankworker.dto.BankBranchStaffDTO;
import com.johnny.bankworker.entity.BankBranchStaffEntity;
import com.johnny.bankworker.manager.UnifiedResponseManager;
import com.johnny.bankworker.mapper.BankBranchStaffMapper;
import com.johnny.bankworker.service.BaseService;
import com.johnny.bankworker.vo.BankBranchStaffVO;
import com.johnny.bankworker.vo.UnifiedResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BankBranchStaffServiceImpl implements BaseService<BankBranchStaffDTO, BankBranchStaffVO, BankBranchStaffEntity> {
    @Autowired
    private BankBranchStaffMapper myMapper;
    private Logger logger = LogManager.getLogger(BankBranchStaffServiceImpl.class);

    @Override
    public UnifiedResponse findList(int pageNumber, int pageSize) {
        return null;
    }

    @Override
    public UnifiedResponse findList(int bankID, int branchID, int pageNumber, int pageSize) {
        try {
            int startIndex = (pageNumber - 1) * pageSize;
            List<BankBranchStaffVO> modelList = new ArrayList<>();
            int totalCount = myMapper.searchTotalCount(bankID, branchID);
            if(totalCount == 0){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            List<BankBranchStaffEntity> entityList =  myMapper.searchList(bankID, branchID, startIndex, pageSize);
            for (BankBranchStaffEntity entity : entityList) {
                modelList.add(convertEntityToVo(entity));
            }
            return UnifiedResponseManager.buildSearchSuccessResponse(totalCount, modelList);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse find(int id) {
        return null;
    }

    @Override
    public UnifiedResponse find(int bankID, int branchID, int id) {
        try {
            BankBranchStaffEntity entity =  myMapper.searchByID(bankID, branchID, id);
            BankBranchStaffVO model = convertEntityToVo(entity);
            return UnifiedResponseManager.buildSearchSuccessResponse(model != null ? 1 : 0, model);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse existCheck(String name) {
        return null;
    }

    @Override
    public UnifiedResponse existCheck(int bankID, int branchID, String name) {
        try {
            BankBranchStaffEntity entity =  myMapper.searchByName(bankID, branchID, name);
            return UnifiedResponseManager.buildSearchSuccessResponse(entity != null ? 1 : 0,entity != null);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse add(BankBranchStaffDTO dto) {
        try {
            BankBranchStaffEntity entity = convertDtoToEntity(dto);
            int affectRow = myMapper.insert(entity);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse change(BankBranchStaffDTO dto) {
        try {
            BankBranchStaffEntity entity = convertDtoToEntity(dto);
            int affectRow = myMapper.update(entity);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse delete(BankBranchStaffDTO dto) {
        try {
            BankBranchStaffEntity entity = convertDtoToEntity(dto);
            int affectRow = myMapper.delete(entity);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse recovery(BankBranchStaffDTO dto) {
        try {
            BankBranchStaffEntity entity = convertDtoToEntity(dto);
            int affectRow = myMapper.recovery(entity);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public BankBranchStaffVO convertEntityToVo(BankBranchStaffEntity entity) {
        BankBranchStaffVO model = new BankBranchStaffVO();
        model.setStaffID(entity.getStaffID());
        model.setBankID(entity.getBankID());
        model.setBankName(entity.getBankName());
        model.setBranchID(entity.getBranchID());
        model.setBranchName(entity.getBranchName());
        model.setStaffName(entity.getStaffName());
        model.setStaffCellphone(entity.getStaffCellphone());
        model.setStaffPostID(entity.getStaffPostID());
        model.setStaffPostName(entity.getStaffPostName());
        model.setStaffPhotoUrl(entity.getStaffPhotoUrl());
        model.setStaffResumeUrl(entity.getStaffResumeUrl());
        model.setDeleteFlag(entity.getDeleteFlag());
        model.setCreateTime(entity.getCreateTime());
        model.setCreateUser(entity.getCreateUser());
        model.setUpdateTime(entity.getUpdateTime());
        model.setUpdateUser(entity.getCreateUser());
        return model;
    }

    @Override
    public BankBranchStaffEntity convertDtoToEntity(BankBranchStaffDTO dto) {
        BankBranchStaffEntity entity = new BankBranchStaffEntity();
        if (dto.getStaffID() != null) {
            entity.setStaffID(dto.getStaffID());
        }
        if (dto.getBankID() != null) {
            entity.setBankID(dto.getBankID());
        }
        if (dto.getBranchID() != null) {
            entity.setBranchID(dto.getBranchID());
        }
        if (dto.getStaffPostID() != null) {
            entity.setStaffPostID(dto.getStaffPostID());
        }
        entity.setStaffName(dto.getStaffName());
        entity.setStaffCellphone(dto.getStaffCellphone());
        entity.setStaffPhotoUrl(dto.getStaffPhotoUrl());
        entity.setStaffResumeUrl(dto.getStaffResumeUrl());
        entity.setDeleteFlag(dto.getDeleteFlag());
        entity.setCreateUser(dto.getLoginUser());
        entity.setUpdateUser(dto.getLoginUser());
        return entity;
    }
}
