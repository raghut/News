package com.rgu.news.ui.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.rgu.news.R;
import com.rgu.news.databinding.ActivityNewsDetailsBinding;

import java.util.Objects;

public class NewsDetailsActivity extends AppCompatActivity {

    public static final String DETAILS_URL = "details_url";
    public static final String DETAILS_TITLE = "details_title";
    private ProgressDialog progressDialog;

    public static void startActivity(Context context, String detailsUrl, String name) {
        Bundle bundle = new Bundle();
        bundle.putString(DETAILS_URL, detailsUrl);
        bundle.putString(DETAILS_TITLE, name);

        Intent intent = new Intent(context, NewsDetailsActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityNewsDetailsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_news_details);


        String url = Objects.requireNonNull(getIntent().getExtras()).getString(DETAILS_URL);
        String title = Objects.requireNonNull(getIntent().getExtras()).getString(DETAILS_TITLE);

        setTitle(title);

        binding.detailsWv.setBackgroundColor(0);
        binding.detailsWv.getSettings().setJavaScriptEnabled(true);
        showProgressBar();
        binding.detailsWv.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                hideProgressBar();
            }

        });

        binding.detailsWv.loadUrl(url);
    }

    //Can be move to Base activity for Listing and Details activity
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
}
