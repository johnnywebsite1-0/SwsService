package com.johnny.bankworker.service;

import com.johnny.bankworker.dto.AccountDTO;
import com.johnny.bankworker.entity.AccountEntity;
import com.johnny.bankworker.vo.AccountVO;
import com.johnny.bankworker.vo.UnifiedResponse;

public interface AccountService extends BaseService<AccountDTO, AccountVO, AccountEntity> {
    UnifiedResponse findCellphone(String cellphone);

    UnifiedResponse findByAccount(String cellphone, String password);

    UnifiedResponse delete(int id);

    UnifiedResponse changePassword(AccountDTO dto);
}
