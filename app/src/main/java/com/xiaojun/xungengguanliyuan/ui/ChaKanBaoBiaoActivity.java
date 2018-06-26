package com.xiaojun.xungengguanliyuan.ui;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sdsmdg.tastytoast.TastyToast;
import com.xiaojun.xungengguanliyuan.MyAppLaction;
import com.xiaojun.xungengguanliyuan.R;
import com.xiaojun.xungengguanliyuan.adapter.ChaKanAdapter;
import com.xiaojun.xungengguanliyuan.beans.BaoBiao2;
import com.xiaojun.xungengguanliyuan.beans.ChuanBean;
import com.xiaojun.xungengguanliyuan.beans.DengLuBean;
import com.xiaojun.xungengguanliyuan.beans.DengLuBeanDao;
import com.xiaojun.xungengguanliyuan.dialog.TiJIaoDialog;
import com.xiaojun.xungengguanliyuan.utils.GsonUtil;
import com.xiaojun.xungengguanliyuan.utils.Utils;
import com.xiaojun.xungengguanliyuan.views.WrapContentLinearLayoutManager;

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

public class ChaKanBaoBiaoActivity extends Activity {
    @BindView(R.id.back)
    ImageView back;
    private LRecyclerView lRecyclerView;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private List<BaoBiao2.ObjectsBean> stringList = new ArrayList<>();
    private ChaKanAdapter adapter;
    private DengLuBean dengLuBean = null;
    private DengLuBeanDao dengLuBeanDao = null;
    private TiJIaoDialog tiJIaoDialog = null;
    private int page = 1;
    private ChuanBean bean = null;

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
        setContentView(R.layout.activity_cha_kan_bao_biao);
        ButterKnife.bind(this);
        dengLuBeanDao = MyAppLaction.myAppLaction.getDaoSession().getDengLuBeanDao();
        dengLuBean = dengLuBeanDao.load(123456L);
        lRecyclerView = (LRecyclerView) findViewById(R.id.recyclerView);
        adapter = new ChaKanAdapter(stringList);
        bean = (ChuanBean) getIntent().getSerializableExtra("chuan");
          Log.d("ChaKanBaoBiaoActivity", bean.toString()+"kkkkkkkk");

        lRecyclerViewAdapter = new LRecyclerViewAdapter(adapter);
        WrapContentLinearLayoutManager linearLayoutManager = new WrapContentLinearLayoutManager(ChaKanBaoBiaoActivity.this, LinearLayoutManager.VERTICAL, false);
        lRecyclerView.setLayoutManager(linearLayoutManager);
        lRecyclerView.setAdapter(lRecyclerViewAdapter);

        final DividerDecoration divider = new DividerDecoration.Builder(ChaKanBaoBiaoActivity.this)
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
        lRecyclerView.setLoadMoreEnabled(true);
        lRecyclerView.setPullRefreshEnabled(true);
        lRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {


            }
        });


        lRecyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                //下拉刷新
                //  Log.d("Fragment144444", "下拉刷新");
                page = 1;
                switch (bean.getType()) {
                    case 1:

                        link_lines1(page);

                        break;
                    case 2:

                        link_lines2(page);

                        break;
                    case 3:

                        link_lines3(page);

                        break;
                }


            }
        });


        lRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {

                switch (bean.getType()) {
                    case 1:

                        link_lines1(++page);

                        break;
                    case 2:

                        link_lines2(++page);

                        break;
                    case 3:

                        link_lines3(++page);

                        break;
                }

            }
        });


        lRecyclerView.forceToRefresh();

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

                Toast tastyToast = TastyToast.makeText(ChaKanBaoBiaoActivity.this, s, TastyToast.LENGTH_LONG, i);
                tastyToast.setGravity(Gravity.CENTER, 0, 0);
                tastyToast.show();

            }
        });
    }

    private void link_lines1(final int page) {
        //  showDialog();
        Log.d("ChaKanBaoBiaoActivity", "ffffffffffffffffff");
        final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        OkHttpClient okHttpClient = MyAppLaction.getOkHttpClient();

        //  String jiami= Utils.jiami(mima).toUpperCase();
        String nonce = Utils.getNonce();
        String timestamp = Utils.getTimestamp();

//    /* form的分割线,自己定义 */
//        String boundary = "xx--------------------------------------------------------------xx";
        JSONObject jsonObject = new JSONObject();
        try {
            String riqi1 = "", riqi2 = "", duan1 = "", duan2 = "";
            if (!bean.getShijian1().equals("")) {
                riqi1 = bean.getShijian1().split(" ")[0];
                duan1 = "1" + bean.getShijian1().split(" ")[1].replace(":", "");
            }
            if (!bean.getShijian2().equals("")) {
                riqi2 = bean.getShijian2().split(" ")[0];
                duan2 = "1" + bean.getShijian2().split(" ")[1].replace(":", "");
            }

            jsonObject.put("cmd", "100");
            jsonObject.put("name", bean.getName());
            jsonObject.put("item_name", bean.getXiangmuMing());
            jsonObject.put("schedule_id", bean.getBianhao());
            jsonObject.put("str_btime", riqi1);
            jsonObject.put("str_etime", riqi2);
            jsonObject.put("s_hour", duan1);
            jsonObject.put("e_hour", duan2);
            jsonObject.put("page_num", page);
            jsonObject.put("page_size", "30");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(JSON, jsonObject.toString());
        Log.d("ChaKanBaoBiaoActivity", jsonObject.toString()+"[][][]");
        Request.Builder requestBuilder = new Request.Builder()
                .header("nonce", nonce)
                .header("timestamp", timestamp)
                .header("userId", dengLuBean.getUserId() + "")
                .header("sign", Utils.encode("100" + nonce + timestamp
                        + dengLuBean.getUserId() + Utils.signaturePassword))
                .post(body)
                .url(dengLuBean.getZhuji() + "queryReportSummary.app");
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
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        lRecyclerView.refreshComplete(30);
                        adapter.notifyDataSetChanged();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                dismissDialog();

                Log.d("AllConnects", "请求识别成功" + call.request().toString());
                //获得返回体
                try {
                    ResponseBody body = response.body();
                    String ss = body.string().trim();
                    Log.d("InFoActivity", "chakanbaogao1" + ss);
                    JsonObject jsonObject = GsonUtil.parse(ss).getAsJsonObject();
                    Gson gson = new Gson();
                    // JsonObject jsonElement= jsonObject.get("account").getAsJsonObject();
                    BaoBiao2 zhaoPianBean = gson.fromJson(jsonObject, BaoBiao2.class);
                    if (jsonObject.get("dtoResult").getAsString().equals("0")) {
                        //showMSG(jsonObject.get("dtoDesc").getAsString(),4);
                        if (page == 1) {
                            if (stringList.size() > 0)
                                stringList.clear();
                            stringList.addAll(zhaoPianBean.getObjects());

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    lRecyclerView.refreshComplete(30);
                                    adapter.notifyDataSetChanged();
                                }
                            });

                        } else {

                            if (zhaoPianBean.getObjects() != null && zhaoPianBean.getObjects().size() > 0) {
                                stringList.addAll(zhaoPianBean.getObjects());
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        lRecyclerView.refreshComplete(30);
                                        adapter.notifyDataSetChanged();
                                    }
                                });
                            } else {

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        lRecyclerView.setNoMore(true);
                                        adapter.notifyDataSetChanged();
                                    }
                                });
                            }
                        }


                    } else {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                lRecyclerView.refreshComplete(30);
                                adapter.notifyDataSetChanged();
                            }
                        });
                        showMSG(jsonObject.get("dtoDesc").getAsString(), 4);
                    }

                } catch (Exception e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            lRecyclerView.refreshComplete(30);
                            adapter.notifyDataSetChanged();
                        }
                    });
                    dismissDialog();
                    showMSG("获取数据失败", 3);
                    Log.d("WebsocketPushMsg", e.getMessage());
                }
            }
        });

    }

    private void link_lines2(final int page) {
        //  showDialog();

        final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        OkHttpClient okHttpClient = MyAppLaction.getOkHttpClient();

        //  String jiami= Utils.jiami(mima).toUpperCase();
        String nonce = Utils.getNonce();
        String timestamp = Utils.getTimestamp();

//    /* form的分割线,自己定义 */
//        String boundary = "xx--------------------------------------------------------------xx";
        JSONObject jsonObject = new JSONObject();
        try {
            String riqi1 = "", riqi2 = "", duan1 = "", duan2 = "";
            if (!bean.getShijian1().equals("")) {
                riqi1 = bean.getShijian1().split(" ")[0];
                duan1 = "1" + bean.getShijian1().split(" ")[1].replace(":", "");
            }
            if (!bean.getShijian2().equals("")) {
                riqi2 = bean.getShijian2().split(" ")[0];
                duan2 = "1" + bean.getShijian2().split(" ")[1].replace(":", "");
            }

            jsonObject.put("cmd", "100");
            jsonObject.put("name", bean.getName());
            jsonObject.put("item_name", bean.getXiangmuMing());
            jsonObject.put("schedule_id", bean.getBianhao());
            jsonObject.put("str_btime", riqi1);
            jsonObject.put("str_etime", riqi2);
            jsonObject.put("s_hour", duan1);
            jsonObject.put("e_hour", duan2);
            jsonObject.put("page_num", page);
            jsonObject.put("page_size", "30");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(JSON, jsonObject.toString());
        Log.d("ChaKanBaoBiaoActivity", jsonObject.toString());
        Request.Builder requestBuilder = new Request.Builder()
                .header("nonce", nonce)
                .header("timestamp", timestamp)
                .header("userId", dengLuBean.getUserId() + "")
                .header("sign", Utils.encode("100" + nonce + timestamp
                        + dengLuBean.getUserId() + Utils.signaturePassword))
                .post(body)
                .url(dengLuBean.getZhuji() + "queryReportPoint.app");
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
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        lRecyclerView.refreshComplete(30);
                        adapter.notifyDataSetChanged();
                    }
                });

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                dismissDialog();

                Log.d("AllConnects", "请求识别成功" + call.request().toString());
                //获得返回体
                try {
                    ResponseBody body = response.body();
                    String ss = body.string().trim();
                    Log.d("InFoActivity", "chakanbaogao1" + ss);
                    JsonObject jsonObject = GsonUtil.parse(ss).getAsJsonObject();
                    Gson gson = new Gson();
                    // JsonObject jsonElement= jsonObject.get("account").getAsJsonObject();
                    BaoBiao2 zhaoPianBean = gson.fromJson(jsonObject, BaoBiao2.class);
                    if (jsonObject.get("dtoResult").getAsString().equals("0")) {
                        //showMSG(jsonObject.get("dtoDesc").getAsString(),4);
                        if (page == 1) {
                            if (stringList.size() > 0)
                                stringList.clear();
                            stringList.addAll(zhaoPianBean.getObjects());

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    lRecyclerView.refreshComplete(30);
                                    adapter.notifyDataSetChanged();
                                }
                            });

                        } else {

                            if (zhaoPianBean.getObjects() != null && zhaoPianBean.getObjects().size() > 0) {
                                stringList.addAll(zhaoPianBean.getObjects());
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        lRecyclerView.refreshComplete(30);
                                        adapter.notifyDataSetChanged();
                                    }
                                });
                            } else {

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        lRecyclerView.setNoMore(true);
                                        adapter.notifyDataSetChanged();
                                    }
                                });
                            }
                        }


                    } else {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                lRecyclerView.refreshComplete(30);
                                adapter.notifyDataSetChanged();
                            }
                        });
                        showMSG(jsonObject.get("dtoDesc").getAsString(), 4);
                    }

                } catch (Exception e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            lRecyclerView.refreshComplete(30);
                            adapter.notifyDataSetChanged();
                        }
                    });
                    dismissDialog();
                    showMSG("获取数据失败", 3);
                    Log.d("WebsocketPushMsg", e.getMessage());
                }
            }
        });

    }


    private void link_lines3(final int page) {
        //  showDialog();

        final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        OkHttpClient okHttpClient = MyAppLaction.getOkHttpClient();

        //  String jiami= Utils.jiami(mima).toUpperCase();
        String nonce = Utils.getNonce();
        String timestamp = Utils.getTimestamp();

//    /* form的分割线,自己定义 */
//        String boundary = "xx--------------------------------------------------------------xx";
        JSONObject jsonObject = new JSONObject();
        try {
            String riqi1 = "", riqi2 = "", duan1 = "", duan2 = "";
            if (!bean.getShijian1().equals("")) {
                riqi1 = bean.getShijian1().split(" ")[0];
                duan1 = "1" + bean.getShijian1().split(" ")[1].replace(":", "");
            }
            if (!bean.getShijian2().equals("")) {
                riqi2 = bean.getShijian2().split(" ")[0];
                duan2 = "1" + bean.getShijian2().split(" ")[1].replace(":", "");
            }

            jsonObject.put("cmd", "100");
            jsonObject.put("name", bean.getName());
            jsonObject.put("item_name", bean.getXiangmuMing());
            jsonObject.put("user_name", bean.getName());
            jsonObject.put("schedule_id", bean.getBianhao());
            jsonObject.put("str_btime", riqi1);
            jsonObject.put("str_etime", riqi2);
            jsonObject.put("s_hour", duan1);
            jsonObject.put("e_hour", duan2);
            jsonObject.put("page_num", page);
            jsonObject.put("page_size", "30");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(JSON, jsonObject.toString());
        Log.d("ChaKanBaoBiaoActivity", jsonObject.toString());
        Request.Builder requestBuilder = new Request.Builder()
                .header("nonce", nonce)
                .header("timestamp", timestamp)
                .header("userId", dengLuBean.getUserId() + "")
                .header("sign", Utils.encode("100" + nonce + timestamp
                        + dengLuBean.getUserId() + Utils.signaturePassword))
                .post(body)
                .url(dengLuBean.getZhuji() + "queryReportUser.app");
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
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        lRecyclerView.refreshComplete(30);
                        adapter.notifyDataSetChanged();
                    }
                });

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                dismissDialog();

                Log.d("AllConnects", "请求识别成功" + call.request().toString());
                //获得返回体
                try {
                    ResponseBody body = response.body();
                    String ss = body.string().trim();
                    Log.d("InFoActivity", "chakanbaogao1" + ss);
                    JsonObject jsonObject = GsonUtil.parse(ss).getAsJsonObject();
                    Gson gson = new Gson();
                    // JsonObject jsonElement= jsonObject.get("account").getAsJsonObject();
                    BaoBiao2 zhaoPianBean = gson.fromJson(jsonObject, BaoBiao2.class);
                    if (jsonObject.get("dtoResult").getAsString().equals("0")) {
                        //showMSG(jsonObject.get("dtoDesc").getAsString(),4);
                        if (page == 1) {
                            if (stringList.size() > 0)
                                stringList.clear();
                            stringList.addAll(zhaoPianBean.getObjects());

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    lRecyclerView.refreshComplete(30);
                                    adapter.notifyDataSetChanged();
                                }
                            });

                        } else {

                            if (zhaoPianBean.getObjects() != null && zhaoPianBean.getObjects().size() > 0) {
                                stringList.addAll(zhaoPianBean.getObjects());
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        lRecyclerView.refreshComplete(30);
                                        adapter.notifyDataSetChanged();
                                    }
                                });
                            } else {

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        lRecyclerView.setNoMore(true);
                                        adapter.notifyDataSetChanged();
                                    }
                                });
                            }
                        }


                    } else {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                lRecyclerView.refreshComplete(30);
                                adapter.notifyDataSetChanged();
                            }
                        });
                        showMSG(jsonObject.get("dtoDesc").getAsString(), 4);
                    }

                } catch (Exception e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            lRecyclerView.refreshComplete(30);
                            adapter.notifyDataSetChanged();
                        }
                    });
                    dismissDialog();
                    showMSG("获取数据失败", 3);
                    Log.d("WebsocketPushMsg", e.getMessage());
                }
            }
        });

    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }
}
