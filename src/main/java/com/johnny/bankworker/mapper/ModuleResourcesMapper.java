package com.johnny.bankworker.mapper;

import com.johnny.bankworker.entity.ModuleResourcesEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ModuleResourcesMapper extends BaseMapper<ModuleResourcesEntity>{
    int deleteResources(int resourcesID);
}
