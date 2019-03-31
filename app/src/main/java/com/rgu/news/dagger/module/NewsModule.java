package com.rgu.news.dagger.module;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.rgu.news.data.persistance.NewsDataBase;
import com.rgu.news.data.persistance.NewsListDao;
import com.rgu.news.network.service.NewsApiService;
import com.rgu.news.ui.interactor.NewsListInteractor;
import com.rgu.news.ui.presenter.NewsListPresenter;
import com.rgu.news.utils.rx.RxSchedulers;
import com.rgu.news.utils.rx.RxSchedulersAbstractBase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class NewsModule {

    private Context applicationContext;

    public NewsModule(Context context) {
        applicationContext = context;
    }

    @Provides
    @Singleton
    Context providesApplicationContext() {
        return applicationContext;
    }

    @Singleton
    @Provides
    public NewsDataBase getNewsListDB(Context context) {

        NewsDataBase database = Room.databaseBuilder(context,
                NewsDataBase.class, "news_room_db")
                .build();
        return database;
    }

    @Provides
    public RxSchedulersAbstractBase provideRxScheduler() {
        return new RxSchedulers();
    }

    @Provides
    @Singleton
    public NewsListDao getNewsListDao(NewsDataBase database) {
        return database.getNewsListDao();
    }

    @Provides
    public NewsListInteractor provideNewsListInteractor(NewsApiService newsApiService) {
        return new NewsListInteractor(newsApiService);
    }

    @Provides
    public NewsListPresenter provideNewListPresenter(NewsListInteractor interactor, NewsListDao newsListDao, RxSchedulersAbstractBase rxSchedulers) {
        return new NewsListPresenter(interactor, newsListDao, rxSchedulers);
    }
}
