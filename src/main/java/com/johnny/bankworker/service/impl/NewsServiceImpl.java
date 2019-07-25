package com.johnny.bankworker.service.impl;

import com.johnny.bankworker.constant.ResponseDataConstant;
import com.johnny.bankworker.dto.NewsDTO;
import com.johnny.bankworker.entity.NewsEntity;
import com.johnny.bankworker.manager.UnifiedResponseManager;
import com.johnny.bankworker.mapper.NewsMapper;
import com.johnny.bankworker.service.NewsService;
import com.johnny.bankworker.vo.NewsVO;
import com.johnny.bankworker.vo.UnifiedResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsMapper myMapper;
    private Logger logger = LogManager.getLogger(NewsServiceImpl.class);

    @Override
    public UnifiedResponse deleteNews(int newsID) {
        try {
            int affectRow = myMapper.deleteNews(newsID);
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
            List<NewsVO> modelList = new ArrayList<>();
            int totalCount = myMapper.searchTotalCount();
            if (totalCount == 0) {
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            List<NewsEntity> entityList = myMapper.searchList(startIndex, pageSize);
            for (NewsEntity entity : entityList) {
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
            NewsEntity entity = myMapper.searchByID(id);
            NewsVO model = convertEntityToVo(entity);
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
    public UnifiedResponse add(NewsDTO dto) {
        try {
            NewsEntity entity = convertDtoToEntity(dto);
            int affectRow = myMapper.insert(entity);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse change(NewsDTO dto) {
        try {
            NewsEntity entity = convertDtoToEntity(dto);
            int affectRow = myMapper.update(entity);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }

    }

    @Override
    public UnifiedResponse delete(NewsDTO dto) {
        return null;
    }

    @Override
    public UnifiedResponse recovery(NewsDTO dto) {
        return null;
    }

    @Override
    public NewsVO convertEntityToVo(NewsEntity entity) {
        if(entity == null){
            return null;
        }
        NewsVO model = new NewsVO();
        model.setNewsID(entity.getNewsID());
        model.setNewsTitle(entity.getNewsTitle());
        model.setNewsThumbnailUrl(entity.getNewsThumbnailUrl());
        model.setNewsDate(entity.getNewsDate());
        model.setNewsContent(entity.getNewsContent());
        model.setCreateTime(entity.getCreateTime());
        model.setCreateUser(entity.getCreateUser());
        model.setUpdateTime(entity.getUpdateTime());
        model.setUpdateUser(entity.getCreateUser());
        return model;
    }

    @Override
    public NewsEntity convertDtoToEntity(NewsDTO dto) {
        NewsEntity entity = new NewsEntity();
        if(dto.getNewsID() != null){
            entity.setNewsID(dto.getNewsID());
        }
        entity.setNewsTitle(dto.getNewsTitle());
        entity.setNewsThumbnailUrl(dto.getNewsThumbnailUrl());
        entity.setNewsDate(dto.getNewsDate());
        entity.setNewsContent(dto.getNewsContent());
        entity.setCreateUser(dto.getLoginUser());
        entity.setUpdateUser(dto.getLoginUser());
        return entity;
    }
}
