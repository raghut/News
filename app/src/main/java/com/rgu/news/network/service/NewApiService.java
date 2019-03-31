package com.rgu.news.network.service;

import com.rgu.news.data.model.NewsListData;

import io.reactivex.Observable;


public interface NewApiService {

    Observable<NewsListData> getNewsList();
}
