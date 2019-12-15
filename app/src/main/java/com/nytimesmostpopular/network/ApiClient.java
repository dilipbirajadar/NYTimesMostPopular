package com.nytimesmostpopular.network;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nytimesmostpopular.BuildConfig;
import com.nytimesmostpopular.utilites.LogUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CertificatePinner;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Dilip Birajadar on 2019-12-14.
 */
public class ApiClient {
    public static final String BASE_URL = "https://api.nytimes.com";

    private static Retrofit retrofit = null;
    private static final String TAG = ApiClient.class.getName();

    /*api network call*/
    private static Retrofit getAppClient(final String url) {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

        if (BuildConfig.DEBUG) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        }
        if (retrofit == null) {

        String domain = "*.nytimes.com";


        /**
         * pining is implemented on public leaf certificate
         */
        CertificatePinner certificatePinner = new CertificatePinner.Builder()
                .add(domain, "sha256/uG9VdEOvi68/8A2Zzkmo7hZbkbKiWE8aOJxaFrBXxj0=")
                .add(domain, "sha256/klO23nT2ehFDXCfx3eHTDRESMz3asj1muO+4aIdjiuY=")
                .add(domain, "sha256/grX4Ta9HpZx6tSHkmCrvpApTQGo67CYDnvprLg5yRME=")
                .build();

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.interceptors().add(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                final okhttp3.Response response = chain.proceed(chain.request());
                LogUtils.debug("Response", " : " + response);
                return response;
            }
        });


        httpClient.connectTimeout(60 * 5, TimeUnit.SECONDS)
                .readTimeout(60 * 5, TimeUnit.SECONDS)
                .writeTimeout(60 * 5, TimeUnit.SECONDS);

        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                // Request customization: add request headers
                Request.Builder requestBuilder = original.newBuilder();

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });

        OkHttpClient client = httpClient.certificatePinner(certificatePinner).build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(url)
                .addConverterFactory(new StringConverterFactory())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }
        return retrofit;


    }

    private static <T> T createAppApiClientService(Class<T> cls) {
        return getAppClient(getSelectedBaseUrl()).create(cls);

    }


    public static ApiInterface appApiService() {
        return createAppApiClientService(ApiInterface.class);
    }


    private static String getSelectedBaseUrl(){
        return ApiClient.BASE_URL;
    }



}
