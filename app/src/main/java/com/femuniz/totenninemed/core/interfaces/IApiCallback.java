package com.femuniz.totenninemed.core.interfaces;

public interface IApiCallback<T> {
    void onSuccess(T result);
    void onFailure(Throwable throwable);
}