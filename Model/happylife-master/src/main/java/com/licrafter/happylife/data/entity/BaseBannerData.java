package com.licrafter.happylife.data.entity;

/**
 * Created by Administrator on 2016/2/18.
 */
public class BaseBannerData {

    private String objectId;
    private String iconUrl;
    private String infoStr;
    private String transUrl;
    private String createdAt;
    private String updatedAt;

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getInfoStr() {
        return infoStr;
    }

    public void setInfoStr(String infoStr) {
        this.infoStr = infoStr;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getTransUrl() {
        return transUrl;
    }

    public void setTransUrl(String transUrl) {
        this.transUrl = transUrl;
    }
}
