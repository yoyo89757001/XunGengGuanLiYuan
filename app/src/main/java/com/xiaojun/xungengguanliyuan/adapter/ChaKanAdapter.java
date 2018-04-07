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

public class ChaKanAdapter extends RecyclerView.Adapter<ChaKanAdapter.ViewHolder> {
    private List<BaoBiao2.ObjectsBean> datas;
    private ClickIntface clickIntface;

    public void setClickIntface(ClickIntface clickIntface){
        this.clickIntface=clickIntface;
    }

    public ChaKanAdapter(List<BaoBiao2.ObjectsBean> datas) {
        this.datas = datas;
    }
    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.baogao4_item,viewGroup,false);
        return new ViewHolder(view);
    }
    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        viewHolder.bianhao.setText(datas.get(position).getSchedule_id()+"");
        viewHolder.xiangmuming.setText(datas.get(position).getItem_name());
        viewHolder.kaishi.setText(datas.get(position).getStr_btime().equals("")?"":datas.get(position).getStr_btime().split("--")[0]);
        viewHolder.jieshu.setText(datas.get(position).getStr_btime().equals("")?"":datas.get(position).getStr_btime().split("--")[1]);
        viewHolder.daka.setText(datas.get(position).getStatus_());
        viewHolder.name.setText(datas.get(position).getUser_names());
    }
    //获取数据的数量
    @Override
    public int getItemCount() {
        return datas.size();
    }
    //自定义的ViewHolder，持有每个Item的的所有界面元素
      class ViewHolder extends RecyclerView.ViewHolder {
        private TextView bianhao ,xiangmuming,name,kaishi,jieshu,daka;


        private ViewHolder(View view){
            super(view);
            bianhao = (TextView) view.findViewById(R.id.bianhao);
            xiangmuming = (TextView) view.findViewById(R.id.xiangmuming);
            name = (TextView) view.findViewById(R.id.name);
            kaishi = (TextView) view.findViewById(R.id.kaishishijian);
            jieshu = (TextView) view.findViewById(R.id.jieshushijian);
            daka = (TextView) view.findViewById(R.id.zhuangtai);

        }
    }


}
