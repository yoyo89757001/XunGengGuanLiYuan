package com.xiaojun.xungengguanliyuan.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.xiaojun.xungengguanliyuan.R;
import com.xiaojun.xungengguanliyuan.beans.XianLuBean;
import com.xiaojun.xungengguanliyuan.intface.ClickIntface;

import java.util.List;

/**
 * Created by Administrator on 2017/10/3.
 */

public class F1Adapter extends RecyclerView.Adapter<F1Adapter.ViewHolder> {
    private List<XianLuBean.ObjectsBean> datas;
    private ClickIntface clickIntface;

    public void setClickIntface(ClickIntface clickIntface){
        this.clickIntface=clickIntface;
    }

    public F1Adapter(List<XianLuBean.ObjectsBean> datas) {
        this.datas = datas;
    }
    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.baogao2_item,viewGroup,false);
        return new ViewHolder(view);
    }
    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        viewHolder.luxian.setText(datas.get(position).getLine_name());
        viewHolder.luxian2.setText(datas.get(position).getSchedule_tag());
        viewHolder.heji.setText(datas.get(position).getTotal()+"");
        viewHolder.daiban.setText((datas.get(position).getTotal()-datas.get(position).getRecords())+"");
    }
    //获取数据的数量
    @Override
    public int getItemCount() {
        return datas.size();
    }
    //自定义的ViewHolder，持有每个Item的的所有界面元素
      class ViewHolder extends RecyclerView.ViewHolder {
        private TextView luxian ,heji,daiban,luxian2;


        private ViewHolder(View view){
            super(view);
            luxian = (TextView) view.findViewById(R.id.renwu);
            luxian2 = (TextView) view.findViewById(R.id.luxian);
            heji = (TextView) view.findViewById(R.id.heji);
            daiban = (TextView) view.findViewById(R.id.daiban);

        }
    }


}
