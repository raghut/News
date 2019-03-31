package com.rgu.news.utils;

import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.SocketTimeoutException;

import retrofit2.HttpException;

public final class NetworkUtil {

    public static <T> T fromJson(String json, Class<T> clazz) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create().fromJson(json, clazz);
    }

    public static String getErrorType(Throwable e) {
        String errMsg = "Something went wrong";
        if (e instanceof SocketTimeoutException) {
            errMsg = "Timeout";
        } else if (e instanceof HttpException) {
            errMsg = ((HttpException) e).code() + " " + ((HttpException) e).message();
        } else if (e instanceof IOException) {
            errMsg = "Please connect to Internet";
        }
        return errMsg;
    }

}
