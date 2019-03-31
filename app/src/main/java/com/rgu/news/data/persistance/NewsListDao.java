package com.rgu.news.data.persistance;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.rgu.news.enums.CategoryType;

import io.reactivex.Flowable;


@Dao
public interface NewsListDao {

    @Query("SELECT newsjsondata FROM newslist WHERE category_type = :categoryType ORDER BY timestamp  DESC LIMIT 1")
    Flowable<String> getNewsListByCategory(@CategoryType int categoryType);

    @Insert
    long insertAll(NewsTable newsListData);

    @Query("DELETE FROM newslist WHERE category_type = :categoryType ")
    int delete(@CategoryType int categoryType);
}
