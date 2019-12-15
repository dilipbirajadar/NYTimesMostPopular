package com.nytimesmostpopular.api;


import com.nytimesmostpopular.utilites.LogUtils;

import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Dilip Birajadar on 2019-12-14.
 */
public class ApiInteractorImpl implements ApiInteractor {

    /**
     * @param <T>
     *
     *     Here we will get response of each and every API.
     *
     *     onResponse will fire when we will get SUCCESS response
     *     onFailure will fire when we will get ERROR response
     */

    public static class FNApiCallback<T> implements Callback<T> {
        private static final String TAG = FNApiCallback.class.getName();
        private OnApiResultCallback callback;

        public FNApiCallback(OnApiResultCallback callback) {
            this.callback = callback;
        }

        @Override
        public void onResponse(retrofit2.Call<T> call, Response<T> response) {
            if (callback != null) {
                if (response.isSuccessful()) {
                    LogUtils.debug(TAG, "Request: " + call.request().url().encodedPath() +":: Response DATA :: " + (response.body() == null? null : response.body().toString()));
                    callback.onCompleted(response.body(),response.code());


                } else {

                    LogUtils.debug(TAG, "Request: " + call.request().url().encodedPath() +":: Response ERROR DATA :: " + (response.errorBody() == null? null : response.errorBody().toString()));
                    callback.onError("Error occurred",response.code(), new FNException());
                }
            }
        }

        @Override
        public void onFailure(retrofit2.Call<T> call, Throwable t) {
            LogUtils.debug(TAG, "Request: " + call.request().url().encodedPath() + ":: Error :: " + t.getMessage());
            t.printStackTrace();

            if (callback != null) {
                callback.onError(t.getMessage(), 1, t);
            }
        }
    }

    static class FNException extends Exception {
        FNException() {
            super();
        }
    }


}
