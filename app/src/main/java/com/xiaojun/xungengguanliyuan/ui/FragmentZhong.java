package com.xiaojun.xungengguanliyuan.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xiaojun.xungengguanliyuan.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentZhong extends Fragment {

    @BindView(R.id.b0)
    TextView b0;
    @BindView(R.id.b1)
    TextView b1;
    @BindView(R.id.b2)
    TextView b2;
    @BindView(R.id.b3)
    TextView b3;
    Unbinder unbinder;

    public FragmentZhong() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_zhong, container, false);

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.b0,R.id.b1, R.id.b2, R.id.b3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.b0:
                startActivity(new Intent(getActivity(),XuanZeBaoBiaoActivity.class).putExtra("type",0));
                break;
            case R.id.b1:
                startActivity(new Intent(getActivity(),XuanZeBaoBiaoActivity.class).putExtra("type",1));
                break;
            case R.id.b2:
                startActivity(new Intent(getActivity(),XuanZeBaoBiaoActivity.class).putExtra("type",2));
                break;
            case R.id.b3:
                startActivity(new Intent(getActivity(),XuanZeBaoBiaoActivity.class).putExtra("type",3));
                break;
        }
    }
}
