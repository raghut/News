package com.rgu.news.ui.contract;

import com.rgu.news.data.model.NewsListDataResponse;

public interface NewViewPresenterContract {

    interface IView {

        void setNewsListData(NewsListDataResponse newsListDataResponse);

    }

    interface IPresenter {
        void onAttachView(IView view);

        void onDetachView();

        void fetchNewsList();
    }
}
