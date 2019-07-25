package com.johnny.bankworker.service.impl;

import com.johnny.bankworker.constant.ResponseDataConstant;
import com.johnny.bankworker.dto.ModulesDTO;
import com.johnny.bankworker.dto.ModulesOrderDTO;
import com.johnny.bankworker.entity.ModulesEntity;
import com.johnny.bankworker.manager.UnifiedResponseManager;
import com.johnny.bankworker.mapper.ModuleDetailMapper;
import com.johnny.bankworker.mapper.ModulesMapper;
import com.johnny.bankworker.service.ModulesService;
import com.johnny.bankworker.vo.ModulesVO;
import com.johnny.bankworker.vo.UnifiedResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ModulesServiceImpl implements ModulesService {

    @Autowired
    private ModulesMapper myMapper;

    @Autowired
    private ModuleDetailMapper moduleDetailMapper;

    private Logger logger = LogManager.getLogger(ModulesServiceImpl.class);

    @Override
    public UnifiedResponse findAllModules() {
        try {
            List<ModulesVO> modelList = new ArrayList<>();
            int totalCount = myMapper.searchTotalCount();
            if(totalCount == 0){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            List<ModulesEntity> entityList =  myMapper.searchAllModules();
            for (ModulesEntity entity : entityList) {
                modelList.add(convertEntityToVo(entity));
            }
            return UnifiedResponseManager.buildSearchSuccessResponse(totalCount, modelList);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse findRootModules() {
        try {
            List<ModulesVO> modelList = new ArrayList<>();
            List<ModulesEntity> entityList =  myMapper.searchRootModules();
            if(entityList == null){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            for (ModulesEntity entity : entityList) {
                modelList.add(convertEntityToVo(entity));
            }
            return UnifiedResponseManager.buildSearchSuccessResponse(modelList.size(), modelList);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse findChildModules(int parentModuleID) {
        try {
            List<ModulesVO> modelList = new ArrayList<>();
            List<ModulesEntity> entityList =  myMapper.searchChildModules(parentModuleID);
            if(entityList == null){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            for (ModulesEntity entity : entityList) {
                modelList.add(convertEntityToVo(entity));
            }
            return UnifiedResponseManager.buildSearchSuccessResponse(modelList.size(), modelList);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse delete(int moduleID) {
        try {
            int affectRow = myMapper.deleteModule(moduleID);
            affectRow += moduleDetailMapper.deleteDetail(moduleID);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse move(ModulesDTO dto) {
        try {
            ModulesEntity entity = convertDtoToEntity(dto);
            int affectRow = myMapper.moveModule(entity);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse changeSequence(ModulesOrderDTO dto) {
        try {
            String[] itemsOrder = dto.getModulesOrder().split(",");
            int itemOrder = 1;
            int affectRow = 0;
            for (String itemID : itemsOrder) {
                ModulesEntity entity = new ModulesEntity();
                entity.setModuleParentID(dto.getModuleParentID());
                entity.setModuleID(Integer.parseInt(itemID));
                entity.setModuleSequence(itemOrder);
                entity.setUpdateUser(dto.getLoginUser());
                affectRow += myMapper.updateModulesSequence(entity);
                itemOrder++;
            }
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
    public UnifiedResponse add(ModulesDTO dto) {
        try {
            ModulesEntity entity = convertDtoToEntity(dto);
            int maxItemOrder = myMapper.searchChildModulesMaxSequence(entity.getModuleParentID());
            entity.setModuleSequence(maxItemOrder + 1);
            int affectRow = myMapper.insert(entity);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse change(ModulesDTO dto) {
        try {
            ModulesEntity entity = convertDtoToEntity(dto);
            int affectRow = myMapper.update(entity);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse delete(ModulesDTO dto) {
        return null;
    }

    @Override
    public UnifiedResponse recovery(ModulesDTO dto) {
        return null;
    }

    @Override
    public ModulesVO convertEntityToVo(ModulesEntity entity) {
        if(entity == null){
            return null;
        }
        ModulesVO model = new ModulesVO();
        model.setModuleID(entity.getModuleID());
        model.setModuleName(entity.getModuleName());
        model.setModuleType(entity.getModuleType());
        model.setModuleParentID(entity.getModuleParentID());
        model.setModuleSequence(entity.getModuleSequence());
        model.setCreateTime(entity.getCreateTime());
        model.setCreateUser(entity.getCreateUser());
        model.setUpdateTime(entity.getUpdateTime());
        model.setUpdateUser(entity.getCreateUser());
        return model;
    }

    @Override
    public ModulesEntity convertDtoToEntity(ModulesDTO dto) {
        ModulesEntity entity = new ModulesEntity();
        if(dto.getModuleID() != null){
            entity.setModuleID(dto.getModuleID());
        }
        if(dto.getModuleParentID() != null){
            entity.setModuleParentID(dto.getModuleParentID());
        }
        entity.setModuleName(dto.getModuleName());
        entity.setModuleType(dto.getModuleType());
        entity.setCreateUser(dto.getLoginUser());
        entity.setUpdateUser(dto.getLoginUser());
        return entity;
    }
}
