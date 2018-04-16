package com.xiaojun.xungengguanliyuan.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiaojun.xungengguanliyuan.R;
import com.xiaojun.xungengguanliyuan.beans.NameBeans;
import com.xiaojun.xungengguanliyuan.beans.RenBean;

import java.util.List;

/**
 * Created by Administrator on 2017/10/3.
 */

public class NameAdapter2 extends RecyclerView.Adapter<NameAdapter2.ViewHolder> {
    private List<NameBeans> datas;

   // private ClickIntface clickIntface;
//    public void setClickIntface(ClickIntface clickIntface){
//        this.clickIntface=clickIntface;
//    }

    public NameAdapter2(List<NameBeans> datas) {
        this.datas = datas;

    }
    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.name_item,viewGroup,false);
        return new ViewHolder(view);
    }
    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (NameBeans bean:datas){
                    bean.setTrue(false);
                }
                datas.get(position).setTrue(true);
                NameAdapter2.this.notifyDataSetChanged();
            }
        });
        if (datas.get(position).isTrue()){

            viewHolder.imageView.setImageResource(R.drawable.ic_selected);
        }else {
            viewHolder.imageView.setImageResource(R.drawable.ic_select);
        }
        viewHolder.name.setText(datas.get(position).getSs());
    }
    //获取数据的数量
    @Override
    public int getItemCount() {
        return datas.size();
    }
    //自定义的ViewHolder，持有每个Item的的所有界面元素
      class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView name;


        private ViewHolder(View view){
            super(view);
            name = (TextView) view.findViewById(R.id.name22);
            imageView= (ImageView) view.findViewById(R.id.image);

        }
    }


}
