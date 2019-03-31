package com.rgu.news.network.service;

import com.rgu.news.data.model.NewsListDataResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;


public interface NewsApiService {

    @GET("top-headlines")
    Observable<NewsListDataResponse> fetchNewsList(@QueryMap Map<String, String> requestParams);
}
