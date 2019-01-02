package com.example.dell.jingze2019102.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;

import com.example.dell.jingze2019102.Apis;
import com.example.dell.jingze2019102.R;
import com.example.dell.jingze2019102.adapter.MyAdapter;
import com.example.dell.jingze2019102.adapter.YouAdapter;
import com.example.dell.jingze2019102.bean.Bean;
import com.example.dell.jingze2019102.bean.ShopBean;
import com.example.dell.jingze2019102.presenter.ShowPresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SecondActivity  extends AppCompatActivity implements IView {

    private ShowPresenter showPresenter;
    private RecyclerView recyclerView;
    private YouAdapter adapter;
    private int mpage;
    private List<ShopBean.DataBean> listBeans = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        showPresenter = new ShowPresenter(this);
        initView();
        initData();
    }


    private void initView() {

        recyclerView = findViewById(R.id.view);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);


       /* GridLayoutManager gridLayoutManager = new GridLayoutManager(SecondActivity.this, 3);
        gridLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);*/

        adapter = new YouAdapter(this,listBeans);
        recyclerView.setAdapter(adapter);



    }


    private void initData() {

        Map<String,String> map = new HashMap<>();
        map.put("mpage","1");
        showPresenter.startRequest(Apis.URL_Z,map,ShopBean.class);



    }
    @Override
    public void showResponseData(Object data) {

        if (data instanceof ShopBean) {
            ShopBean shops = (ShopBean) data;
         /*  listBeans = shops.getData();
            adapter.setGood(listBeans);*/


        }
    }

    @Override
    public void showResponseFail(Object data) {

    }

    //防止内存泄漏
    @Override
    protected void onDestroy() {
        super.onDestroy();
        showPresenter.onDetach();

    }
}
