package com.johnny.bankworker.vo;

public class ModulesVO extends BaseVO {
    private int moduleID;
    private String moduleName;
    private String moduleType;
    private int moduleParentID;
    private Integer moduleSequence;

    public int getModuleID() {
        return moduleID;
    }

    public void setModuleID(int moduleID) {
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

    public int getModuleParentID() {
        return moduleParentID;
    }

    public void setModuleParentID(int moduleParentID) {
        this.moduleParentID = moduleParentID;
    }

    public Integer getModuleSequence() {
        return moduleSequence;
    }

    public void setModuleSequence(Integer moduleSequence) {
        this.moduleSequence = moduleSequence;
    }
}
