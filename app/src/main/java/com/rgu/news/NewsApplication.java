package com.rgu.news;

import android.app.Application;

import com.rgu.news.dagger.component.DaggerNewsComponent;
import com.rgu.news.dagger.component.NewsComponent;
import com.rgu.news.dagger.module.NewsModule;


public class NewsApplication extends Application {

    private NewsComponent newsComponent;
    private static NewsApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        sInstance = this;
        initDependencies();
    }

    private void initDependencies() {
        newsComponent = DaggerNewsComponent.builder()
                .newsModule(new NewsModule(this))
                .build();
    }

    public static NewsApplication getsInstance() {
        return sInstance;
    }

    public NewsComponent getNewsComponent() {
        return newsComponent;
    }
}
