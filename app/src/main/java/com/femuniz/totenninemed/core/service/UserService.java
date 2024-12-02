package com.femuniz.totenninemed.core.service;

import android.content.Context;

import com.femuniz.totenninemed.core.dto.LoginUserDTO;
import com.femuniz.totenninemed.core.interfaces.ApiCallback;
import com.femuniz.totenninemed.core.interfaces.UserApi;
import com.femuniz.totenninemed.core.model.response.RetornoToken;
import com.femuniz.totenninemed.core.model.UrlApiNineMed;
import com.femuniz.totenninemed.helpers.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UserService{
    private UserApi _userApi;

    public UserService(Context context){
        Retrofit retrofit = RetrofitClient.GetClient(UrlApiNineMed.Account, context);

        _userApi = retrofit.create(UserApi.class);
    }

    public void Login(LoginUserDTO model, final ApiCallback<RetornoToken> callback){
        Call<RetornoToken> call = _userApi.CreateToken(model);

        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<RetornoToken> call, Response<RetornoToken> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure(new Exception("Erro na resposta"));
                }
            }

            @Override
            public void onFailure(Call<RetornoToken> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }
}
