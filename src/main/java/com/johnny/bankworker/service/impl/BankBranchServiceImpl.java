package com.johnny.bankworker.service.impl;

import com.johnny.bankworker.constant.ResponseDataConstant;
import com.johnny.bankworker.dto.BankBranchDTO;
import com.johnny.bankworker.entity.BankBranchEntity;
import com.johnny.bankworker.manager.UnifiedResponseManager;
import com.johnny.bankworker.mapper.BankBranchMapper;
import com.johnny.bankworker.service.BaseService;
import com.johnny.bankworker.vo.BankBranchVO;
import com.johnny.bankworker.vo.UnifiedResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BankBranchServiceImpl implements BaseService<BankBranchDTO, BankBranchVO, BankBranchEntity> {
    @Autowired
    private BankBranchMapper myMapper;
    private Logger logger = LogManager.getLogger(BankBranchServiceImpl.class);

    @Override
    public UnifiedResponse findList(int pageNumber, int pageSize) {
        try {
            int startIndex = (pageNumber - 1) * pageSize;
            List<BankBranchVO> modelList = new ArrayList<>();
            int totalCount = myMapper.searchTotalCount();
            if(totalCount == 0){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            List<BankBranchEntity> entityList =  myMapper.searchList(startIndex, pageSize);
            for (BankBranchEntity entity : entityList) {
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
            BankBranchEntity entity =  myMapper.searchByID(id);
            BankBranchVO model = convertEntityToVo(entity);
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
            BankBranchEntity entity =  myMapper.searchByName(name);
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
    public UnifiedResponse add(BankBranchDTO dto) {
        try {
            BankBranchEntity entity = convertDtoToEntity(dto);
            int affectRow = myMapper.insert(entity);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse change(BankBranchDTO dto) {
        try {
            BankBranchEntity entity = convertDtoToEntity(dto);
            int affectRow = myMapper.update(entity);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse delete(BankBranchDTO dto) {
        try {
            BankBranchEntity entity = convertDtoToEntity(dto);
            int affectRow = myMapper.delete(entity);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse recovery(BankBranchDTO dto) {
        try {
            BankBranchEntity entity = convertDtoToEntity(dto);
            int affectRow = myMapper.recovery(entity);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public BankBranchVO convertEntityToVo(BankBranchEntity entity) {
        BankBranchVO model = new BankBranchVO();
        model.setBranchID(entity.getBranchID());
        model.setBankID(entity.getBankID());
        model.setProvinceCode(entity.getProvinceCode());
        model.setProvinceName(entity.getProvinceName());
        model.setCityCode(entity.getCityCode());
        model.setCityName(entity.getCityName());
        model.setDistrictCode(entity.getDistrictCode());
        model.setDistrictName(entity.getDistrictName());
        model.setBranchName(entity.getBranchName());
        model.setAddress(entity.getAddress());
        model.setBranchLogo(entity.getBranchLogo());
        model.setBranchContact(entity.getBranchContact());
        model.setBranchContactCellphone(entity.getBranchContactCellphone());
        model.setDeleteFlag(entity.getDeleteFlag());
        model.setCreateTime(entity.getCreateTime());
        model.setCreateUser(entity.getCreateUser());
        model.setUpdateTime(entity.getUpdateTime());
        model.setUpdateUser(entity.getCreateUser());
        return model;
    }

    @Override
    public BankBranchEntity convertDtoToEntity(BankBranchDTO dto) {
        BankBranchEntity entity = new BankBranchEntity();
        if (dto.getBranchID() != null) {
            entity.setBranchID(dto.getBranchID());
        }
        if (dto.getBankID() != null) {
            entity.setBankID(dto.getBankID());
        }
        if (dto.getProvinceCode() != null) {
            entity.setProvinceCode(dto.getProvinceCode());
        }
        if (dto.getCityCode() != null) {
            entity.setCityCode(dto.getCityCode());
        }
        if (dto.getDistrictCode() != null) {
            entity.setDistrictCode(dto.getDistrictCode());
        }
        entity.setBranchName(dto.getBranchName());
        entity.setAddress(dto.getAddress());
        entity.setBranchLogo(dto.getBranchLogo());
        entity.setBranchContact(dto.getBranchContact());
        entity.setBranchContactCellphone(dto.getBranchContactCellphone());
        entity.setDeleteFlag(dto.getDeleteFlag());
        entity.setCreateUser(dto.getLoginUser());
        entity.setUpdateUser(dto.getLoginUser());
        return entity;
    }
}
