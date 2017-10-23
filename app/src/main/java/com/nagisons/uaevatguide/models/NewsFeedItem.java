package com.nagisons.uaevatguide.models;

import java.util.Date;

/**
 * Created by manish on 10/21/17.
 */

public class NewsFeedItem {

    public String newsTitle;
    public String newsTime;
    public String newsSource;
    public String newsUrl;
    public String newsSummary;
    public Date newsDate;

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsTime() {
        return newsTime;
    }

    public void setNewsTime(String newsTime) {
        this.newsTime = newsTime;
    }

    public String getNewsSource() {
        return newsSource;
    }

    public void setNewsSource(String newsSource) {
        this.newsSource = newsSource;
    }

    public String getNewsUrl() {
        return newsUrl;
    }

    public void setNewsUrl(String newsUrl) {
        this.newsUrl = newsUrl;
    }

    public String getNewsSummary() {
        return newsSummary;
    }

    public void setNewsSummary(String newsSummary) {
        this.newsSummary = newsSummary;
    }

    public Date getNewsDate() {
        return newsDate;
    }

    public void setNewsDate(Date milliTime) {
        this.newsDate = milliTime;
    }
}
