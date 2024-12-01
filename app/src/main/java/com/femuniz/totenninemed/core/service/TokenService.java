package com.femuniz.totenninemed.core.service;

import android.content.Context;
import android.content.SharedPreferences;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

public class TokenService {
    private static final String SHARED_PREFS_NAME = "TokenPrefs";
    private static final String TOKEN_KEY = "auth_token";
    private SharedPreferences _sharedPreferences;

    public TokenService(Context context){
        _sharedPreferences = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE);
    }

    public void saveToken(String token){
        SharedPreferences.Editor editor = _sharedPreferences.edit();
        editor.putString(TOKEN_KEY, token);
        editor.apply();
    }

    public String getToken(){
        return _sharedPreferences.getString(TOKEN_KEY,null);
    }

    public boolean isValidToken(){
        String token = getToken();
        if(token == null || token.isEmpty())
            return false;

        try{
            DecodedJWT decodedJWT = JWT.decode(token);
            long currentTime = System.currentTimeMillis() / 1000;
            return decodedJWT.getExpiresAt().getTime() > currentTime;
        }
        catch (JWTDecodeException e){
            return false;
        }
    }

    public void removeToken(){
        SharedPreferences.Editor editor = _sharedPreferences.edit();
        editor.remove(TOKEN_KEY);
        editor.apply();
    }
}
