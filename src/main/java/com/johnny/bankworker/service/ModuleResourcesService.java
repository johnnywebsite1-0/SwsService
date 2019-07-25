package com.johnny.bankworker.service;

import com.johnny.bankworker.dto.ModuleResourcesDTO;
import com.johnny.bankworker.entity.ModuleResourcesEntity;
import com.johnny.bankworker.vo.ModuleResourcesVO;
import com.johnny.bankworker.vo.UnifiedResponse;

public interface ModuleResourcesService extends BaseService<ModuleResourcesDTO, ModuleResourcesVO, ModuleResourcesEntity> {
    UnifiedResponse deleteResources(int resourcesID);
}
