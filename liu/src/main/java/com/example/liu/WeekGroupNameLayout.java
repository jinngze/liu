package com.example.liu;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

@SuppressLint("AppCompatCustomView")
public class WeekGroupNameLayout extends TextView {

    public WeekGroupNameLayout(Context context) {
        super(context);
    }

    public WeekGroupNameLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.WeekFlowLayout);
        int color =  typedArray.getColor(R.styleable.WeekFlowLayout_textColor,Color.BLACK);

        setTextColor(color);

        //收回
        typedArray.recycle();
    }
}
