package com.example.dell.jingze2019102.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.dell.jingze2019102.Apis;
import com.example.dell.jingze2019102.R;
import com.example.dell.jingze2019102.adapter.MyAdapter;
import com.example.dell.jingze2019102.bean.Bean;
import com.example.dell.jingze2019102.presenter.IPresenter;
import com.example.dell.jingze2019102.presenter.ShowPresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements IView {

    private ShowPresenter showPresenter;
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private int mpage;
    private List<Bean.DataBean.MiaoshaBean.ListBean> listBeans = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showPresenter = new ShowPresenter(this);
        initView();
        initData();

        adapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, String title) {
                Toast.makeText(MainActivity.this, "点击了" + (title) + "数据", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
                finish();

            }
        });

        adapter.setLongItemClickListeners(new MyAdapter.OnLongItemClickListener() {
            @Override
            public void onItemLongClick(int i) {
                showAlertDialog(i);
            }
        });
    }

    private void initData() {

        Map<String, String> map = new HashMap<>();
        map.put("mpage", "1");
        showPresenter.startRequest(Apis.URL_S, map, Bean.class);
    }


    private void initView() {

        recyclerView = findViewById(R.id.recyle);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 3);
        gridLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);

        adapter = new MyAdapter(this, listBeans);
        recyclerView.setAdapter(adapter);


    }

    private void showAlertDialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("提示：");
        builder.setMessage("请问你要删除吗?");
        builder.setIcon(R.mipmap.ic_launcher_round);
        //点击对话框以外的区域是否让对话框消失
        builder.setCancelable(false);
        //设置正面按钮
        builder.setPositiveButton("是的", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                adapter.deleteItem(position);
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });


        builder.setNegativeButton("取消", null);
        builder.show();


    }

    @Override
    public void showResponseData(Object data) {

        if (data instanceof Bean) {
            Bean beans = (Bean) data;
            listBeans = beans.getData().getMiaosha().getList();
            adapter.setData(listBeans);


        }
    }

    @Override
    public void showResponseFail(Object data) {

    }

    //防止内存泄漏
    @Override
    protected void onDestroy () {
        super.onDestroy();
        showPresenter.onDetach();

    }
}
