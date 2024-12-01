package com.femuniz.totenninemed.core.model.response;

public class RetornoToken {
    private String token;
    private String email;

    public RetornoToken(){

    }

    public String getToken(){
        return this.token;
    }

    public String getEmail(){
        return this.email;
    }

    public void setToken(String token){
        this.token = token;
    }

    public void setEmail(String email){
        this.email = email;
    }
}
