package com.xiaojun.xungengguanliyuan.ui;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aprilbrother.aprilbrothersdk.Beacon;
import com.aprilbrother.aprilbrothersdk.BeaconManager;
import com.aprilbrother.aprilbrothersdk.Region;
import com.aprilbrother.aprilbrothersdk.utils.AprilL;
import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sdsmdg.tastytoast.TastyToast;
import com.xiaojun.xungengguanliyuan.MyAppLaction;
import com.xiaojun.xungengguanliyuan.R;
import com.xiaojun.xungengguanliyuan.adapter.RenWuLiuChengAdapter;
import com.xiaojun.xungengguanliyuan.beans.DengLuBean;
import com.xiaojun.xungengguanliyuan.beans.DengLuBeanDao;
import com.xiaojun.xungengguanliyuan.beans.MainBean;
import com.xiaojun.xungengguanliyuan.beans.XuanGengDian;
import com.xiaojun.xungengguanliyuan.dialog.TiJIaoDialog;
import com.xiaojun.xungengguanliyuan.utils.ComparatorBeaconByRssi;
import com.xiaojun.xungengguanliyuan.utils.GsonUtil;
import com.xiaojun.xungengguanliyuan.utils.Utils;
import com.xiaojun.xungengguanliyuan.views.WrapContentLinearLayoutManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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

public class RenWuLiuChengActivity extends Activity {

    @BindView(R.id.renwu)
    TextView renwu;
    private LRecyclerView lRecyclerView;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private List<XuanGengDian.ObjectsBean> stringList = new ArrayList<>();
    private RenWuLiuChengAdapter adapter;
    private DengLuBean dengLuBean = null;
    private DengLuBeanDao dengLuBeanDao = null;
    private TiJIaoDialog tiJIaoDialog = null;
    private String lineId = null;
    private final int REQUEST_ENABLE_BT = 5678;
    private static final Region ALL_BEACONS_REGION = new Region(
            "customRegionName", null, null, null);
    private BeaconManager beaconManager;
    private ArrayList<Beacon> myBeacons = new ArrayList<Beacon>();
    private String luxian=null;
    private static boolean isShow = true;
    private String schedule_id=null;

    @BindView(R.id.back)
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dengLuBeanDao = MyAppLaction.myAppLaction.getDaoSession().getDengLuBeanDao();
        dengLuBean = dengLuBeanDao.load(123456L);
        AprilL.enableDebugLogging(false);
        beaconManager = new BeaconManager(RenWuLiuChengActivity.this);
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
        EventBus.getDefault().register(RenWuLiuChengActivity.this);//订阅
        setContentView(R.layout.activity_ren_wu_liu_cheng);
        ButterKnife.bind(this);
        lineId = getIntent().getStringExtra("lineId");
        luxian=getIntent().getStringExtra("luxian");
        schedule_id=getIntent().getStringExtra("schedule_id");
        lRecyclerView = (LRecyclerView) findViewById(R.id.recyclerView);
        adapter = new RenWuLiuChengAdapter(stringList);
        lRecyclerViewAdapter = new LRecyclerViewAdapter(adapter);
        WrapContentLinearLayoutManager linearLayoutManager = new WrapContentLinearLayoutManager(RenWuLiuChengActivity.this, LinearLayoutManager.VERTICAL, false);
        lRecyclerView.setLayoutManager(linearLayoutManager);
        lRecyclerView.setAdapter(lRecyclerViewAdapter);

        final DividerDecoration divider = new DividerDecoration.Builder(RenWuLiuChengActivity.this)
                .setHeight(2.0f)
                .setPadding(2.0f)
                .setColorResource(R.color.transparent)
                .build();

        lRecyclerView.addItemDecoration(divider);
        //设置头部加载颜色
        lRecyclerView.setHeaderViewColor(R.color.colorAccent, R.color.blake, R.color.write);
        lRecyclerView.setRefreshProgressStyle(ProgressStyle.LineSpinFadeLoader);
        lRecyclerView.setFooterViewColor(R.color.huise, R.color.blake, R.color.write);
        //设置底部加载文字提示
        lRecyclerView.setFooterViewHint("拼命加载中", "--------我是有底线的--------", "网络不给力...");
        lRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        lRecyclerView.setLoadMoreEnabled(false);
        lRecyclerView.setPullRefreshEnabled(false);
        lRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
              //  if (dengLuBean.getStatus()==0){
                    startActivity(new Intent(RenWuLiuChengActivity.this, DaKaActivity_ChaKan.class)
                            .putExtra("recordId", stringList.get(position).getId())
                            .putExtra("itemId", stringList.get(position).getItem_id())
                            .putExtra("lineId", stringList.get(position).getLine_id())
                            .putExtra("patrolId", stringList.get(position).getXungeng_id())
                            .putExtra("luxian",luxian)
                            .putExtra("imgs",stringList.get(position).getImgs())
                            .putExtra("vedios",stringList.get(position).getVedios())
                            .putExtra("qita",stringList.get(position).getOther()));
              //  }
                // startActivity(new Intent(RenWuLiuChengActivity.this,RenWuLiuChengActivity.class));

            }
        });

        beaconManager.setRangingListener(new BeaconManager.RangingListener() {
            @Override
            public void onBeaconsDiscovered(Region region,
                                            final List<Beacon> beacons) {

                 // Log.i("RenWuLiuChengActivity", "beacons.size = " + beacons.size()+isShow);
                myBeacons.clear();
                if (beacons.size() > 0)
                    myBeacons.addAll(beacons);
                ComparatorBeaconByRssi com = new ComparatorBeaconByRssi();
                Collections.sort(myBeacons, com);
                if (myBeacons.size() > 0) {
                  //  Log.d("RenWuLiuChengActivity", "jinlai0");
                    if (myBeacons.get(0).getDistance() <= 5.0f) {
                       // Log.d("RenWuLiuChengActivity", "jinlai1");
                        if (isShow) {
                          //  Log.d("RenWuLiuChengActivity", "jinlai2");
                            isShow = false;
                            int size = stringList.size();
                            if (size > 0) {
                                for (int i = 0; i < size; i++) {
                                 //   Log.d("RenWuLiuChengActivity", "ggg");
                                      if (stringList.get(i).getStatus()==1 && stringList.get(i).getMac().equals(myBeacons.get(0).getMacAddress().replaceAll(":", ""))) {
                                        isShow = false;
                                        startActivity(new Intent(RenWuLiuChengActivity.this, DaKaActivity.class)
                                                .putExtra("recordId", stringList.get(i).getId())
                                                .putExtra("itemId", stringList.get(i).getItem_id())
                                                .putExtra("lineId", stringList.get(i).getLine_id())
                                                .putExtra("patrolId", stringList.get(i).getXungeng_id())
                                                .putExtra("qita",stringList.get(i).getOther())
                                                .putExtra("luxian",luxian));

                                        break;
                                    }else {
                                        if (i==size-1)
                                        isShow = true;
                                    }

                                }


                            } else {

                                isShow = true;
                            }


                        }
                    }
                }


            }
        });

        beaconManager.setMonitoringListener(new BeaconManager.MonitoringListener() {

            @Override
            public void onExitedRegion(Region arg0) {
                showMSG("进入范围", 3);

            }

            @Override
            public void onEnteredRegion(Region arg0, List<Beacon> arg1) {
                showMSG("不在范围", 3);
            }
        });
        renwu.setText(luxian+"");
        link_save();

        if (dengLuBean.getStatus() != 0) {
            final BluetoothManager bluetoothManager =
                    (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
            BluetoothAdapter mBluetoothAdapter = bluetoothManager.getAdapter();
            if (mBluetoothAdapter == null || !mBluetoothAdapter.isEnabled()) {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            } else {
                //开启扫描
                Log.d("MainActivity", "扫描开始");

                connectToService();

            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onDataSynEvent(MainBean bean) {
        Log.d("kkk", "收到" + bean.isIstrue());
        if (bean.isIstrue()) {
            isShow = true;
        }
        if (bean.isShuaXin()) {
            if (stringList.size()>0)
                stringList.clear();
            link_save();
        }

    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_ENABLE_BT) {
            if (resultCode == Activity.RESULT_OK) {
                //开始扫描
                Log.d("MainActivity", "开始扫描");


                connectToService();

            } else {
                showMSG("开启蓝牙失败,无法巡更,请重新开启蓝牙", 3);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 连接服务 开始搜索beacon connect service start scan beacons
     */
    private void connectToService() {
        //   Log.i(TAG, "connectToService");
        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                try {
                    beaconManager.startRanging(ALL_BEACONS_REGION);
                    // beaconManager.startMonitoring(ALL_BEACONS_REGION);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        isShow=true;
        super.onDestroy();
        try {
            if (EventBus.getDefault().isRegistered(RenWuLiuChengActivity.this)) {
                EventBus.getDefault().unregister(this);//解除订阅
                Log.d("kkk", "解除订阅");
            }
            myBeacons.clear();
            beaconManager.stopRanging(ALL_BEACONS_REGION);
            beaconManager.disconnect();

        } catch (RemoteException e) {
            Log.d("kkk", "Error while stopping ranging", e);
        }
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }

    private void link_save() {
        showDialog();

        final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        OkHttpClient okHttpClient = MyAppLaction.getOkHttpClient();

        // String jiami= Utils.jiami(mima).toUpperCase();
        String nonce = Utils.getNonce();
        String timestamp = Utils.getTimestamp();

//    /* form的分割线,自己定义 */
//        String boundary = "xx--------------------------------------------------------------xx";
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("cmd", "100");
            jsonObject.put("lineId", lineId);
            //  jsonObject.put("password",jiami);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(JSON, jsonObject.toString());

        Request.Builder requestBuilder = new Request.Builder()
                .header("nonce", nonce)
                .header("timestamp", timestamp)
                .header("schedule_id", schedule_id==null?"":schedule_id)
                .header("userId", dengLuBean.getUserId() + "")
                .header("sign", Utils.encode("100" + lineId + nonce + timestamp
                        + dengLuBean.getUserId() + Utils.signaturePassword))
                .post(body)
                .url(dengLuBean.getZhuji() + "patrols.app");
//        Log.d("LogingActivity", "100"+zhanghao+jiami+nonce+timestamp
//                +"0"+ Utils.signaturePassword);
        // step 3：创建 Call 对象
        Call call = okHttpClient.newCall(requestBuilder.build());

        //step 4: 开始异步请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("AllConnects", "请求识别失败" + e.getMessage());
                dismissDialog();

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                dismissDialog();

                Log.d("AllConnects", "请求识别成功" + call.request().toString());
                //获得返回体
                try {

                    ResponseBody body = response.body();
                    String ss = body.string().trim();
                    Log.d("InFoActivity", "patrols" + ss);
                    JsonObject jsonObject = GsonUtil.parse(ss).getAsJsonObject();
                    Gson gson = new Gson();
                    // JsonObject jsonElement= jsonObject.get("account").getAsJsonObject();
                    XuanGengDian zhaoPianBean = gson.fromJson(jsonObject, XuanGengDian.class);
                    if (jsonObject.get("dtoResult").getAsString().equals("0")) {
                        //  showMSG(jsonObject.get("dtoDesc").getAsString(),4);
                        stringList.addAll(zhaoPianBean.getObjects());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapter.notifyDataSetChanged();
                            }
                        });

                    } else {

                        showMSG(jsonObject.get("dtoDesc").getAsString(), 4);
                    }

                } catch (Exception e) {

                    dismissDialog();
                    showMSG("获取数据失败", 3);
                    Log.d("WebsocketPushMsg", e.getMessage());
                }
            }
        });

    }

    private void showDialog() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (tiJIaoDialog == null) {
                    tiJIaoDialog = new TiJIaoDialog(RenWuLiuChengActivity.this);
                    if (!RenWuLiuChengActivity.this.isFinishing())
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

    private void showMSG(final String s, final int i) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Toast tastyToast = TastyToast.makeText(RenWuLiuChengActivity.this, s, TastyToast.LENGTH_LONG, i);
                tastyToast.setGravity(Gravity.CENTER, 0, 0);
                tastyToast.show();

            }
        });
    }

}
