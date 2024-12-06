package com.femuniz.totenninemed.core.interfaces;

import com.femuniz.totenninemed.core.model.Clinica;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IClinicAPI {
    @GET("ListaClinicasUsuario")
    Call<List<Clinica>> ListaClinicasUsuario(@Query("email") String email) ;

}
