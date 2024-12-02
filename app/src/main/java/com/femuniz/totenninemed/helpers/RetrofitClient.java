package com.femuniz.totenninemed.helpers;

import android.content.Context;

import androidx.annotation.NonNull;

import com.femuniz.totenninemed.core.service.TokenService;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static Retrofit GetClient(String baseUrl, Context context){
        TokenService tokenService = new TokenService(context);
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(createOkHttpClient(tokenService.getToken()))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private static OkHttpClient createOkHttpClient(String token) {
        return new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @NonNull
                    @Override
                    public Response intercept(@NonNull Chain chain) throws IOException {
                        Request originalRequest = chain.request();

                        Request newRequest = originalRequest.newBuilder()
                                .header("Authorization", "Bearer " + token)
                                .build();

                        return chain.proceed(newRequest);
                    }
                })
                .build();
    }
}
