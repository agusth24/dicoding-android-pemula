package com.example.prototype.catatanpohon.rest;

import com.example.prototype.catatanpohon.rest.services.KategoriService;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Retrofit;

public class ApiClient
{
    public static final String BASE_URL = "http://app-dev.unmul.ac.id/restful/public/";
    public static Retrofit retrofit = null;
    public static Retrofit getClient()
    {

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(
                        new Interceptor() {
                            @Override
                            public Response intercept(Chain chain) throws IOException {
                                Request request = chain.request().newBuilder().addHeader("Authorization", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6ImFndXMiLCJ1c2VybmFtZSI6ImFndXMiLCJpYXQiOjE1NjgyMTA3NTIsImV4cCI6MTU2ODIxNDM1Mn0.FSKbn_uFaqSajhE3jsHM3B1eN4AQVuVOQD9VUWZZzo0").build();
                                return chain.proceed(request);
                            }
                        }).build();
        if (retrofit == null)
        {
            retrofit = new Retrofit.Builder()
                    .baseUrl( BASE_URL )
                    .client( httpClient )
                    .addConverterFactory( GsonConverterFactory.create() )
                    .build();
        }
        return retrofit;
    }
}
