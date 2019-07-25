package com.johnny.bankworker.dto;

public class ModulesDTO extends BaseDTO {
    private Integer moduleID;
    private String moduleName;
    private String moduleType;
    private Integer moduleParentID;
    private Integer moduleSequence;

    public Integer getModuleID() {
        return moduleID;
    }

    public void setModuleID(Integer moduleID) {
        this.moduleID = moduleID;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getModuleType() {
        return moduleType;
    }

    public void setModuleType(String moduleType) {
        this.moduleType = moduleType;
    }

    public Integer getModuleParentID() {
        return moduleParentID;
    }

    public void setModuleParentID(Integer moduleParentID) {
        this.moduleParentID = moduleParentID;
    }

    public Integer getModuleSequence() {
        return moduleSequence;
    }

    public void setModuleSequence(Integer moduleSequence) {
        this.moduleSequence = moduleSequence;
    }
}
