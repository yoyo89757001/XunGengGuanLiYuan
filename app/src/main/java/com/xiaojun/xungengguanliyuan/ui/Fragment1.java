package com.xiaojun.xungengguanliyuan.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
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
import com.xiaojun.xungengguanliyuan.adapter.F1Adapter;
import com.xiaojun.xungengguanliyuan.beans.DengLuBean;
import com.xiaojun.xungengguanliyuan.beans.DengLuBeanDao;
import com.xiaojun.xungengguanliyuan.beans.NameBeans;
import com.xiaojun.xungengguanliyuan.beans.RenBean;
import com.xiaojun.xungengguanliyuan.beans.XianLuBean;
import com.xiaojun.xungengguanliyuan.dialog.NameDialog;
import com.xiaojun.xungengguanliyuan.dialog.NameDialog2;
import com.xiaojun.xungengguanliyuan.dialog.TiJIaoDialog;
import com.xiaojun.xungengguanliyuan.utils.GsonUtil;
import com.xiaojun.xungengguanliyuan.utils.SpringEffect;
import com.xiaojun.xungengguanliyuan.utils.Utils;
import com.xiaojun.xungengguanliyuan.views.WrapContentLinearLayoutManager;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment1 extends Fragment {

    private LRecyclerView lRecyclerView;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private List<XianLuBean.ObjectsBean> stringList=new ArrayList<>();
    private List<NameBeans> stringList22=new ArrayList<>();
    private F1Adapter adapter;
    private TextView name,name22;
    private List<RenBean.ObjectsBean> nameString=new ArrayList<>();
    private DengLuBean dengLuBean=null;
    private DengLuBeanDao dengLuBeanDao=null;
    private TiJIaoDialog tiJIaoDialog=null;
    private int p=0;
    private int p22=1;
    private int p3=0;

    public Fragment1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        dengLuBeanDao= MyAppLaction.myAppLaction.getDaoSession().getDengLuBeanDao();
        dengLuBean=dengLuBeanDao.load(123456L);
        View view = inflater.inflate(R.layout.fragment_fragment1, container, false);
        name= (TextView) view.findViewById(R.id.name);
        name22= (TextView) view.findViewById(R.id.name22);
        lRecyclerView = (LRecyclerView) view.findViewById(R.id.recyclerView);
        adapter = new F1Adapter(stringList);
        stringList22.add(new NameBeans("进行中",true,1));
        stringList22.add(new NameBeans("已完成",false,2));
        stringList22.add(new NameBeans("未开始",false,3));
        stringList22.add(new NameBeans("未打卡",false,4));

        lRecyclerViewAdapter = new LRecyclerViewAdapter(adapter);
        WrapContentLinearLayoutManager linearLayoutManager = new WrapContentLinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        lRecyclerView.setLayoutManager(linearLayoutManager);
        lRecyclerView.setAdapter(lRecyclerViewAdapter);

        final DividerDecoration divider = new DividerDecoration.Builder(getContext())
                .setHeight(2.0f)
                .setPadding(2.0f)
                .setColorResource(R.color.transparent)
                .build();

        lRecyclerView.addItemDecoration(divider);
        //设置头部加载颜色
        lRecyclerView.setHeaderViewColor(R.color.colorAccent, R.color.blake ,R.color.write);
        lRecyclerView.setRefreshProgressStyle(ProgressStyle.LineSpinFadeLoader);
        lRecyclerView.setFooterViewColor(R.color.huise, R.color.blake ,R.color.write);
        //设置底部加载文字提示
        lRecyclerView.setFooterViewHint("拼命加载中","--------我是有底线的--------","网络不给力...");
        lRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        lRecyclerView.setLoadMoreEnabled(false);
        lRecyclerView.setPullRefreshEnabled(true);
        lRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (dengLuBean.getStatus()!=0){
                    startActivity(new Intent(getContext(),RenWuLiuChengActivity.class).
                            putExtra("userid",dengLuBean.getUserId()+"").
                            putExtra("lineId",stringList.get(position).getLine_id()+"").
                            putExtra("luxian",stringList.get(position).getLine_name()).
                            putExtra("schedule_id",stringList.get(position).getSchedule_id()));
                }else {
                startActivity(new Intent(getContext(),RenWuLiuChengActivity.class).
                        putExtra("userid",p+"").
                        putExtra("lineId",stringList.get(position).getLine_id()+"").
                        putExtra("luxian",stringList.get(position).getLine_name()).
                        putExtra("schedule_id",stringList.get(position).getSchedule_id()));
                }

            }
        });


        lRecyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                //下拉刷新
                 //  Log.d("Fragment144444", "下拉刷新");

              //  Log.d("Fragment1", "System.currentTimeMillis()-1523898000000:" + (System.currentTimeMillis() - 1523898000000L));
            //    Log.d("Fragment1", "System.currentTimeMillis()-1523898000000:" + (System.currentTimeMillis() - 1523977200000L));
                if (dengLuBean.getStatus()!=0){
                    link_lines(dengLuBean.getUserId(),p22);
                }else {
                    link_ren();
                }
            }
        });


        lRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {

            }
        });

        SpringEffect.doEffectSticky(view.findViewById(R.id.name_ll), new Runnable() {
            @Override
            public void run() {
                if (nameString.size()>0){
                final NameDialog dialog=new NameDialog(getContext(),nameString);
                dialog.gengxin();
                dialog.setOnPositiveListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (dialog.getData()!=null){
                            name.setText(dialog.getData().getName());
                            p=dialog.getData().getId();
                            link_lines(p,p22);

                        }
                        dialog.dismiss();

                    }
                });
                dialog.setCancelListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
                }else {
                    showMSG("暂无数据",4);
                }

            }
        });

        SpringEffect.doEffectSticky(view.findViewById(R.id.name_22), new Runnable() {
            @Override
            public void run() {
                if (stringList22.size()>0){
                    final NameDialog2 dialog=new NameDialog2(getContext(),stringList22);
                    dialog.gengxin();
                    dialog.setOnPositiveListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (dialog.getData()!=null){
                                name22.setText(dialog.getData().getSs());
                                p22=dialog.getData().getP2();
                                if (dengLuBean.getStatus()!=0)
                                link_lines(dengLuBean.getUserId(),p22);
                                else
                                    link_lines(p,p22);

                            }
                            dialog.dismiss();

                        }
                    });
                    dialog.setCancelListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });

                    dialog.show();
                }else {
                    showMSG("暂无数据",4);
                }

            }
        });

        if (dengLuBean.getStatus()!=0){
            view.findViewById(R.id.name_ll).setVisibility(View.GONE);
        }

        lRecyclerView.forceToRefresh();

        return view;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }



    private void link_lines(long idid,int p22) {
      //  showDialog();

        final MediaType JSON=MediaType.parse("application/json; charset=utf-8");
        OkHttpClient okHttpClient= MyAppLaction.getOkHttpClient();

      //  String jiami= Utils.jiami(mima).toUpperCase();
        String nonce=Utils.getNonce();
        String timestamp=Utils.getTimestamp();

//    /* form的分割线,自己定义 */
//        String boundary = "xx--------------------------------------------------------------xx";
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("cmd","101");
            jsonObject.put("itemId","0");
            jsonObject.put("status",p22+"");
            jsonObject.put("userId",idid);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(JSON, jsonObject.toString());

        Request.Builder requestBuilder = new Request.Builder()
                .header("nonce", nonce)
                .header("timestamp", timestamp)
                .header("userId", dengLuBean.getUserId()+"")
                .header("sign", Utils.encode("101"+"0"+idid+nonce+timestamp
                        +dengLuBean.getUserId()+ Utils.signaturePassword))
                .post(body)
                .url(dengLuBean.getZhuji() + "lines.app");
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
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        lRecyclerView.refreshComplete(20);
                        adapter.notifyDataSetChanged();
                    }
                });

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                dismissDialog();

                Log.d("AllConnects", "请求识别成功"+call.request().toString());
                //获得返回体
                try {

                    ResponseBody body = response.body();
                    String ss=body.string().trim();
                    Log.d("InFoActivity", "lins" + ss);
                    JsonObject jsonObject= GsonUtil.parse(ss).getAsJsonObject();
                    Gson gson=new Gson();
                   // JsonObject jsonElement= jsonObject.get("account").getAsJsonObject();
                    XianLuBean zhaoPianBean=gson.fromJson(jsonObject,XianLuBean.class);
                    if (jsonObject.get("dtoResult").getAsString().equals("0")){
                        //showMSG(jsonObject.get("dtoDesc").getAsString(),4);
                        if (stringList.size()>0)
                            stringList.clear();
                        stringList.addAll(zhaoPianBean.getObjects());
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                lRecyclerView.refreshComplete(20);
                                adapter.notifyDataSetChanged();
                            }
                        });



                    }else {

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                lRecyclerView.refreshComplete(20);
                                adapter.notifyDataSetChanged();
                            }
                        });
                        showMSG(jsonObject.get("dtoDesc").getAsString(),4);
                    }

                }catch (Exception e){
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            lRecyclerView.refreshComplete(20);
                            adapter.notifyDataSetChanged();
                        }
                    });
                    dismissDialog();
                    showMSG("获取数据失败",3);
                    Log.d("WebsocketPushMsg", e.getMessage());
                }
            }
        });

    }

    private void showDialog(){
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (tiJIaoDialog==null){
                    tiJIaoDialog=new TiJIaoDialog(getActivity());
                    if (!getActivity().isFinishing())
                        tiJIaoDialog.show();
                }
            }
        });
    }

    private void link_ren() {
      //  showDialog();

        final MediaType JSON=MediaType.parse("application/json; charset=utf-8");
        OkHttpClient okHttpClient= MyAppLaction.getOkHttpClient();

        //  String jiami= Utils.jiami(mima).toUpperCase();
        final String nonce=Utils.getNonce();
        String timestamp=Utils.getTimestamp();

//    /* form的分割线,自己定义 */
//        String boundary = "xx--------------------------------------------------------------xx";
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("cmd","100");
            jsonObject.put("region",dengLuBean.getRegion_code());
            //  jsonObject.put("password",jiami);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(JSON, jsonObject.toString());

        Request.Builder requestBuilder = new Request.Builder()
                .header("nonce", nonce)
                .header("timestamp", timestamp)
                .header("userId", dengLuBean.getUserId()+"")
                .header("sign", Utils.encode("100"+dengLuBean.getRegion_code()+nonce+timestamp
                        +dengLuBean.getUserId()+ Utils.signaturePassword))
                .post(body)
                .url(dengLuBean.getZhuji() + "accounts.app");
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

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                dismissDialog();

                Log.d("AllConnects", "请求识别成功"+call.request().toString());
                //获得返回体
                try {

                    ResponseBody body = response.body();
                    String ss=body.string().trim();
                    Log.d("InFoActivity", "ss" + ss);
                    JsonObject jsonObject= GsonUtil.parse(ss).getAsJsonObject();
                    Gson gson=new Gson();
                    // JsonObject jsonElement= jsonObject.get("account").getAsJsonObject();
                    final RenBean zhaoPianBean=gson.fromJson(jsonObject,RenBean.class);
                    if (jsonObject.get("dtoResult").getAsString().equals("0")){
                        //showMSG(jsonObject.get("dtoDesc").getAsString(),4);
                        if (zhaoPianBean.getObjects().size()>0){
                            if (nameString.size()>0)
                                nameString.clear();
                            nameString.addAll(zhaoPianBean.getObjects());
                                if (p==0){
                                   p= zhaoPianBean.getObjects().get(0).getId();
                                }
                            link_lines(p,p22);

                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    name.setText(zhaoPianBean.getObjects().get(0).getName());
                                }
                            });
                        }else {

                            showMSG("暂无巡更员数据",4);
                        }

                    }else {

                        showMSG(jsonObject.get("dtoDesc").getAsString(),4);
                    }

                }catch (Exception e){

                    dismissDialog();
                    showMSG("获取数据失败",3);
                    Log.d("WebsocketPushMsg", e.getMessage());
                }
            }
        });

    }



    private void dismissDialog(){
        getActivity().runOnUiThread(new Runnable() {
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

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Toast tastyToast= TastyToast.makeText(getActivity(),s,TastyToast.LENGTH_LONG,i);
                tastyToast.setGravity(Gravity.CENTER,0,0);
                tastyToast.show();

            }
        });
    }

}
