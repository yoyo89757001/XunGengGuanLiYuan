package com.xiaojun.xungengguanliyuan.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sdsmdg.tastytoast.TastyToast;
import com.xiaojun.xungengguanliyuan.MyAppLaction;
import com.xiaojun.xungengguanliyuan.R;
import com.xiaojun.xungengguanliyuan.beans.DengLuBean;
import com.xiaojun.xungengguanliyuan.beans.DengLuBeanDao;
import com.xiaojun.xungengguanliyuan.dialog.TiJIaoDialog;
import com.xiaojun.xungengguanliyuan.utils.GsonUtil;
import com.xiaojun.xungengguanliyuan.utils.SpringEffect;
import com.xiaojun.xungengguanliyuan.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;


public class LogingActivity extends Activity {
    @BindView(R.id.zhanghao)
    EditText zhanghao;
    @BindView(R.id.mima)
    EditText mima;
    @BindView(R.id.wangjimima)
    TextView wangjimima;
    @BindView(R.id.denglu)
    Button denglu;
    private TiJIaoDialog tiJIaoDialog=null;
    private DengLuBean dengLuBean=null;
    private DengLuBeanDao dengLuBeanDao=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dengLuBeanDao= MyAppLaction.myAppLaction.getDaoSession().getDengLuBeanDao();
        dengLuBean=dengLuBeanDao.load(123456L);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            //  window.setNavigationBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_loging);
        ButterKnife.bind(this);





        SpringEffect.doEffectSticky(findViewById(R.id.denglu), new Runnable() {
            @Override
            public void run() {
                if(!zhanghao.getText().toString().trim().equals("") && !mima.getText().toString().trim().equals("") ){
                    link_save(zhanghao.getText().toString().trim(),mima.getText().toString().trim(),0);
                }else {
                    showMSG("请填写账号密码",4);
                }


            }
        });
        SpringEffect.doEffectSticky(findViewById(R.id.wangjimima), new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(LogingActivity.this,WangJiMiMaActivity.class));

            }
        });


    }


    @Override
    protected void onResume() {
        super.onResume();
        if (dengLuBean!=null && dengLuBean.getAccount()!=null && dengLuBean.getZhuji()!=null ){
            link_save(dengLuBean.getAccount(),dengLuBean.getMima(),1);
        }
    }


    private void link_save(final String zhanghao, final String mima, final int i) {
        showDialog();

        final MediaType JSON=MediaType.parse("application/json; charset=utf-8");
        OkHttpClient okHttpClient= MyAppLaction.getOkHttpClient();

        String jiami= Utils.jiami(mima).toUpperCase();
        String nonce=Utils.getNonce();
        String timestamp=Utils.getTimestamp();

//    /* form的分割线,自己定义 */
//        String boundary = "xx--------------------------------------------------------------xx";
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("cmd","100");
            jsonObject.put("account",zhanghao);
            jsonObject.put("password",jiami);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(JSON, jsonObject.toString());

        Request.Builder requestBuilder = new Request.Builder()
                .header("nonce", nonce)
                .header("timestamp", timestamp)
                .header("userId", "0")
                .header("sign", Utils.encode("100"+zhanghao+jiami+nonce+timestamp
                        +"0"+ Utils.signaturePassword))
                .post(body)
                .url(dengLuBean.getZhuji() + "login.app");
//        Log.d("LogingActivity", "100"+zhanghao+jiami+nonce+timestamp
//                +"0"+ Utils.signaturePassword);
        // step 3：创建 Call 对象
        Call call = okHttpClient.newCall(requestBuilder.build());

        //step 4: 开始异步请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("AllConnects", "请求识别失败"+e.getMessage());
                dismissDialog();
                finish();
                startActivity(new Intent(LogingActivity.this,MainActivity.class));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (i==1){
                    SystemClock.sleep(1200);
                }
                dismissDialog();

                Log.d("AllConnects", "请求识别成功"+call.request().toString());
                //获得返回体
                try {

                    ResponseBody body = response.body();
                    String ss=body.string().trim();
                    Log.d("InFoActivity", "ss" + ss);
                    JsonObject jsonObject= GsonUtil.parse(ss).getAsJsonObject();
                    Gson gson=new Gson();
                    JsonObject jsonElement= jsonObject.get("account").getAsJsonObject();
                    DengLuBean zhaoPianBean=gson.fromJson(jsonElement,DengLuBean.class);
                    if (jsonObject.get("dtoResult").getAsString().equals("0")){
                        showMSG(jsonObject.get("dtoDesc").getAsString(),4);
                        int  type=-1;
                        if (zhaoPianBean.getRole_id()==10000005||zhaoPianBean.getRole_id()==10000003){
                            //管理员
                            type=0;

                        }else //if (zhaoPianBean.getRole_id()==200000)
                        {
                            //巡更员
                            type=1;
                        }
                        zhaoPianBean.setUserId(zhaoPianBean.getId());
                        zhaoPianBean.setId(123456L);
                         zhaoPianBean.setStatus(type);
                        zhaoPianBean.setAccount(zhanghao);
                        zhaoPianBean.setMima(mima);
//                        zhaoPianBean.setQqTime(dengLuBean.getQqTime()==null?"2017-01-01 11:11:11":dengLuBean.getQqTime());
                        zhaoPianBean.setZhuji("http://14.23.169.42:8899/api/");
                        dengLuBeanDao.update(zhaoPianBean);
                        finish();
                        startActivity(new Intent(LogingActivity.this,MainActivity.class));

                    }else {
                        if (i==1){
                            finish();
                            startActivity(new Intent(LogingActivity.this,MainActivity.class));
                        }
                        showMSG(jsonObject.get("dtoDesc").getAsString(),4);
                    }

                }catch (Exception e){
                    if (i==1){
                        finish();
                        startActivity(new Intent(LogingActivity.this,MainActivity.class));
                    }
                    dismissDialog();
                    showMSG("获取数据失败",3);
                    Log.d("WebsocketPushMsg", e.getMessage());
                }
            }
        });

    }

    private void showDialog(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (tiJIaoDialog==null){
                    tiJIaoDialog=new TiJIaoDialog(LogingActivity.this);
                    if (!LogingActivity.this.isFinishing())
                        tiJIaoDialog.show();
                }
            }
        });
    }


    private void dismissDialog(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (tiJIaoDialog!=null && tiJIaoDialog.isShowing()){
                    tiJIaoDialog.dismiss();
                    tiJIaoDialog=null;
                }
            }
        });
    }

    private void showMSG(final String s,final int i){

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Toast tastyToast= TastyToast.makeText(LogingActivity.this,s,TastyToast.LENGTH_LONG,i);
                tastyToast.setGravity(Gravity.CENTER,0,0);
                tastyToast.show();

            }
        });
    }
}
