package com.johnny.bankworker.service.impl;

import com.johnny.bankworker.constant.ResponseDataConstant;
import com.johnny.bankworker.dto.SingleDetailDTO;
import com.johnny.bankworker.entity.SingleDetailEntity;
import com.johnny.bankworker.manager.UnifiedResponseManager;
import com.johnny.bankworker.mapper.SingleDetailMapper;
import com.johnny.bankworker.service.SingleDetailService;
import com.johnny.bankworker.vo.SingleDetailVO;
import com.johnny.bankworker.vo.UnifiedResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SingleDetailServiceImpl implements SingleDetailService {
    @Autowired
    private SingleDetailMapper myMapper;
    private Logger logger = LogManager.getLogger(SingleDetailServiceImpl.class);

    @Override
    public UnifiedResponse deleteDetail(int detailID) {
        try {
            int affectRow = myMapper.deleteDetail(detailID);
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
            List<SingleDetailVO> modelList = new ArrayList<>();
            int totalCount = myMapper.searchTotalCount();
            if(totalCount == 0){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            List<SingleDetailEntity> entityList =  myMapper.searchList(startIndex, pageSize);
            for (SingleDetailEntity entity : entityList) {
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
            SingleDetailEntity entity =  myMapper.searchByID(id);
            SingleDetailVO model = convertEntityToVo(entity);
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
    public UnifiedResponse add(SingleDetailDTO dto) {
        try {
            SingleDetailEntity entity = convertDtoToEntity(dto);
            int affectRow = myMapper.insert(entity);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse change(SingleDetailDTO dto) {
        try {
            SingleDetailEntity entity = convertDtoToEntity(dto);
            int affectRow = myMapper.update(entity);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse delete(SingleDetailDTO dto) {
        return null;
    }

    @Override
    public UnifiedResponse recovery(SingleDetailDTO dto) {
        return null;
    }

    @Override
    public SingleDetailVO convertEntityToVo(SingleDetailEntity entity) {
        if(entity == null){
            return null;
        }
        SingleDetailVO model = new SingleDetailVO();
        model.setDetailID(entity.getDetailID());
        model.setDetailTitle(entity.getDetailTitle());
        model.setContent(entity.getContent());
        model.setCreateTime(entity.getCreateTime());
        model.setCreateUser(entity.getCreateUser());
        model.setUpdateTime(entity.getUpdateTime());
        model.setUpdateUser(entity.getCreateUser());
        return model;
    }

    @Override
    public SingleDetailEntity convertDtoToEntity(SingleDetailDTO dto) {
        SingleDetailEntity entity = new SingleDetailEntity();
        if(dto.getDetailID() != null){
            entity.setDetailID(dto.getDetailID());
        }
        entity.setDetailTitle(dto.getDetailTitle());
        entity.setContent(dto.getContent());
        entity.setCreateUser(dto.getLoginUser());
        entity.setUpdateUser(dto.getLoginUser());
        return entity;
    }
}
