package com.example.dell.jingze2019102.view;

public interface IView<T> {

    void showResponseData(T data);
    void showResponseFail(T data);

}
