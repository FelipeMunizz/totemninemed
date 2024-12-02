package com.femuniz.totenninemed.core.interfaces;

public interface ApiCallback<T> {
    void onSuccess(T result);
    void onFailure(Throwable throwable);
}