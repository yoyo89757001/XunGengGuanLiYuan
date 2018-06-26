package com.xiaojun.xungengguanliyuan.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xiaojun.xungengguanliyuan.R;
import com.xiaojun.xungengguanliyuan.beans.BaoBiao2;
import com.xiaojun.xungengguanliyuan.intface.ClickIntface;

import java.util.List;

/**
 * Created by Administrator on 2017/10/3.
 */

public class ChaKanAdapter0 extends RecyclerView.Adapter<ChaKanAdapter0.ViewHolder> {
    private List<BaoBiao2.ObjectsBean> datas;
    private ClickIntface clickIntface;

    public void setClickIntface(ClickIntface clickIntface){
        this.clickIntface=clickIntface;
    }

    public ChaKanAdapter0(List<BaoBiao2.ObjectsBean> datas) {
        this.datas = datas;
    }
    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.baogao6_item,viewGroup,false);
        return new ViewHolder(view);
    }
    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        viewHolder.xiangmuming.setText(datas.get(position).getItem_name());
        viewHolder.shidjianduan.setText(datas.get(position).getStart_time());
        viewHolder.yinggaiwancheng.setText(datas.get(position).getTotal_mission()+"");
        viewHolder.yiwancheng.setText(datas.get(position).getFinish_mission()+"");
        viewHolder.weiwancheng.setText(datas.get(position).getUnfinished_mission()+"");

    }
    //获取数据的数量
    @Override
    public int getItemCount() {
        return datas.size();
    }
    //自定义的ViewHolder，持有每个Item的的所有界面元素
      class ViewHolder extends RecyclerView.ViewHolder {
        private TextView xiangmuming,shidjianduan,yinggaiwancheng,weiwancheng,yiwancheng;


        private ViewHolder(View view){
            super(view);
            xiangmuming = (TextView) view.findViewById(R.id.xiangmuming);
            shidjianduan = (TextView) view.findViewById(R.id.riqi);
            yinggaiwancheng = (TextView) view.findViewById(R.id.yinwancheng);
            weiwancheng = (TextView) view.findViewById(R.id.weiwancheng);
            yiwancheng = (TextView) view.findViewById(R.id.zhunshiwancheng);

        }
    }


}
