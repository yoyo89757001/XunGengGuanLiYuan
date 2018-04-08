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

public class ChaKanAdapter2 extends RecyclerView.Adapter<ChaKanAdapter2.ViewHolder> {
    private List<BaoBiao2.ObjectsBean> datas;
    private ClickIntface clickIntface;

    public void setClickIntface(ClickIntface clickIntface){
        this.clickIntface=clickIntface;
    }

    public ChaKanAdapter2(List<BaoBiao2.ObjectsBean> datas) {
        this.datas = datas;
    }
    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.baogao5_item,viewGroup,false);
        return new ViewHolder(view);
    }
    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        viewHolder.bianhao.setText(datas.get(position).getSchedule_id()+"");
        viewHolder.xiangmuming.setText(datas.get(position).getItem_name());
        viewHolder.riqi.setText(datas.get(position).getStart_time());
        viewHolder.shidjianduan.setText(datas.get(position).getStr_btime());
        viewHolder.yinwancheng.setText(datas.get(position).getTotal_mission()+"");
        viewHolder.yiwancheng.setText(datas.get(position).getFinish_mission()+"");
        viewHolder.weiwancheng.setText(datas.get(position).getUnfinished_mission()+"");
        viewHolder.name.setText(datas.get(position).getUser_names());
    }
    //获取数据的数量
    @Override
    public int getItemCount() {
        return datas.size();
    }
    //自定义的ViewHolder，持有每个Item的的所有界面元素
      class ViewHolder extends RecyclerView.ViewHolder {
        private TextView bianhao ,xiangmuming,name,riqi,shidjianduan,yinwancheng,weiwancheng,yiwancheng;


        private ViewHolder(View view){
            super(view);
            bianhao = (TextView) view.findViewById(R.id.bianhao);
            xiangmuming = (TextView) view.findViewById(R.id.xiangmuming);
            name = (TextView) view.findViewById(R.id.name);
            riqi = (TextView) view.findViewById(R.id.riqi);
            shidjianduan = (TextView) view.findViewById(R.id.shijianduan);
            yinwancheng = (TextView) view.findViewById(R.id.yinwancheng);
            weiwancheng = (TextView) view.findViewById(R.id.weiwancheng);
            yiwancheng = (TextView) view.findViewById(R.id.yiwancheng);

        }
    }


}
