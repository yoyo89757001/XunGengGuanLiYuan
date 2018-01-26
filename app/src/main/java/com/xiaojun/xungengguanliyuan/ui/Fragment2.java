package com.xiaojun.xungengguanliyuan.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xiaojun.xungengguanliyuan.MyAppLaction;
import com.xiaojun.xungengguanliyuan.R;
import com.xiaojun.xungengguanliyuan.beans.DengLuBean;
import com.xiaojun.xungengguanliyuan.beans.DengLuBeanDao;
import com.xiaojun.xungengguanliyuan.dialog.QueRenDialog;
import com.xiaojun.xungengguanliyuan.utils.SpringEffect;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment2 extends Fragment {

    private DengLuBeanDao baoCunBeanDao=null;
    private DengLuBean baoCunBean=null;
    @BindView(R.id.zhanghao)
    TextView zhanghao;
    @BindView(R.id.xingming)
    TextView xingming;
    @BindView(R.id.juese)
    TextView juese;
    @BindView(R.id.shouji)
    TextView shouji;
    @BindView(R.id.youxiang)
    TextView youxiang;
    Unbinder unbinder;

    public Fragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        baoCunBeanDao= MyAppLaction.myAppLaction.getDaoSession().getDengLuBeanDao();
        if (baoCunBeanDao!=null){
            baoCunBean=baoCunBeanDao.load(123456L);
        }
        View view = inflater.inflate(R.layout.fragment_fragment2, container, false);

        SpringEffect.doEffectSticky(view.findViewById(R.id.tuichu), new Runnable() {
            @Override
            public void run() {

                final QueRenDialog dialog2=new QueRenDialog(getActivity());
                dialog2.setVisibility_BT();
                dialog2.setCountText("你确定要退出?");
                dialog2.setOnPositiveListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        baoCunBeanDao.deleteByKey(123456L);
                        getActivity().sendBroadcast(new Intent("guanbiyemian"));
                        dialog2.dismiss();
                        baoCunBean.setQqTime(null);
                        baoCunBean.setAccount("");
                        baoCunBeanDao.deleteAll();

                      //  link();
                        startActivity(new Intent(getActivity(),LogingActivity.class));
                    }
                });
                dialog2.setOnQuXiaoListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog2.dismiss();
                    }
                });
                dialog2.show();

            }
        });


        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
