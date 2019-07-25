package com.johnny.bankworker.mapper;

import com.johnny.bankworker.entity.ModulesEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ModulesMapper extends BaseMapper<ModulesEntity> {
    List<ModulesEntity> searchAllModules();

    List<ModulesEntity> searchRootModules();

    List<ModulesEntity> searchChildModules(int parentModuleID);

    int searchChildModulesMaxSequence(int parentModuleID);

    int deleteModule(int moduleID);

    int moveModule(ModulesEntity itemEntity);

    int updateModulesSequence(ModulesEntity itemEntity);

}
