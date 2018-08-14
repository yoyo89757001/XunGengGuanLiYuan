package com.xiaojun.xungengguanliyuan.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sdsmdg.tastytoast.TastyToast;
import com.xiaojun.xungengguanliyuan.MyAppLaction;
import com.xiaojun.xungengguanliyuan.R;
import com.xiaojun.xungengguanliyuan.adapter.PopupWindowAdapter;
import com.xiaojun.xungengguanliyuan.beans.ChuanBean;
import com.xiaojun.xungengguanliyuan.beans.DengLuBean;
import com.xiaojun.xungengguanliyuan.beans.DengLuBeanDao;
import com.xiaojun.xungengguanliyuan.utils.GsonUtil;
import com.xiaojun.xungengguanliyuan.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class XuanZeBaoBiaoActivity extends Activity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.xiangmuming)
    TextView xiangmuming;
    @BindView(R.id.bianhao)
    EditText bianhao;
    @BindView(R.id.shijian1)
    TextView shijian1;
    @BindView(R.id.shijian2)
    TextView shijian2;
    @BindView(R.id.xiayibu)
    Button xiayibu;
    @BindView(R.id.nameRL)
    RelativeLayout nameRL;
    @BindView(R.id.bianhaoRl)
    RelativeLayout bianhaoRl;
    @BindView(R.id.xiangmu_rl)
    RelativeLayout xiangmuRl;
    private int type;
    private PopupWindow popupWindow=null;
    private PopupWindowAdapter adapterss;
    private List<String> stringList=new ArrayList<>();
    private DengLuBean dengLuBean = null;
    private DengLuBeanDao dengLuBeanDao = null;

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
        type = getIntent().getIntExtra("type", 0);

        setContentView(R.layout.activity_xuan_ze_bao_biao);
        ButterKnife.bind(this);
        if (type == 0) {
            nameRL.setVisibility(View.GONE);
            bianhaoRl.setVisibility(View.GONE);

        }
        dengLuBeanDao = MyAppLaction.myAppLaction.getDaoSession().getDengLuBeanDao();
        dengLuBean = dengLuBeanDao.load(123456L);
        if (dengLuBean!=null){
            link_save();
        }

    }

    @OnClick({R.id.back, R.id.shijian1, R.id.shijian2, R.id.xiayibu,R.id.xiangmu_rl})
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
                if (type == 0) {
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

                    ChuanBean bean = new ChuanBean();
                    bean.setBianhao(bianhao.getText().toString().trim());
                    bean.setName(name.getText().toString().trim());
                    bean.setType(type);
                    bean.setXiangmuMing(xiangmuming.getText().toString().trim());
                    bean.setShijian1(shijian1.getText().toString().trim());
                    bean.setShijian2(shijian2.getText().toString().trim());
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("chuan", bean);
                    startActivity(new Intent(XuanZeBaoBiaoActivity.this, ChaKanBaoBiaoActivity0.class).putExtras(bundle));

                } else if (type == 1) {
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
                    ChuanBean bean = new ChuanBean();
                    bean.setBianhao(bianhao.getText().toString().trim());
                    bean.setName(name.getText().toString().trim());
                    bean.setType(type);
                    bean.setXiangmuMing(xiangmuming.getText().toString().trim());
                    bean.setShijian1(shijian1.getText().toString().trim());
                    bean.setShijian2(shijian2.getText().toString().trim());
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("chuan", bean);
                    startActivity(new Intent(XuanZeBaoBiaoActivity.this, ChaKanBaoBiaoActivity1.class).putExtras(bundle));
                } else {

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
                    ChuanBean bean = new ChuanBean();
                    bean.setBianhao(bianhao.getText().toString().trim());
                    bean.setName(name.getText().toString().trim());
                    bean.setType(type);
                    bean.setXiangmuMing(xiangmuming.getText().toString().trim());
                    bean.setShijian1(shijian1.getText().toString().trim());
                    bean.setShijian2(shijian2.getText().toString().trim());
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("chuan", bean);
                    startActivity(new Intent(XuanZeBaoBiaoActivity.this, ChaKanBaoBiaoActivity.class).putExtras(bundle));
                }
                break;
            case R.id.xiangmu_rl:
                if (stringList.size()>0){
                    View contentView = LayoutInflater.from(XuanZeBaoBiaoActivity.this).inflate(R.layout.xiangmu_po_item, null);

                    ListView listView = (ListView) contentView.findViewById(R.id.dddddd);
                    adapterss = new PopupWindowAdapter(XuanZeBaoBiaoActivity.this, stringList);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            xiangmuming.setText(stringList.get(position));
                            popupWindow.dismiss();
                        }
                    });
                    listView.setAdapter(adapterss);
                    popupWindow = new PopupWindow(contentView, 200, 400);
                    popupWindow.setFocusable(true);//获取焦点
                    popupWindow.setOutsideTouchable(true);//获取外部触摸事件
                    popupWindow.setTouchable(true);//能够响应触摸事件
                    popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));//设置背景
                    popupWindow.showAsDropDown(xiangmuming, 0, 1);

                }else {
                    TastyToast.makeText(XuanZeBaoBiaoActivity.this, "没有获取到数据", TastyToast.LENGTH_LONG, TastyToast.INFO).show();
                }

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

    private void link_save() {

        final MediaType JSON=MediaType.parse("application/json; charset=utf-8");
        OkHttpClient okHttpClient= MyAppLaction.getOkHttpClient();

        String nonce=Utils.getNonce();
        String timestamp=Utils.getTimestamp();

//    /* form的分割线,自己定义 */
//        String boundary = "xx--------------------------------------------------------------xx";
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("cmd","100");
         //   jsonObject.put("account",zhanghao);
         //   jsonObject.put("password",jiami);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(JSON, jsonObject.toString());

        Request.Builder requestBuilder = new Request.Builder()
                .header("nonce", nonce)
                .header("timestamp", timestamp)
                .header("userId", dengLuBean.getUserId()+"")
                .header("sign", Utils.encode("100"+nonce+timestamp
                        +dengLuBean.getUserId()+ Utils.signaturePassword))
                .post(body)
                .url(dengLuBean.getZhuji() + "items.app");
//        Log.d("LogingActivity", "100"+zhanghao+jiami+nonce+timestamp
//                +"0"+ Utils.signaturePassword);
        // step 3：创建 Call 对象
        Call call = okHttpClient.newCall(requestBuilder.build());

        //step 4: 开始异步请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("AllConnects", "请求识别失败"+e.getMessage());

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                Log.d("AllConnects", "请求识别成功"+call.request().toString());
                //获得返回体
                try {

                    ResponseBody body = response.body();
                    String ss=body.string().trim();
                    Log.d("InFoActivity", "项目名" + ss);
                    JsonObject jsonObject= GsonUtil.parse(ss).getAsJsonObject();
                    Gson gson=new Gson();
                    JsonObject jsonElement= jsonObject.get("account").getAsJsonObject();
                    DengLuBean zhaoPianBean=gson.fromJson(jsonElement,DengLuBean.class);
                    if (jsonObject.get("dtoResult").getAsString().equals("0")){}

                }catch (Exception e){

                    Log.d("WebsocketPushMsg", e.getMessage());
                }
            }
        });

    }

}
