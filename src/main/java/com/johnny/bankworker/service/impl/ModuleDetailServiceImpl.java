package com.johnny.bankworker.service.impl;

import com.johnny.bankworker.constant.ResponseDataConstant;
import com.johnny.bankworker.dto.ModuleDetailDTO;
import com.johnny.bankworker.entity.ModuleDetailEntity;
import com.johnny.bankworker.manager.UnifiedResponseManager;
import com.johnny.bankworker.mapper.ModuleDetailMapper;
import com.johnny.bankworker.service.ModuleDetailService;
import com.johnny.bankworker.vo.ModuleDetailVO;
import com.johnny.bankworker.vo.UnifiedResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ModuleDetailServiceImpl implements ModuleDetailService {
    @Autowired
    private ModuleDetailMapper myMapper;
    private Logger logger = LogManager.getLogger(ModuleDetailServiceImpl.class);

    @Override
    public UnifiedResponse findListOfParentModule(int parentModuleID) {
        try {
            List<ModuleDetailVO> modelList = new ArrayList<>();
            List<ModuleDetailEntity> entityList =  myMapper.searchListOfParentModule(parentModuleID);
            if(entityList == null){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            for (ModuleDetailEntity entity : entityList) {
                modelList.add(convertEntityToVo(entity));
            }
            return UnifiedResponseManager.buildSearchSuccessResponse(modelList.size(), modelList);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse findListOfModule(int moduleID) {
        try {
            List<ModuleDetailVO> modelList = new ArrayList<>();
            List<ModuleDetailEntity> entityList =  myMapper.searchListOfModule(moduleID);
            if(entityList == null){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            for (ModuleDetailEntity entity : entityList) {
                modelList.add(convertEntityToVo(entity));
            }
            return UnifiedResponseManager.buildSearchSuccessResponse(modelList.size(), modelList);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse deleteDetail(int moduleID) {
        try {
            int affectRow = myMapper.deleteDetail(moduleID);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse findList(int pageNumber, int pageSize) {
        return null;
    }

    @Override
    public UnifiedResponse findList(int bankID, int branchID, int pageNumber, int pageSize) {
        return null;
    }

    @Override
    public UnifiedResponse find(int id) {
        return null;
    }

    @Override
    public UnifiedResponse find(int bankID, int branchID, int id) {
        return null;
    }

    @Override
    public UnifiedResponse existCheck(String name) {
        return null;
    }

    @Override
    public UnifiedResponse existCheck(int bankID, int branchID, String name) {
        return null;
    }

    @Override
    public UnifiedResponse add(ModuleDetailDTO dto) {
        try {
            ModuleDetailEntity entity = convertDtoToEntity(dto);
            int deleteRow = myMapper.deleteDetail(dto.getModuleID());
            int affectRow = myMapper.insert(entity);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse change(ModuleDetailDTO dto) {
        try {
            ModuleDetailEntity entity = convertDtoToEntity(dto);
            int affectRow = myMapper.update(entity);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse delete(ModuleDetailDTO dto) {
        return null;
    }

    @Override
    public UnifiedResponse recovery(ModuleDetailDTO dto) {
        return null;
    }

    @Override
    public ModuleDetailVO convertEntityToVo(ModuleDetailEntity entity) {
        if(entity == null){
            return null;
        }
        ModuleDetailVO model = new ModuleDetailVO();

        model.setDetailID(entity.getDetailID());
        model.setModuleID(entity.getModuleID());
        model.setContent(entity.getContent());
        model.setCreateTime(entity.getCreateTime());
        model.setCreateUser(entity.getCreateUser());
        model.setUpdateTime(entity.getUpdateTime());
        model.setUpdateUser(entity.getCreateUser());
        return model;

    }

    @Override
    public ModuleDetailEntity convertDtoToEntity(ModuleDetailDTO dto) {
        ModuleDetailEntity entity = new ModuleDetailEntity();
        if(dto.getDetailID() != null){
            entity.setDetailID(dto.getDetailID());
        }
        if(dto.getModuleID() != null){
            entity.setModuleID(dto.getModuleID());
        }
        entity.setContent(dto.getContent());
        entity.setCreateUser(dto.getLoginUser());
        entity.setUpdateUser(dto.getLoginUser());
        return entity;
    }
}
