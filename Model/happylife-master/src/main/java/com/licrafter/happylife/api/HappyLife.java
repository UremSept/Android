package com.licrafter.happylife.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.licrafter.happylife.AppAplication;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by Administrator on 2016/2/15.
 */
public class HappyLife {

    private static HappyLife happyInstance;
    private final HappyService happyService;

    public static HappyLife getInstance() {
        if (happyInstance == null) {
            happyInstance = new HappyLife();
        }
        return happyInstance;
    }

    public HappyLife() {
        OkHttpClient client = new OkHttpClient();
        client.setReadTimeout(12, TimeUnit.SECONDS);

        if (AppAplication.debug) {
            client.interceptors().add(interceptor);
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            client.interceptors().add(interceptor);
        }

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .serializeNulls()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HappyService.BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
        this.happyService = retrofit.create(HappyService.class);
    }

    public HappyService getHappyService() {
        return happyService;
    }

    Interceptor interceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request newRequest = chain.request().newBuilder()
                    .addHeader("X-LC-Id", ApiKey.X_LC_Id)
                    .addHeader("X-LC-Key", ApiKey.X_LC_Key)
                    .addHeader("Content-Type","application/json")
                    .build();
            return chain.proceed(newRequest);
        }
    };

}
