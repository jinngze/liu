package com.example.dell.jingze2019102.callback;

public interface MyCallBack<T> {

    void success(T data);
    void failed(Exception e);
}
