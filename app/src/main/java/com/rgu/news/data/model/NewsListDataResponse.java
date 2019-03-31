package com.rgu.news.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsListDataResponse {

    @SerializedName("status")
    public String status;

    @SerializedName("totalResults")
    public int totalResults;

    @SerializedName("articles")
    public List<NewsItem> newsList;


    public class NewsItem {
        @SerializedName("title")
        public String title;

        @SerializedName("description")
        public String desc;

        @SerializedName("url")
        public String url;
    }
}
