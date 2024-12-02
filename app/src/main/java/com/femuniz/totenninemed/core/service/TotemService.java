package com.femuniz.totenninemed.core.service;

import android.content.Context;

import androidx.annotation.NonNull;

import com.femuniz.totenninemed.core.interfaces.ApiCallback;
import com.femuniz.totenninemed.core.interfaces.TotemApi;
import com.femuniz.totenninemed.core.model.SenhaToten;
import com.femuniz.totenninemed.core.model.Toten;
import com.femuniz.totenninemed.core.model.UrlApiNineMed;
import com.femuniz.totenninemed.core.model.response.RetornoGenerico;
import com.femuniz.totenninemed.core.model.response.RetornoToken;
import com.femuniz.totenninemed.helpers.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TotemService {
    private TotemApi _totemApi;

    public TotemService(Context context){
        Retrofit retrofit = RetrofitClient.GetClient(UrlApiNineMed.Totem, context);
        _totemApi = retrofit.create(TotemApi.class);
    }

    public void ListaTotensClinica(int idClinica, final ApiCallback<List<Toten>> callback) {
        Call<List<Toten>> call = _totemApi.listaTotensClinica(idClinica);

        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<List<Toten>> call, @NonNull Response<List<Toten>> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure(new Exception("Erro na resposta"));
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Toten>> call, @NonNull Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    public void AdicionaSenhaToten(SenhaToten senhaToten, final ApiCallback<RetornoGenerico<SenhaToten>> callback){
        Call<RetornoGenerico<SenhaToten>> call = _totemApi.AdicionarSenhaToten(senhaToten);

        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<RetornoGenerico<SenhaToten>> call, @NonNull Response<RetornoGenerico<SenhaToten>> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure(new Exception("Erro na resposta"));
                }
            }

            @Override
            public void onFailure(@NonNull Call<RetornoGenerico<SenhaToten>> call, @NonNull Throwable t) {
                callback.onFailure(t);
            }
        });
    }
}
