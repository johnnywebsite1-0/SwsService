package com.johnny.bankworker.service;

import com.johnny.bankworker.dto.NewsDTO;
import com.johnny.bankworker.entity.NewsEntity;
import com.johnny.bankworker.vo.NewsVO;
import com.johnny.bankworker.vo.UnifiedResponse;

public interface NewsService extends BaseService<NewsDTO, NewsVO, NewsEntity> {
    UnifiedResponse deleteNews(int newsID);
}
