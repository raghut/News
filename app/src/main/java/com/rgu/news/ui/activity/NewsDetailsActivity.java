package com.rgu.news.ui.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
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
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Please wait...");
        progressDialog.show();
        binding.detailsWv.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) {
                try {
                    progressDialog.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();

                }
            }

        });

        binding.detailsWv.loadUrl(url);
    }
}
