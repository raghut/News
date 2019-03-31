package com.rgu.news.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.rgu.news.R;
import com.rgu.news.ui.contract.NewViewPresenterContract;

public class NewsListActivity extends AppCompatActivity implements NewViewPresenterContract.IView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {

    }
}
