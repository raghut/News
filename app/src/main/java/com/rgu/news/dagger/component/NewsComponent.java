package com.rgu.news.dagger.component;

import com.rgu.news.dagger.module.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class})
public interface NewsComponent {
}
