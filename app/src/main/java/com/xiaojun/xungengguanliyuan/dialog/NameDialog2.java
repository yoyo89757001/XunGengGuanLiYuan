package com.xiaojun.xungengguanliyuan.dialog;
import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

import com.xiaojun.xungengguanliyuan.R;
import com.xiaojun.xungengguanliyuan.adapter.NameAdapter2;
import com.xiaojun.xungengguanliyuan.beans.NameBeans;
import com.xiaojun.xungengguanliyuan.views.WrapContentLinearLayoutManager;

import java.util.List;


/**
 * @Function: 自定义对话框
 * @Date: 2013-10-28
 * @Time: 下午12:37:43
 * @author Tom.Cai
 */
public class NameDialog2 extends Dialog {
private TextView cancel,affirm;
private RecyclerView recyclerView;
private NameAdapter2 adapter;
private List<NameBeans> stringList;
private Context context;

    public NameDialog2(Context context, List<NameBeans> strings) {
        super(context, R.style.dialog_style2);
        stringList=strings;
        this.context=context;
        setCustomDialog();
    }

    public void gengxin(){
        adapter.notifyDataSetChanged();
    }

    private void setCustomDialog() {
        View mView = LayoutInflater.from(getContext()).inflate(R.layout.queren_ll5, null);
        cancel= (TextView) mView.findViewById(R.id.cancel);
        affirm= (TextView) mView.findViewById(R.id.affirm);
        recyclerView= (RecyclerView) mView.findViewById(R.id.recyclerView);
        adapter=new NameAdapter2(stringList);
        WrapContentLinearLayoutManager linearLayoutManager = new WrapContentLinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);


        super.setContentView(mView);

    }

    public NameBeans getData(){
        for (NameBeans ss: stringList){
            if (ss.isTrue()){
                return ss;
            }
        }
        return null;

    }


    @Override
    public void setContentView(int layoutResID) {
    }

    @Override
    public void setContentView(View view, LayoutParams params) {
    }

    @Override
    public void setContentView(View view) {
    }

    /**
     * 确定键监听器
     * @param listener
     */
    public void setOnPositiveListener(View.OnClickListener listener){
        affirm.setOnClickListener(listener);
    }
    /**
     * 取消键监听器
     * @param listener
     */
    public void setCancelListener(View.OnClickListener listener){
        cancel.setOnClickListener(listener);
    }
}
