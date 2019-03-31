package com.rgu.news.ui.interactor;

import com.rgu.news.data.model.NewsListDataResponse;
import com.rgu.news.network.service.NewsApiService;

import java.util.Map;

import io.reactivex.Observable;

public class NewsListInteractor {

    private NewsApiService newsApiService;

    public NewsListInteractor(NewsApiService newsApiService) {

        this.newsApiService = newsApiService;
    }

    public Observable<NewsListDataResponse> fetchNewsList(Map<String, String> requestParams) {
        return newsApiService.fetchNewsList(requestParams);
    }
}
