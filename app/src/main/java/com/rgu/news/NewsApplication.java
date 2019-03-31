package com.rgu.news;

import android.app.Application;

import com.rgu.news.dagger.component.DaggerNewsComponent;
import com.rgu.news.dagger.component.NewsComponent;


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
        newsComponent = DaggerNewsComponent.create();
    }

    public static NewsApplication getsInstance() {
        return sInstance;
    }

    public NewsComponent getNewsComponent() {
        return newsComponent;
    }
}
