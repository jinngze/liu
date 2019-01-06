package com.example.liu;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.title)
    TitleBarView title;
    @BindView(R.id.fl_search)
    WeekFlowLayout flSearch;
    @BindView(R.id.fl_hot)
    WeekFlowLayout flHot;
    @BindView(R.id.fl_bottom)
    WeekFlowLayout flBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        init();
    }

    private void init() {

        title.setButttonClickListener(new TitleBarView.OnButttonClickListener() {
            @Override
            public void onButtonClick(final String str) {

                UUID uuid = UUID.randomUUID();
                Log.i("dj","创建时的UUID："+ uuid +",字符串是:" + str );
                TextView tv = new TextView(MainActivity.this);
                tv.setTag(uuid);
                tv.setTextColor(Color.RED);
                tv.setText(str);
                tv.setBackgroundResource(R.drawable.edit_bg);
                flSearch.addView(tv);

                tv.setOnClickListener(new View.OnClickListener() {
                    @SuppressLint("WrongConstant")
                    @Override
                    public void onClick(View view) {

                        String uuid = String.valueOf(view.getTag());
                        Log.i("dj","点击时的UUID："+ uuid +",字符串是:" + str);
                        Toast.makeText(MainActivity.this,"点击了"+(str)+"数据",0).show();
                    }
                });
            }
        });

        for (int i = 0;  i<15; i++){
            TextView tv = new TextView(MainActivity.this);
            tv.setText("数据" + i);
            tv.setTextColor(Color.GREEN);
            tv.setBackgroundResource(R.drawable.edit_bg);
            flHot.addView(tv);
        }

        for (int i = 0;  i<15; i++){
            TextView tv = new TextView(MainActivity.this);
            tv.setText("数据" + i);
            tv.setTextColor(Color.BLUE);
            tv.setBackgroundResource(R.drawable.edit_bg);
            flBottom.addView(tv);
        }

    }
}
