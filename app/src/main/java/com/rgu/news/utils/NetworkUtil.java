package com.rgu.news.utils;

import com.google.gson.GsonBuilder;

public final class NetworkUtil {

    public static <T> T fromJson(String json, Class<T> clazz) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create().fromJson(json, clazz);
    }

}
