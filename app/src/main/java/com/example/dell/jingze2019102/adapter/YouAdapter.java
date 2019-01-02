package com.example.dell.jingze2019102.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dell.jingze2019102.R;
import com.example.dell.jingze2019102.bean.ShopBean;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

import static java.util.regex.Pattern.compile;

public class YouAdapter extends RecyclerView.Adapter<YouAdapter.SubViewHolder> {


    Context context;
    List<ShopBean.DataBean> beanList;


    public YouAdapter(Context context, List<ShopBean.DataBean> list) {
        this.context = context;
        beanList = new ArrayList<>();
    }

    public void setGood(List<ShopBean.DataBean> youlist) {
        this.beanList = youlist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SubViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.ss, viewGroup, false);

        SubViewHolder subViewHolder = new SubViewHolder(inflate);

        return subViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SubViewHolder subViewHolder, int i) {

        subViewHolder.text3.setText(beanList.get(i).getTitle());
        subViewHolder.text4.setText(beanList.get(i).getPrice()+"");
        Glide.with(context).load(beanList.get(i).getImages()).into(subViewHolder.tu);

        String images = beanList.get(i).getImages();
        Pattern pen = compile("\\|");
        String [] img = pen.split(images);

        Glide.with(context).load(img[0]).into(subViewHolder.tu);
    }

    @Override
    public int getItemCount() {
        return beanList.size();
    }


    class SubViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tu)
        ImageView tu;
        @BindView(R.id.text3)
        TextView text3;
        @BindView(R.id.text4)
        TextView text4;


        public SubViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }


    }
}
