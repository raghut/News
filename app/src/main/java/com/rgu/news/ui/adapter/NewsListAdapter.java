package com.rgu.news.ui.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.rgu.news.R;
import com.rgu.news.data.model.NewsListDataResponse.NewsItem;
import com.rgu.news.databinding.ItemNewsAdapterLayoutBinding;
import com.rgu.news.ui.viewholder.NewsListItemViewHolder;

import java.util.List;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListItemViewHolder> {

    private List<NewsItem> newsList;

    public NewsListAdapter(List<NewsItem> newsItems) {

        newsList = newsItems;
    }

    @NonNull
    @Override
    public NewsListItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int pos) {
        ItemNewsAdapterLayoutBinding binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.item_news_adapter_layout, viewGroup, false);
        return new NewsListItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsListItemViewHolder newsListItemViewHolder, int pos) {
        newsListItemViewHolder.bindData(newsList.get(pos));
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }
}
