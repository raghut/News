package com.rgu.news.ui.presenter;

import android.util.Log;

import com.rgu.news.BuildConfig;
import com.rgu.news.data.model.NewsListDataResponse;
import com.rgu.news.ui.contract.NewViewPresenterContract;
import com.rgu.news.ui.interactor.NewsListInteractor;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class NewsListPresenter implements NewViewPresenterContract.IPresenter {
    public static final String TAG = NewsListPresenter.class.getSimpleName();

    private NewsListInteractor newListInteractor;
    private NewViewPresenterContract.IView newsView;
    private CompositeDisposable compositeDisposable;

    public NewsListPresenter(NewsListInteractor interactor) {
        newListInteractor = interactor;
    }

    @Override
    public void onAttachView(NewViewPresenterContract.IView view) {
        newsView = view;
        compositeDisposable = new CompositeDisposable();
    }


    @Override
    public void fetchNewsList() {
        compositeDisposable.add(newListInteractor.fetchNewsList(getRequestParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleNewsListResponse,
                        throwable -> {
                            Log.d(TAG, "fetchNewsList failed with: " + throwable.getMessage());
                        }));
    }

    private void handleNewsListResponse(NewsListDataResponse newsListDataResponse) {
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
