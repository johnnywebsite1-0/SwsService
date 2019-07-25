package com.johnny.bankworker.entity;

import com.johnny.bankworker.vo.BaseVO;

public class NewsEntity extends BaseVO {
    private int newsID;
    private String newsTitle;
    private String newsThumbnailUrl;
    private String newsDate;
    private String newsContent;

    public int getNewsID() {
        return newsID;
    }

    public void setNewsID(int newsID) {
        this.newsID = newsID;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsThumbnailUrl() {
        return newsThumbnailUrl;
    }

    public void setNewsThumbnailUrl(String newsThumbnailUrl) {
        this.newsThumbnailUrl = newsThumbnailUrl;
    }

    public String getNewsDate() {
        return newsDate;
    }

    public void setNewsDate(String newsDate) {
        this.newsDate = newsDate;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }
}
