package com.johnny.bankworker.mapper;

import com.johnny.bankworker.entity.NewsEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NewsMapper extends  BaseMapper<NewsEntity> {
    int deleteNews(int newsID);
}
