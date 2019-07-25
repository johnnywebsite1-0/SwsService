package com.johnny.bankworker.service;

import com.johnny.bankworker.dto.ModuleDetailDTO;
import com.johnny.bankworker.entity.ModuleDetailEntity;
import com.johnny.bankworker.vo.ModuleDetailVO;
import com.johnny.bankworker.vo.UnifiedResponse;

public interface ModuleDetailService extends BaseService<ModuleDetailDTO, ModuleDetailVO, ModuleDetailEntity> {
    UnifiedResponse findListOfParentModule(int parentModuleID);

    UnifiedResponse findListOfModule(int moduleID);

    UnifiedResponse deleteDetail(int moduleID);
}
