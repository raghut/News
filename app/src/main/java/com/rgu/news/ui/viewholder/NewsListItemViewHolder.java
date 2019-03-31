package com.rgu.news.ui.viewholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.rgu.news.data.model.NewsListDataResponse;
import com.rgu.news.databinding.ItemNewsAdapterLayoutBinding;
import com.rgu.news.ui.activity.NewsDetailsActivity;

public class NewsListItemViewHolder extends RecyclerView.ViewHolder {
    private ItemNewsAdapterLayoutBinding binding;

    public NewsListItemViewHolder(@NonNull ItemNewsAdapterLayoutBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bindData(NewsListDataResponse.NewsItem newsItem) {
        binding.titleTv.setText(newsItem.title);
        binding.descTv.setText(newsItem.desc);

        binding.parentViewCv.setOnClickListener(view -> {
            NewsDetailsActivity.startActivity(itemView.getContext(), newsItem.url, newsItem.source.name);
        });
    }
}
