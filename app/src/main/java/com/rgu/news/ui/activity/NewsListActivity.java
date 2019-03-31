package com.rgu.news.ui.activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.rgu.news.NewsApplication;
import com.rgu.news.R;
import com.rgu.news.data.model.NewsListDataResponse;
import com.rgu.news.databinding.ActivityNewsListLayoutBinding;
import com.rgu.news.ui.adapter.NewsListAdapter;
import com.rgu.news.ui.contract.NewViewPresenterContract;
import com.rgu.news.ui.presenter.NewsListPresenter;

import javax.inject.Inject;

public class NewsListActivity extends AppCompatActivity implements NewViewPresenterContract.IView {

    private ActivityNewsListLayoutBinding newsListActivityBinding;

    @Inject
    NewsListPresenter presenter;









    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newsListActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_news_list_layout);
        NewsApplication.getsInstance().getNewsComponent().inject(this);

        initViews();

        presenter.fetchNewsList();
    }

    private void initViews() {
        presenter.onAttachView(this);
        newsListActivityBinding.newsListRv.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void setNewsListData(NewsListDataResponse newsListDataResponse) {
        if (newsListDataResponse.newsList != null && !newsListDataResponse.newsList.isEmpty()) {
            newsListActivityBinding.newsListRv.setAdapter(new NewsListAdapter(newsListDataResponse.newsList));
        } else {
            Toast.makeText(this, getString(R.string.no_news_found_message), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (presenter != null) {
            presenter.onDetachView();
        }
    }
}
