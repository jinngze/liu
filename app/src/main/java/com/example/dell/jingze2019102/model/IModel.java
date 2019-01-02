package com.example.dell.jingze2019102.model;

import com.example.dell.jingze2019102.callback.MyCallBack;

import java.util.Map;

public interface IModel {

    void requestData(String url, Map<String,String> params, Class clazz, MyCallBack callBack);
}
