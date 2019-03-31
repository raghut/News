package com.rgu.news.ui.presenter;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.rgu.news.BuildConfig;
import com.rgu.news.data.model.NewsListDataResponse;
import com.rgu.news.data.persistance.NewsListDao;
import com.rgu.news.data.persistance.NewsTable;
import com.rgu.news.enums.CategoryType;
import com.rgu.news.ui.contract.NewViewPresenterContract;
import com.rgu.news.ui.interactor.NewsListInteractor;
import com.rgu.news.utils.NetworkUtil;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class NewsListPresenter implements NewViewPresenterContract.IPresenter {
    private static final String TAG = NewsListPresenter.class.getSimpleName();

    private NewsListInteractor newListInteractor;
    private NewsListDao newsListDao;
    private NewViewPresenterContract.IView newsView;
    private CompositeDisposable compositeDisposable;

    public NewsListPresenter(NewsListInteractor interactor, NewsListDao newsListDao) {
        newListInteractor = interactor;
        this.newsListDao = newsListDao;
    }

    @Override
    public void onAttachView(NewViewPresenterContract.IView view) {
        newsView = view;
        compositeDisposable = new CompositeDisposable();
    }


    @Override
    public void fetchTopHeadLines() {
        fetchFromCache(CategoryType.TOP_HEADLINES);
        compositeDisposable.add(newListInteractor.fetchTopHeadLines(getRequestParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(newsListDataResponse -> {
                            Log.d(TAG, "fetchTopHeadLines onNext");
                            handleNewsListResponse(newsListDataResponse);
                            insertIntoDB(CategoryType.TOP_HEADLINES, newsListDataResponse);
                        },
                        throwable -> {
                            Log.d(TAG, "fetchTopHeadLines failed with: " + throwable.getMessage());
                        }));
    }

    private void fetchFromCache(@CategoryType int topHeadlines) {
        compositeDisposable.add(newsListDao.getNewsListByCategory(topHeadlines)
                .firstElement()
                .toObservable()
                .subscribeOn(Schedulers.io())
                .switchMap(newsListJson -> {
                    Log.d(TAG, "fetchFromCache switchMap: " + newsListJson);
                    return Observable.just(NetworkUtil.fromJson(newsListJson, NewsListDataResponse.class));
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(newsListDataResponse -> {
                            Log.d(TAG, "fetchFromCache onNext");
                            handleNewsListResponse(newsListDataResponse);
                        },
                        throwable -> {
                            Log.e(TAG, "fetchFromCache error: " + throwable.getMessage());
                        }
                ));
    }

    private void insertIntoDB(@CategoryType int categoryType, NewsListDataResponse newsListDataResponse) {
        NewsTable newsTable = new NewsTable();

        try {
            Log.d(TAG, "insertIntoDB");
            newsTable.setCategoryType(categoryType);
            newsTable.setNewsData(new Gson().toJson(newsListDataResponse));
            newsTable.setTimestamp(System.currentTimeMillis());
            compositeDisposable.add(Observable.just(1)
                    .subscribeOn(Schedulers.io())
                    .switchMap(integer -> {
                        newsListDao.delete(categoryType);
                        newsListDao.insertAll(newsTable);
                        return Observable.just(1);
                    })
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(integers -> {
                        Log.d(TAG, "insertIntoDB: " + integers);
                    }, throwable -> {
                        Log.e(TAG, "insertIntoDB " + throwable.getMessage());
                    }));
        } catch (JsonIOException ex) {
            Log.e(TAG, "insertIntoDB catch" + ex.getMessage());
        }

    }

    private void handleNewsListResponse(NewsListDataResponse newsListDataResponse) {
        Log.d(TAG, "handleNewsListResponse: " + newsListDataResponse);
        if (newsListDataResponse != null) {
            newsView.setNewsListData(newsListDataResponse);
        }
    }

    private Map<String, String> getRequestParams() {
        HashMap<String, String> map = new HashMap<>();
        map.put("country", "us");
        map.put("apiKey", BuildConfig.API_KEY);

        return map;
    }

    @Override
    public void onDetachView() {
        compositeDisposable.clear();
    }
}
