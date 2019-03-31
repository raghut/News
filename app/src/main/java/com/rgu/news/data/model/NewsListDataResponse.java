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


    public static class NewsItem {
        @SerializedName("title")
        public String title;

        @SerializedName("description")
        public String desc;

        @SerializedName("url")
        public String url;

        @SerializedName("source")
        public Source source;
    }

    public static class Source {
        @SerializedName("id")
        public String id;

        @SerializedName("name")
        public String name;
    }
}
