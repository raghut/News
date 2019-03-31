package com.rgu.news.dagger.module;

import com.rgu.news.network.service.NewsApiService;
import com.rgu.news.ui.interactor.NewsListInteractor;
import com.rgu.news.ui.presenter.NewsListPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class NewsModule {

    @Provides
    public NewsListInteractor provideNewsListInteractor(NewsApiService newsApiService) {
        return new NewsListInteractor(newsApiService);
    }

    @Provides
    public NewsListPresenter provideNewListPresenter(NewsListInteractor interactor) {
        return new NewsListPresenter(interactor);
    }
}
