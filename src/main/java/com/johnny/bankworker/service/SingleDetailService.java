package com.johnny.bankworker.service;

import com.johnny.bankworker.dto.SingleDetailDTO;
import com.johnny.bankworker.entity.SingleDetailEntity;
import com.johnny.bankworker.vo.SingleDetailVO;
import com.johnny.bankworker.vo.UnifiedResponse;

public interface SingleDetailService extends BaseService<SingleDetailDTO, SingleDetailVO, SingleDetailEntity> {
    UnifiedResponse deleteDetail(int detailID);
}
