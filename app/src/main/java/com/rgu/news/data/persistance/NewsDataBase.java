package com.rgu.news.data.persistance;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {NewsTable.class}, exportSchema = false, version = 1)
public abstract class NewsDataBase extends RoomDatabase {

    public abstract NewsListDao getNewsListDao();

}
