package com.femuniz.totenninemed.core.model.response;

public  class RetornoGenerico<T>
{
    public boolean success;
    public String message;
    public T result;
}