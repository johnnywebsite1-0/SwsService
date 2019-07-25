package com.johnny.bankworker.service.impl;

import com.johnny.bankworker.constant.ResponseDataConstant;
import com.johnny.bankworker.dto.ModuleResourcesDTO;
import com.johnny.bankworker.entity.ModuleResourcesEntity;
import com.johnny.bankworker.manager.UnifiedResponseManager;
import com.johnny.bankworker.mapper.ModuleResourcesMapper;
import com.johnny.bankworker.service.ModuleResourcesService;
import com.johnny.bankworker.vo.ModuleResourcesVO;
import com.johnny.bankworker.vo.UnifiedResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ModuleResourcesServiceImpl implements ModuleResourcesService {
    @Autowired
    private ModuleResourcesMapper myMapper;
    private Logger logger = LogManager.getLogger(ModuleResourcesServiceImpl.class);

    @Override
    public UnifiedResponse deleteResources(int resourcesID) {
        try {
            int affectRow = myMapper.deleteResources(resourcesID);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse findList(int pageNumber, int pageSize) {
        try {
            int startIndex = (pageNumber - 1) * pageSize;
            List<ModuleResourcesVO> modelList = new ArrayList<>();
            int totalCount = myMapper.searchTotalCount();
            if(totalCount == 0){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            List<ModuleResourcesEntity> entityList =  myMapper.searchList(startIndex, pageSize);
            for (ModuleResourcesEntity entity : entityList) {
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
    public UnifiedResponse add(ModuleResourcesDTO dto) {
        try {
            int affectRow = 0;
            String[] fileUrlArray = dto.getResourcesUrlList().split(",");
            for (String fileUrl : fileUrlArray) {
                ModuleResourcesEntity entity = new ModuleResourcesEntity();
                entity.setResourcesUrl(fileUrl);
                entity.setResourcesType(dto.getResourcesType());
                entity.setCreateUser(dto.getLoginUser());
                entity.setUpdateUser(dto.getLoginUser());
                affectRow += myMapper.insert(entity);
            }

            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse change(ModuleResourcesDTO dto) {
        return null;
    }

    @Override
    public UnifiedResponse delete(ModuleResourcesDTO dto) {
        return null;
    }

    @Override
    public UnifiedResponse recovery(ModuleResourcesDTO dto) {
        return null;
    }

    @Override
    public ModuleResourcesVO convertEntityToVo(ModuleResourcesEntity entity) {
        if(entity == null){
            return null;
        }

        ModuleResourcesVO model = new ModuleResourcesVO();
        model.setResourcesID(entity.getResourcesID());
        model.setResourcesUrl(entity.getResourcesUrl());
        model.setResourcesType(entity.getResourcesType());
        model.setCreateTime(entity.getCreateTime());
        model.setCreateUser(entity.getCreateUser());
        model.setUpdateTime(entity.getUpdateTime());
        model.setUpdateUser(entity.getCreateUser());
        return model;
    }

    @Override
    public ModuleResourcesEntity convertDtoToEntity(ModuleResourcesDTO dto) {
        return null;
    }
}
