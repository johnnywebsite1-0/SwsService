package com.johnny.bankworker.dto;

public class NewsDTO extends BaseDTO {
    private Integer newsID;
    private String newsTitle;
    private String newsThumbnailUrl;
    private String newsDate;
    private String newsContent;

    public Integer getNewsID() {
        return newsID;
    }

    public void setNewsID(Integer newsID) {
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
