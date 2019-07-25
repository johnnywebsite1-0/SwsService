package com.johnny.bankworker.mapper;

import com.johnny.bankworker.entity.ModuleDetailEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ModuleDetailMapper extends BaseMapper<ModuleDetailEntity> {
    List<ModuleDetailEntity> searchListOfParentModule(int parentModuleID);

    List<ModuleDetailEntity> searchListOfModule(int moduleID);

    int deleteDetail(int moduleID);
}
