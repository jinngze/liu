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
import com.example.dell.jingze2019102.bean.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

import static java.util.regex.Pattern.*;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.SubViewHolder> {


    Context context;
    List<Bean.DataBean.MiaoshaBean.ListBean> beanList;
    private OnItemClickListener onItemClickListener;
    private OnLongItemClickListener mLongItemClickListener;


    public interface OnLongItemClickListener {
        void onItemLongClick(int i);
    }


    public interface OnItemClickListener{

           void onItemClick(int position,String title);
       }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setLongItemClickListeners(OnLongItemClickListener longItemClickListener) {
        this.mLongItemClickListener = longItemClickListener;
    }

    public MyAdapter(Context context, List<Bean.DataBean.MiaoshaBean.ListBean> list) {
        this.context = context;
        beanList = new ArrayList<>();
    }

    public void setData(List<Bean.DataBean.MiaoshaBean.ListBean> setlist) {
        this.beanList = setlist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SubViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View inflate = LayoutInflater.from(context).inflate(R.layout.item, viewGroup, false);

        SubViewHolder subViewHolder = new SubViewHolder(inflate);
        return subViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SubViewHolder subViewHolder, final int i) {

        subViewHolder.text1.setText(beanList.get(i).getTitle());
        subViewHolder.text2.setText(beanList.get(i).getPrice()+"");
        Glide.with(context).load(beanList.get(i).getImages()).into(subViewHolder.image);

        String images = beanList.get(i).getImages();
        Pattern pen = compile("\\|");
        String [] img = pen.split(images);

        Glide.with(context).load(img[0]).into(subViewHolder.image);

        //点击事件
      subViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              if(onItemClickListener != null){

                  onItemClickListener.onItemClick(i,beanList.get(i).getTitle());
              }
          }
      });

        //长按
        subViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (mLongItemClickListener != null){
                    mLongItemClickListener.onItemLongClick(i);
                }
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return beanList.size();
    }

    //删除
    public void deleteItem(int poistion){
        beanList.remove(poistion);
        notifyItemRemoved(poistion);
    }


    class SubViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.text1)
        TextView text1;
        @BindView(R.id.text2)
        TextView text2;
        public SubViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(this);
        }

         @Override
         public void onClick(View v){

            int position = getLayoutPosition();

            onItemClickListener.onItemClick(position,beanList.get(position).getTitle());
         }
    }
}
