package com.johnny.bankworker.service;

import com.johnny.bankworker.dto.ModulesDTO;
import com.johnny.bankworker.dto.ModulesOrderDTO;
import com.johnny.bankworker.entity.ModulesEntity;
import com.johnny.bankworker.vo.ModulesVO;
import com.johnny.bankworker.vo.UnifiedResponse;

public interface ModulesService extends BaseService<ModulesDTO, ModulesVO, ModulesEntity> {
    UnifiedResponse findAllModules();

    UnifiedResponse findRootModules();

    UnifiedResponse findChildModules(int parentModuleID);

    UnifiedResponse delete(int moduleID);

    UnifiedResponse move(ModulesDTO dto);

    UnifiedResponse changeSequence(ModulesOrderDTO dto);


}
