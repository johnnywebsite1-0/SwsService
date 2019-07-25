package com.johnny.bankworker.mapper;

import com.johnny.bankworker.entity.SingleDetailEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SingleDetailMapper extends BaseMapper<SingleDetailEntity> {
    int deleteDetail(int detailID);
}
