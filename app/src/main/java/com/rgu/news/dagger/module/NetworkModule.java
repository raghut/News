package com.rgu.news.dagger.module;

import android.util.Log;

import com.rgu.news.BuildConfig;
import com.rgu.news.network.service.NewsApiService;
import com.rgu.news.utils.NetworkUtil;

import java.util.logging.Level;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {
    public static final String TAG = NetworkModule.class.getSimpleName();

    @Provides
    public Retrofit getRetrofitInstance(OkHttpClient client, GsonConverterFactory converterFactory,
                                        RxJava2CallAdapterFactory rxJava2CallAdapterFactory) {
        return new Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .addConverterFactory(converterFactory)
                .client(client)
                .build();
    }

    @Provides
    public GsonConverterFactory getGsonConverterFactory() {
        return GsonConverterFactory.create();
    }

    @Provides
    public RxJava2CallAdapterFactory getRxAdaptorFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    @Provides
    public OkHttpClient getHttpClient(OkHttpClient.Builder builder) {
        return builder.build();
    }

    @Provides
    public OkHttpClient.Builder getHttpClientBuilder() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(
                message -> Log.d(TAG, message));
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        builder.addInterceptor(httpLoggingInterceptor);

        return builder;
    }

    @Provides
    @Singleton
    public NewsApiService getApiHelper(Retrofit retrofit) {
        return retrofit.create(NewsApiService.class);
    }
}
