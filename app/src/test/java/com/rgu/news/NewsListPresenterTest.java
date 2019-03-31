package com.rgu.news;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.runner.AndroidJUnit4;

import com.rgu.news.data.model.NewsListDataResponse;
import com.rgu.news.data.persistance.NewsDataBase;
import com.rgu.news.ui.contract.NewsListViewPresenterContract;
import com.rgu.news.ui.interactor.NewsListInteractor;
import com.rgu.news.ui.presenter.NewsListPresenter;
import com.rgu.news.utils.rx.RxSchedulersTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.robolectric.annotation.Config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.Mockito.mock;

@RunWith(AndroidJUnit4.class)
@Config(manifest = Config.NONE)
public class NewsListPresenterTest {

    private NewsListViewPresenterContract.IView newsListView;
    private NewsListInteractor newsListInteractor;
    private NewsDataBase newsDb;
    private NewsListPresenter newListPresenter;
    private HashMap<String, String> requestParamsMap;

    @Before
    public void setup() {
        newsListView = mock(NewsListViewPresenterContract.IView.class);
        newsListInteractor = mock(NewsListInteractor.class);

        newsDb = Room.inMemoryDatabaseBuilder(
                mock(Context.class),
                NewsDataBase.class).build();

        newListPresenter = new NewsListPresenter(newsListInteractor, newsDb.getNewsListDao(), new RxSchedulersTest(Schedulers.trampoline()));
        newListPresenter.onAttachView(newsListView);

        requestParamsMap = new HashMap<>();
        requestParamsMap.put("country", "us");
        requestParamsMap.put("apiKey", BuildConfig.API_KEY);
    }


    @Test
    public void testFetchNewsListLoadNetworkError() {
        Mockito.when(newsListInteractor.fetchTopHeadLines(requestParamsMap)).thenReturn(Observable.error(new IOException()));

        newListPresenter.fetchTopHeadLines();

        Mockito.verify(newsListView).onFetchNewsListError(ArgumentMatchers.any(IOException.class));
    }

    @Test
    public void testFetchNewsListSuccess() {
        NewsListDataResponse response = new NewsListDataResponse();

        response.newsList = new ArrayList<>();
        NewsListDataResponse.NewsItem newsItem = new NewsListDataResponse.NewsItem();
        newsItem.title = "sample title";
        newsItem.desc = "sample description";
        newsItem.url = "sample url";

        response.newsList.add(newsItem);

        Mockito.when(newsListInteractor.fetchTopHeadLines(requestParamsMap)).thenReturn(Observable.just(response));

        newListPresenter.fetchTopHeadLines();

        Mockito.verify(newsListView).setNewsListData(response);
    }

    @Test
    public void testCacheNewsEmpty() {
        //Todo: Need to implement
    }

    @Test
    public void testCacheNewsNonEmpty() {
        //Todo: Need to implement
    }

    @Test
    public void testUpdateNewsListIntoDbSuccess() {
         //Todo: Need to implement
    }


    @After
    public void tearDown() {
        newsDb.close();
    }
}
