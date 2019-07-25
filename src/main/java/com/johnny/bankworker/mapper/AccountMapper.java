package com.johnny.bankworker.mapper;

import com.johnny.bankworker.entity.AccountEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper extends BaseMapper<AccountEntity> {
    int searchTotalCountByCellphone(String cellphone);

    AccountEntity searchByAccount(String cellphone, String password);

    int changePassword(AccountEntity entity);
}
