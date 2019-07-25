package com.johnny.bankworker.dto;

public class ModuleResourcesDTO extends BaseDTO {
    private Integer resourcesID;
    private String resourcesUrlList;
    private String resourcesType;

    public Integer getResourcesID() {
        return resourcesID;
    }

    public void setResourcesID(Integer resourcesID) {
        this.resourcesID = resourcesID;
    }

    public String getResourcesType() {
        return resourcesType;
    }

    public void setResourcesType(String resourcesType) {
        this.resourcesType = resourcesType;
    }

    public String getResourcesUrlList() {
        return resourcesUrlList;
    }

    public void setResourcesUrlList(String resourcesUrlList) {
        this.resourcesUrlList = resourcesUrlList;
    }
}
