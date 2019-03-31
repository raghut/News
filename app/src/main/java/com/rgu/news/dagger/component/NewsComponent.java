package com.rgu.news.dagger.component;

import com.rgu.news.dagger.module.NetworkModule;
import com.rgu.news.dagger.module.NewsModule;
import com.rgu.news.ui.activity.NewsListActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class, NewsModule.class})
public interface NewsComponent {

    void inject(NewsListActivity activity);
}
