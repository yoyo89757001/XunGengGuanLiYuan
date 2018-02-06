package com.xiaojun.xungengguanliyuan.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mabeijianxi.smallvideorecord2.MediaRecorderActivity;
import com.sdsmdg.tastytoast.TastyToast;
import com.xiaojun.xungengguanliyuan.MyAppLaction;
import com.xiaojun.xungengguanliyuan.R;
import com.xiaojun.xungengguanliyuan.beans.DataSynEvent;
import com.xiaojun.xungengguanliyuan.beans.DengLuBean;
import com.xiaojun.xungengguanliyuan.beans.DengLuBeanDao;
import com.xiaojun.xungengguanliyuan.beans.FanHuiBean;
import com.xiaojun.xungengguanliyuan.beans.MainBean;
import com.xiaojun.xungengguanliyuan.cookies.CookiesManager;
import com.xiaojun.xungengguanliyuan.dialog.TiJIaoDialog;
import com.xiaojun.xungengguanliyuan.intface.ClickIntface2;
import com.xiaojun.xungengguanliyuan.utils.GsonUtil;
import com.xiaojun.xungengguanliyuan.utils.SpringEffect;
import com.xiaojun.xungengguanliyuan.utils.Utils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

import static org.greenrobot.eventbus.EventBus.TAG;

public class DaKaActivity_ChaKan extends Activity {
    @BindView(R.id.yulan)
    Button yulan;
    @BindView(R.id.renwu)
    TextView renwu;
    @BindView(R.id.tijiao)
    Button tijiao;
    @BindView(R.id.qita)
    TextView qita;
    private ZhaoPianAdapter zhaoPianAdapter = null;
    private List<String> stringList;
    private RecyclerView recyclerView;
    private ImageView shiping_im;
    private String video_uri = null;
    private String output_directory = null;
    private String video_screenshot = null;
    private DataSynEvent dataSynEvent = null;
    private TiJIaoDialog tiJIaoDialog = null;
    private DengLuBean dengLuBean = null;
    private DengLuBeanDao dengLuBeanDao = null;
    private int recordId, itemId, lineId, patrolId;
    private String luxian = null, vedios = null, imgs = null;
    private boolean biaozhi = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        stringList = new ArrayList<>();
        dengLuBeanDao = MyAppLaction.myAppLaction.getDaoSession().getDengLuBeanDao();
        dengLuBean = dengLuBeanDao.load(123456L);
        video_uri = getIntent().getStringExtra(MediaRecorderActivity.VIDEO_URI);
        output_directory = getIntent().getStringExtra(MediaRecorderActivity.OUTPUT_DIRECTORY);
        video_screenshot = getIntent().getStringExtra(MediaRecorderActivity.VIDEO_SCREENSHOT);
        recordId = getIntent().getIntExtra("recordId", -1);
        itemId = getIntent().getIntExtra("itemId", -1);
        lineId = getIntent().getIntExtra("lineId", -1);
        patrolId = getIntent().getIntExtra("patrolId", -1);
        luxian = getIntent().getStringExtra("luxian");
        imgs = getIntent().getStringExtra("imgs");
        vedios = getIntent().getStringExtra("vedios");
        if (imgs != null && !imgs.equals("")) {
            String img[] = imgs.split(";");
            stringList.addAll(Arrays.asList(img));
        }


        if (video_uri != null || output_directory != null && video_screenshot != null) {

            EventBus.getDefault().post(new DataSynEvent(video_uri, output_directory, video_screenshot));
            finish();
        } else {
            EventBus.getDefault().register(DaKaActivity_ChaKan.this);//订阅
        }

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
        setContentView(R.layout.activity_da_ka);
        ButterKnife.bind(this);
        shiping_im = (ImageView) findViewById(R.id.shiping_im);


        recyclerView = (RecyclerView) findViewById(R.id.recy);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(manager);
        // recyclerView.addItemDecoration(new MyDecoration(DaKaActivity.this, LinearLayoutManager.VERTICAL,10,R.color.transparent));

        zhaoPianAdapter = new ZhaoPianAdapter(stringList);
        recyclerView.setAdapter(zhaoPianAdapter);
        renwu.setText(luxian + "");


        SpringEffect.doEffectSticky(findViewById(R.id.yulan), new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(DaKaActivity_ChaKan.this, VideoActivity.class).putExtra("url", vedios).putExtra("sss", 1));

            }
        });

        SpringEffect.doEffectSticky(findViewById(R.id.tijiao), new Runnable() {
            @Override
            public void run() {

                link_P1(stringList);
            }
        });

        tijiao.setVisibility(View.GONE);
        if (vedios != null && !vedios.equals("")) {
            yulan.setVisibility(View.VISIBLE);
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onDataSynEvent(DataSynEvent event) {
        Log.d(TAG, event.toString());
        dataSynEvent = event;
        Glide.with(DaKaActivity_ChaKan.this)
                .load(event.getVideo_screenshot())
                //  .skipMemoryCache(true)
                //  .diskCacheStrategy(DiskCacheStrategy.NONE)
                //  .transform(new GlideCircleTransform(DengJiActivity.this,2, Color.parseColor("#ffffffff")))
                .into(shiping_im);
        shiping_im.setPadding(20, 20, 20, 20);
        yulan.setVisibility(View.VISIBLE);
    }


    @Override
    protected void onDestroy() {
        if (EventBus.getDefault().isRegistered(DaKaActivity_ChaKan.this)) {
            EventBus.getDefault().unregister(this);//解除订阅
            if (biaozhi)
                EventBus.getDefault().post(new MainBean(true, true));
            else
                EventBus.getDefault().post(new MainBean(true, false));
        }
        super.onDestroy();
    }


    private class ZhaoPianAdapter extends RecyclerView.Adapter<ZhaoPianAdapter.ViewHolder> {
        private List<String> datas;
        private ClickIntface2 clickIntface;

        public void setClickIntface(ClickIntface2 clickIntface) {
            this.clickIntface = clickIntface;
        }

        private ZhaoPianAdapter(List<String> datas) {
            this.datas = datas;
        }

        //创建新View，被LayoutManager所调用
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tupian_item2, viewGroup, false);
            return new ViewHolder(view);
        }


        //将数据与界面进行绑定的操作
        @Override
        public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
//            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    clickIntface.BackId(viewHolder.itemView);
//                }
//            });
            Log.d("VideoActivity", dengLuBean.getZhuji() + "patrolImage" + "/" + datas.get(position));
            Glide.with(DaKaActivity_ChaKan.this)
                    .load(dengLuBean.getZhuji().replaceAll("api/", "") + "patrolImages" + "/" + datas.get(position))
                    //  .skipMemoryCache(true)
                    //  .diskCacheStrategy(DiskCacheStrategy.NONE)
                    //  .transform(new GlideCircleTransform(DengJiActivity.this,2, Color.parseColor("#ffffffff")))
                    .into(viewHolder.tupian);
            viewHolder.tupian.setPadding(20, 20, 20, 20);


            // Log.d("ZhaoPianAdapter", "stringList.size():" + stringList.size());
        }

        //获取数据的数量
        @Override
        public int getItemCount() {
            return datas.size();
        }

        //自定义的ViewHolder，持有每个Item的的所有界面元素
        class ViewHolder extends RecyclerView.ViewHolder {
            private ImageView tupian;


            private ViewHolder(View view) {
                super(view);
                tupian = (ImageView) view.findViewById(R.id.tupian);


            }
        }
    }


    private void showMSG(final String s, final int i) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Toast tastyToast = TastyToast.makeText(DaKaActivity_ChaKan.this, s, TastyToast.LENGTH_LONG, i);
                tastyToast.setGravity(Gravity.CENTER, 0, 0);
                tastyToast.show();

            }
        });
    }


    private void showDialog() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (tiJIaoDialog == null) {
                    tiJIaoDialog = new TiJIaoDialog(DaKaActivity_ChaKan.this);
                    if (!DaKaActivity_ChaKan.this.isFinishing())
                        tiJIaoDialog.show();
                }
            }
        });
    }


    private void dismissDialog() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (tiJIaoDialog != null && tiJIaoDialog.isShowing()) {
                    tiJIaoDialog.dismiss();
                    tiJIaoDialog = null;
                }
            }
        });
    }

    public static final int TIMEOUT = 1000 * 150;

    private void link_P1(List<String> stringList) {
        showDialog();
        String nonce = Utils.getNonce();
        String timestamp = Utils.getTimestamp();
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .writeTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .connectTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .cookieJar(new CookiesManager())
                .retryOnConnectionFailure(true)
                .build();
        ;
        MultipartBody mBody;
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);

        if (stringList.size() > 1) {
            StringBuilder buffer = new StringBuilder();
            int ss2 = stringList.size() - 1;
            for (int i = 0; i < ss2; i++) {
                File file1 = new File(stringList.get(i));
                RequestBody fileBody1 = RequestBody.create(MediaType.parse("application/octet-stream"), file1);
                //去掉前面的路径
                builder.addFormDataPart("imageFile", stringList.get(i).substring(stringList.get(i).lastIndexOf("/") + 1, stringList.get(i).length()), fileBody1);

                if (i < ss2 - 1) {
                    buffer.append(stringList.get(i).substring(stringList.get(i).lastIndexOf("/") + 1, stringList.get(i).length()));
                    buffer.append(";");
                } else {
                    buffer.append(stringList.get(i).substring(stringList.get(i).lastIndexOf("/") + 1, stringList.get(i).length()));
                }
            }
            builder.addFormDataPart("imgs", buffer.toString());
            // Log.d("DaKaActivity", buffer.toString());
        } else {
            builder.addFormDataPart("imgs", "");
        }

        if (dataSynEvent != null && dataSynEvent.getVideo_uri() != null) {

            File file1 = new File(dataSynEvent.getVideo_uri());
            RequestBody fileBody1 = RequestBody.create(MediaType.parse("application/octet-stream"), file1);
            //去掉前面的路径
            builder.addFormDataPart("vedioFile", dataSynEvent.getVideo_uri().substring(dataSynEvent.getVideo_uri()
                    .lastIndexOf("/") + 1, dataSynEvent.getVideo_uri().length()), fileBody1);
            builder.addFormDataPart("vedios", dataSynEvent.getVideo_uri().substring(dataSynEvent.getVideo_uri()
                    .lastIndexOf("/") + 1, dataSynEvent.getVideo_uri().length()));

        } else {
            builder.addFormDataPart("vedios", "");
        }
        builder.addFormDataPart("cmd", "100");
        builder.addFormDataPart("recordId", recordId + "");
        builder.addFormDataPart("itemId", itemId + "");
        builder.addFormDataPart("lineId", lineId + "");
        builder.addFormDataPart("patrolId", patrolId + "");
        builder.addFormDataPart("other", "");

//        JSONObject tijiao = null;
//        // JSONArray jsonArray=null;
//        try {
//            tijiao = new JSONObject();
//            tijiao.put("recordId",0);
//            tijiao.put("id",0);
//            tijiao.put("accountId",dengLuBean.getUserId());
//            tijiao.put("address",dizhi.getText().toString().trim());
//            tijiao.put("companyId",dengLuBean.getCompanyId());
//            tijiao.put("deviceId",shebeiID);
//            tijiao.put("planId",itemsBeanList.get(p1).getId());
//            tijiao.put("deviceNumber",bianhao);
//            tijiao.put("faultTime",System.currentTimeMillis());
//            tijiao.put("remark",guzhangEt.getText().toString().trim());
//            tijiao.put("contactTel",dianhua.getText().toString());
//            tijiao.put("faultImage",dengJiBean.getFaultImage());
//
//            // jsonArray=new JSONArray();
//            // jsonArray.put(tijiao);
//
//            // jsonObject.put("cmd","100");
//            // jsonObject.put("faults",jsonArray);
//            //  Log.d("BaoZhangDengJiActivity", jsonObject.toString());
//            //   jsonObject.put("password",jiami);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//

        mBody = builder.build();
        //   Log.d("BaoZhangDengJiActivity", tijiao.toString());

//         /* 第一个要上传的file */
//       File file1 = new File(filename1);
//        RequestBody fileBody1 = RequestBody.create(MediaType.parse("application/octet-stream") , file1);
//        final String file1Name = System.currentTimeMillis()+"testFile1.jpg";
//
//
//        MultipartBody mBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
//            /* 底下是上传了两个文件 */
//                .addFormDataPart("file" , file1Name , fileBody1)
//                  /* 上传一个普通的String参数 */
//                //  .addFormDataPart("subject_id" , subject_id+"")
//                //  .addFormDataPart("image_2" , file2Name , fileBody2)
//                .build();
        Request.Builder requestBuilder = new Request.Builder()
                // .header("Content-Type", "application/json")
                .header("nonce", nonce)
                .header("timestamp", timestamp)
                .header("userId", dengLuBean.getUserId() + "")
                .header("sign", Utils.encode("100" + nonce + timestamp
                        + dengLuBean.getUserId() + Utils.signaturePassword))
                .post(mBody)
                .url(dengLuBean.getZhuji() + "addPatrol.app");

        // step 3：创建 Call 对象
        Call call = okHttpClient.newCall(requestBuilder.build());

        //step 4: 开始异步请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                dismissDialog();
                showMSG("上传图片出错", 4);
                Log.d("AllConnects", "请求识别失败" + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                dismissDialog();
                Log.d("AllConnects", "请求识别成功" + call.request().toString());
                //获得返回体
                try {

                    ResponseBody body = response.body();
                    String ss = body.string();

                    //  link_save(dengJiBean);
                    Log.d("AllConnects", "aa   " + ss);

                    JsonObject jsonObject = GsonUtil.parse(ss).getAsJsonObject();
                    Gson gson = new Gson();
                    FanHuiBean zhaoPianBean = gson.fromJson(jsonObject, FanHuiBean.class);
                    if (zhaoPianBean.getDtoResult() == 0) {

                        biaozhi = true;
                        showMSG("打卡成功", 4);
                        finish();
                    } else if (zhaoPianBean.getDtoResult() == -33) {
                        showMSG("登录过期,请重新登录", 4);
                    }

                } catch (Exception e) {
                    dismissDialog();
                    showMSG("上传图片出错", 4);
                    Log.d("WebsocketPushMsg", e.getMessage());
                }
            }
        });

    }
}
