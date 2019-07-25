package com.johnny.bankworker.service.impl;

import com.johnny.bankworker.constant.ResponseDataConstant;
import com.johnny.bankworker.dto.AccountDTO;
import com.johnny.bankworker.entity.AccountEntity;
import com.johnny.bankworker.manager.UnifiedResponseManager;
import com.johnny.bankworker.mapper.AccountMapper;
import com.johnny.bankworker.service.AccountService;
import com.johnny.bankworker.vo.AccountVO;
import com.johnny.bankworker.vo.UnifiedResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountMapper myMapper;
    private Logger logger = LogManager.getLogger(AccountServiceImpl.class);
    @Override
    public UnifiedResponse findCellphone(String cellphone) {
        try {
            int totalCount = myMapper.searchTotalCountByCellphone(cellphone);
            return UnifiedResponseManager.buildSearchSuccessResponse(totalCount, ResponseDataConstant.NO_DATA);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse findByAccount(String cellphone, String password) {
        try {
            AccountEntity entity =  myMapper.searchByAccount(cellphone, password);
            AccountVO model = convertEntityToVo(entity);
            return UnifiedResponseManager.buildSearchSuccessResponse(model != null ? 1 : 0, model);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse findList(int pageNumber, int pageSize) {
        try {
            int startIndex = (pageNumber - 1) * pageSize;
            List<AccountVO> modelList = new ArrayList<>();
            int totalCount = myMapper.searchTotalCount();
            if(totalCount == 0){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            List<AccountEntity> entityList =  myMapper.searchList(startIndex, pageSize);
            for (AccountEntity entity : entityList) {
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
            AccountEntity entity =  myMapper.searchByID(id);
            AccountVO model = convertEntityToVo(entity);
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
        return null;
    }

    @Override
    public UnifiedResponse existCheck(int bankID, int branchID, String name) {
        return null;
    }

    @Override
    public UnifiedResponse changePassword(AccountDTO dto) {
        try {
            AccountEntity entity = convertDtoToEntity(dto);
            int affectRow = myMapper.changePassword(entity);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse add(AccountDTO dto) {
        try {
            AccountEntity entity = convertDtoToEntity(dto);
            int affectRow = myMapper.insert(entity);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse change(AccountDTO dto) {
        try {
            AccountEntity entity = convertDtoToEntity(dto);
            int affectRow = myMapper.update(entity);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse delete(AccountDTO dto) {
        try {
            AccountEntity entity = convertDtoToEntity(dto);
            int affectRow = myMapper.delete(entity);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse delete(int id) {
        try {
            AccountDTO dto = new AccountDTO();
            dto.setAccountID(id);
            AccountEntity entity = convertDtoToEntity(dto);
            int affectRow = myMapper.delete(entity);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse recovery(AccountDTO dto) {
        return null;
    }

    @Override
    public AccountVO convertEntityToVo(AccountEntity entity) {
        if(entity == null){
            return null;
        }
        AccountVO model = new AccountVO();
        model.setAccountID(entity.getAccountID());
        model.setFullName(entity.getFullName());
        model.setCellphone(entity.getCellphone());
        model.setPassword(entity.getPassword());
        model.setPhoto(entity.getPhoto());
        model.setIntroduction(entity.getIntroduction());
        model.setDeleteFlag(entity.getDeleteFlag());
        model.setCreateTime(entity.getCreateTime());
        model.setCreateUser(entity.getCreateUser());
        model.setUpdateTime(entity.getUpdateTime());
        model.setUpdateUser(entity.getCreateUser());
        return model;
    }

    @Override
    public AccountEntity convertDtoToEntity(AccountDTO dto) {
        AccountEntity entity = new AccountEntity();
        if(dto.getAccountID() != null){
            entity.setAccountID(dto.getAccountID());
        }
        entity.setFullName(dto.getFullName());
        entity.setCellphone(dto.getCellphone());
        entity.setPassword(dto.getPassword());
        entity.setPhoto(dto.getPhoto());
        entity.setIntroduction(dto.getIntroduction());
        entity.setDeleteFlag(dto.getDeleteFlag());
        entity.setCreateUser(dto.getLoginUser());
        entity.setUpdateUser(dto.getLoginUser());
        return entity;
    }
}
