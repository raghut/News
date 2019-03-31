package com.rgu.news.ui.contract;

import com.rgu.news.data.model.NewsListDataResponse;

public interface NewsListViewPresenterContract {

    interface IView {

        void setNewsListData(NewsListDataResponse newsListDataResponse);

        void onFetchNewsListError(Throwable throwable);
    }

    interface IPresenter {
        void onAttachView(IView view);

        void onDetachView();

        void fetchTopHeadLines();
    }
}
