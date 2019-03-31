package com.rgu.news.ui.activity;

import android.app.ProgressDialog;
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
    private NewsListAdapter newsListAdapter;
    private ProgressDialog progressDialog;

    @Inject
    NewsListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newsListActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_news_list_layout);
        NewsApplication.getsInstance().getNewsComponent().inject(this);

        initViews();

        fetchTopHeadLines();
    }

    private void initViews() {
        setTitle(R.string.head_lines_lable);
        presenter.onAttachView(this);

        newsListActivityBinding.newsListRv.setLayoutManager(new LinearLayoutManager(this));
        newsListAdapter = new NewsListAdapter();
        newsListActivityBinding.newsListRv.setAdapter(newsListAdapter);
    }

    @Override
    public void setNewsListData(NewsListDataResponse newsListDataResponse) {
        hideProgressBar();
        if (newsListDataResponse.newsList != null && !newsListDataResponse.newsList.isEmpty()) {
            newsListAdapter.updateList(newsListDataResponse.newsList);
        } else {
            Toast.makeText(this, getString(R.string.no_news_found_message), Toast.LENGTH_SHORT).show();
        }
    }

    private void fetchTopHeadLines() {
        showProgressBar();
        presenter.fetchTopHeadLines();
    }

    private void showProgressBar() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage(getString(R.string.loading_message));
        }
        progressDialog.show();
    }

    private void hideProgressBar() {
        if (progressDialog != null) {
            progressDialog.hide();
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
