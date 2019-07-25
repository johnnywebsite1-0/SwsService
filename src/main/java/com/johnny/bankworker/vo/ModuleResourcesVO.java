package com.johnny.bankworker.vo;

public class ModuleResourcesVO extends BaseVO {
    private int resourcesID;
    private String resourcesUrl;
    private String resourcesType;

    public int getResourcesID() {
        return resourcesID;
    }

    public void setResourcesID(int resourcesID) {
        this.resourcesID = resourcesID;
    }

    public String getResourcesUrl() {
        return resourcesUrl;
    }

    public void setResourcesUrl(String resourcesUrl) {
        this.resourcesUrl = resourcesUrl;
    }

    public String getResourcesType() {
        return resourcesType;
    }

    public void setResourcesType(String resourcesType) {
        this.resourcesType = resourcesType;
    }
}
