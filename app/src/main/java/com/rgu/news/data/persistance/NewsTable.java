package com.rgu.news.data.persistance;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "newslist")
public class NewsTable {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "category_type")
    public int categoryType;

    @ColumnInfo(name = "newsjsondata")
    public String newsData;

    @ColumnInfo(name = "timestamp")
    public long timestamp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(int category) {
        this.categoryType = category;
    }

    public String getNewsData() {
        return newsData;
    }

    public void setNewsData(String newsData) {
        this.newsData = newsData;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
