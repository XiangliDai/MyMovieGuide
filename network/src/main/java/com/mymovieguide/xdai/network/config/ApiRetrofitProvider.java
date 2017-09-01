package com.mymovieguide.xdai.network.config;

import android.support.annotation.NonNull;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by xiangli on 7/7/17.
 */

public class ApiRetrofitProvider implements IApiRetrofitProvider {
    private static String TMDB_BASE_URL = "https://api.themoviedb.org/3/";

    private static final long TIMEOUT_LENGTH = 50;
    private static final long READ_WRITE_TIMEOUT_LENGTH = 50L;

    @NonNull
    public Retrofit getTMDBApiRetrofit() {
        Retrofit retrofit = getRetrofit(TMDB_BASE_URL);
        return retrofit;
    }

    @NonNull
    private Retrofit getRetrofit(String baseUrl) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = getOKHttpClient(logging);

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @NonNull
    private static OkHttpClient getOKHttpClient(HttpLoggingInterceptor logging) {
        return new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();
                        Request.Builder builder = original.newBuilder()
                                .method(original.method(), original.body());
                        Request request = builder.build();
                        return chain.proceed(request);
                    }
                })
                .addInterceptor(logging)
                .connectTimeout(TIMEOUT_LENGTH, TimeUnit.SECONDS)
                .readTimeout(READ_WRITE_TIMEOUT_LENGTH, TimeUnit.SECONDS)
                .writeTimeout(READ_WRITE_TIMEOUT_LENGTH, TimeUnit.SECONDS)
                .build();
    }

}
