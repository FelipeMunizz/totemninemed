package com.femuniz.totenninemed.core.service;

import android.content.Context;

import androidx.annotation.NonNull;

import com.femuniz.totenninemed.core.interfaces.IApiCallback;
import com.femuniz.totenninemed.core.interfaces.IClinicAPI;
import com.femuniz.totenninemed.core.model.Clinica;
import com.femuniz.totenninemed.core.model.Toten;
import com.femuniz.totenninemed.core.model.UrlApiNineMed;
import com.femuniz.totenninemed.helpers.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ClinicService {
    private IClinicAPI _clinicApi;

    public ClinicService(Context context){
        Retrofit retrofit = RetrofitClient.GetClient(UrlApiNineMed.Clinic, context);
        _clinicApi = retrofit.create(IClinicAPI.class);
    }

    public void ListClinicUser(String emailUser, final IApiCallback<List<Clinica>> callback){
        Call<List<Clinica>> call = _clinicApi.ListaClinicasUsuario(emailUser);
        call.enqueue(new Callback<List<Clinica>>() {
            @Override
            public void onResponse(@NonNull Call<List<Clinica>> call, @NonNull Response<List<Clinica>> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure(new Exception("Erro na resposta"));
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Clinica>> call, @NonNull Throwable t) {
                callback.onFailure(t);
            }
        });
    }
}
