package com.johnny.bankworker.dto;

public class ModulesOrderDTO extends BaseDTO {
    private Integer moduleParentID;
    private String modulesOrder;

    public Integer getModuleParentID() {
        return moduleParentID;
    }

    public void setModuleParentID(Integer moduleParentID) {
        this.moduleParentID = moduleParentID;
    }

    public String getModulesOrder() {
        return modulesOrder;
    }

    public void setModulesOrder(String modulesOrder) {
        this.modulesOrder = modulesOrder;
    }
}
