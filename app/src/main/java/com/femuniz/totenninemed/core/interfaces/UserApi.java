package com.femuniz.totenninemed.core.interfaces;

import com.femuniz.totenninemed.core.dto.LoginUserDTO;
import com.femuniz.totenninemed.core.model.response.RetornoToken;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserApi {
    @POST("CreateToken")
    Call<RetornoToken> CreateToken(@Body LoginUserDTO model);
}
