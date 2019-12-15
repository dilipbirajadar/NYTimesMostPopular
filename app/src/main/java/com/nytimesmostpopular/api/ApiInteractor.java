package com.nytimesmostpopular.api;


/**
 * Created by Dilip Birajadar on 2019-12-14.
 */
public interface ApiInteractor {

    interface OnApiResultCallback<T> {
        void onCompleted(T t, int code);
        void onError(String errMsg, int code, Throwable t);
        void onNext(T t);

    }
}
