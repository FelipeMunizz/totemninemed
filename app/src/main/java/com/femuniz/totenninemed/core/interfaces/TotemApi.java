package com.femuniz.totenninemed.core.interfaces;

import com.femuniz.totenninemed.core.model.SenhaToten;
import com.femuniz.totenninemed.core.model.Toten;
import com.femuniz.totenninemed.core.model.response.RetornoGenerico;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface TotemApi {
    @GET("ListaTotensClinica/{idClinica}")
    Call<List<Toten>> listaTotensClinica(@Path("idClinica") int idClinica);

    @POST("AdicionarSenhaToten")
    Call<RetornoGenerico<SenhaToten>> AdicionarSenhaToten(@Body SenhaToten senhaToten);
}
