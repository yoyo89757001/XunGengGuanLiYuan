package com.xiaojun.xungengguanliyuan.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiaojun.xungengguanliyuan.R;
import com.xiaojun.xungengguanliyuan.beans.ChuanBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class XuanZeBaoBiaoActivity extends Activity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.xiangmuming)
    EditText xiangmuming;
    @BindView(R.id.bianhao)
    EditText bianhao;
    @BindView(R.id.shijian1)
    TextView shijian1;
    @BindView(R.id.shijian2)
    TextView shijian2;
    @BindView(R.id.xiayibu)
    Button xiayibu;
    private int type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            // window.setNavigationBarColor(Color.TRANSPARENT);
        }
        type=getIntent().getIntExtra("type",0);

        setContentView(R.layout.activity_xuan_ze_bao_biao);
        ButterKnife.bind(this);


    }

    @OnClick({R.id.back, R.id.shijian1, R.id.shijian2, R.id.xiayibu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.shijian1:
                Intent intent = new Intent(XuanZeBaoBiaoActivity.this, DatePickActivity.class);
                startActivityForResult(intent, 2);
                break;
            case R.id.shijian2:
                Intent intent2 = new Intent(XuanZeBaoBiaoActivity.this, DatePickActivity.class);
                startActivityForResult(intent2, 3);
                break;
            case R.id.xiayibu:
            xiayibu.setEnabled(false);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        SystemClock.sleep(2000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                xiayibu.setEnabled(true);
                            }
                        });
                    }
                }).start();
                ChuanBean bean=new ChuanBean();
                bean.setBianhao(bianhao.getText().toString().trim());
                bean.setName(name.getText().toString().trim());
                bean.setType(type);
                bean.setXiangmuMing(xiangmuming.getText().toString().trim());
                bean.setShijian1(shijian1.getText().toString().trim());
                bean.setShijian2(shijian2.getText().toString().trim());
                Bundle bundle=new Bundle();
                bundle.putSerializable("chuan",bean);

                startActivity(new Intent(XuanZeBaoBiaoActivity.this,ChaKanBaoBiaoActivity.class).putExtras(bundle));

                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK && requestCode == 2) {
            // 选择预约时间的页面被关闭
            String date = data.getStringExtra("date");
            shijian1.setText(date);

        }
        if (resultCode == Activity.RESULT_OK && requestCode == 3) {
            // 选择预约时间的页面被关闭
            String date = data.getStringExtra("date");
            shijian2.setText(date);
        }
    }
}
