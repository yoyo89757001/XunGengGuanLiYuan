package com.xiaojun.xungengguanliyuan.ui;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aprilbrother.aprilbrothersdk.Beacon;
import com.aprilbrother.aprilbrothersdk.BeaconManager;
import com.aprilbrother.aprilbrothersdk.Region;
import com.aprilbrother.aprilbrothersdk.utils.AprilL;
import com.sdsmdg.tastytoast.TastyToast;
import com.xiaojun.xungengguanliyuan.MyAppLaction;
import com.xiaojun.xungengguanliyuan.R;
import com.xiaojun.xungengguanliyuan.beans.DengLuBean;
import com.xiaojun.xungengguanliyuan.beans.DengLuBeanDao;
import com.xiaojun.xungengguanliyuan.beans.MainBean;
import com.xiaojun.xungengguanliyuan.dialog.TiJIaoDialog;
import com.xiaojun.xungengguanliyuan.utils.ComparatorBeaconByRssi;
import com.xiaojun.xungengguanliyuan.utils.SpringEffect;
import com.xiaojun.xungengguanliyuan.views.ViewPagerFragmentAdapter;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.PermissionListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.Call;


public class MainActivity extends FragmentActivity  {
  //  private RelativeLayout r1,r2,rl_zhong;
    private ViewPager mViewPager;
    private ViewPagerFragmentAdapter mViewPagerFragmentAdapter;
    private List<Fragment> mFragmentList = new ArrayList<>();
    private ImageView tabIm,tabIm2,zhong_im;
    private TextView tabText,tabText2,zhong_tv;
    // 定义一个变量，来标识是否退出
    private static boolean isExit = false;
    private TiJIaoDialog tiJIaoDialog=null;
    private Call call=null;
    // private int maxCount=0;
    //定义一个过滤器；
    private IntentFilter intentFilter;
    //定义一个广播监听器；
    private NetChangReceiver netChangReceiver;
    private DengLuBean dengLuBean=null;
    private DengLuBeanDao dengLuBeanDao=null;
    private static final String TAG = "MainActivity";
    private RelativeLayout zhong_rl;



    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dengLuBeanDao= MyAppLaction.myAppLaction.getDaoSession().getDengLuBeanDao();
        dengLuBean=dengLuBeanDao.load(123456L);
        FragmentManager mFragmentManager = getSupportFragmentManager();
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
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
      //  EventBus.getDefault().register(MainActivity.this);//订阅

        setContentView(R.layout.activity_main);
        //实例化过滤器；
        intentFilter = new IntentFilter();
        //添加过滤的Action值；
        intentFilter.addAction("guanbiyemian");
        //实例化广播监听器；
        netChangReceiver = new NetChangReceiver();
        //将广播监听器和过滤器注册在一起；
        registerReceiver(netChangReceiver, intentFilter);

        initFragmetList();

        mViewPagerFragmentAdapter = new ViewPagerFragmentAdapter(mFragmentManager,mFragmentList);

        initView();
        initViewPager();


        AndPermission.with(MainActivity.this)
                .requestCode(300)
                .permission(Permission.STORAGE,Permission.CAMERA)
                .callback(listener)
                .start();

        if (dengLuBean.getStatus()!=0){
            zhong_rl.setVisibility(View.GONE);
        }

    }




    private PermissionListener listener = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode,  List<String> grantedPermissions) {
            // 权限申请成功回调。
            // 这里的requestCode就是申请时设置的requestCode。
            // 和onActivityResult()的requestCode一样，用来区分多个不同的请求。
            if(requestCode == 300) {
             // Initializes Bluetooth adapter.




            }
        }

        @Override
        public void onFailed(int requestCode,  List<String> deniedPermissions) {
            // 权限申请失败回调。
            showMSG("授权失败,请到设置--软件权限界面重新授权",3);

        }
    };






    @Override
    protected void onStop() {
        Log.d(TAG, "停止");

        super.onStop();
    }



    private  class NetChangReceiver extends BroadcastReceiver {

        //重写onReceive方法，该方法的实体为，接收到广播后的执行代码；
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("guanbiyemian")){
                finish();
            }
        }
    }


    private void showMSG(final String s,final int i){

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Toast tastyToast= TastyToast.makeText(MainActivity.this,s,TastyToast.LENGTH_LONG,i);
                tastyToast.setGravity(Gravity.CENTER,0,0);
                tastyToast.show();

            }
        });
    }

    @Override
    protected void onDestroy() {
        if (call!=null)
            call.cancel();

        unregisterReceiver(netChangReceiver);
//        if ( EventBus.getDefault().isRegistered(MainActivity.this)){
//            EventBus.getDefault().unregister(this);//解除订阅
//            Log.d(TAG, "解除订阅");
//        }
        super.onDestroy();
    }

    private void initViewPager() {

        mViewPager.addOnPageChangeListener(new ViewPagetOnPagerChangedLisenter());
        mViewPager.setAdapter(mViewPagerFragmentAdapter);
        mViewPager.setCurrentItem(0);
        mViewPager.setOffscreenPageLimit(3);
        updateBottomLinearLayoutSelect(0);
    }

    private void initFragmetList() {
        Fragment fragment1 = new Fragment1();
        Fragment fragment2 = new Fragment2();
        Fragment fragment3 = new FragmentZhong();
        mFragmentList.add(fragment1);
        if (dengLuBean.getStatus()==0)
        mFragmentList.add(fragment3);
        mFragmentList.add(fragment2);
    }

    private void initView() {
        mViewPager= (ViewPager) findViewById(R.id.viewpage);
     //   r1= (RelativeLayout) findViewById(R.id.homeLayout);
      //  r1.setOnClickListener(this);
     //   r2= (RelativeLayout) findViewById(R.id.chosenLayout);
       // r2.setOnClickListener(this);
        zhong_rl= (RelativeLayout) findViewById(R.id.zhong_rl);
        tabIm= (ImageView) findViewById(R.id.tabImg);
        tabIm2= (ImageView) findViewById(R.id.tabImg2);
        zhong_im= (ImageView) findViewById(R.id.zhong_im);

        tabText= (TextView) findViewById(R.id.tabText);
        tabText2= (TextView) findViewById(R.id.tabText2);
        zhong_tv= (TextView) findViewById(R.id.zhong_tv);

        SpringEffect.doEffectSticky(findViewById(R.id.homeLayout), new Runnable() {
            @Override
            public void run() {

                mViewPager.setCurrentItem(0);
                updateBottomLinearLayoutSelect(0);

            }
        });
        SpringEffect.doEffectSticky(findViewById(R.id.chosenLayout), new Runnable() {
            @Override
            public void run() {

                mViewPager.setCurrentItem(2);
                updateBottomLinearLayoutSelect(2);

            }
        });
        SpringEffect.doEffectSticky(findViewById(R.id.zhong_rl), new Runnable() {
            @Override
            public void run() {

                mViewPager.setCurrentItem(1);
                updateBottomLinearLayoutSelect(1);

            }
        });

    }




    private void  updateBottomLinearLayoutSelect(int position) {
        tabText.setTextColor(Color.parseColor("#b4b2b2"));
        tabText2.setTextColor(Color.parseColor("#b4b2b2"));
        zhong_tv.setTextColor(Color.parseColor("#b4b2b2"));

        switch (position){
            case 0:
                tabIm.setImageResource(R.drawable.ic_home_p);
                tabIm2.setImageResource(R.drawable.ic_my);
                zhong_im.setImageResource(R.drawable.ic_report);
                tabText.setTextColor(Color.parseColor("#00C196"));
                break;
            case 1:
                if (dengLuBean.getStatus()==0){
                    zhong_tv.setTextColor(Color.parseColor("#00C196"));
                    tabIm.setImageResource(R.drawable.ic_home);
                    zhong_im.setImageResource(R.drawable.ic_report_p);
                    tabIm2.setImageResource(R.drawable.ic_my);
                }else {
                    tabText2.setTextColor(Color.parseColor("#00C196"));
                    tabIm.setImageResource(R.drawable.ic_home);
                    zhong_im.setImageResource(R.drawable.ic_report);
                    tabIm2.setImageResource(R.drawable.ic_my_p);
                }
                break;
            case 2:
                tabText2.setTextColor(Color.parseColor("#00C196"));
                tabIm.setImageResource(R.drawable.ic_home);
                zhong_im.setImageResource(R.drawable.ic_report);
                tabIm2.setImageResource(R.drawable.ic_my_p);

                break;
        }

    }
    private class ViewPagetOnPagerChangedLisenter implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//            Log.d(TAG,"onPageScrooled");
        }
        @Override
        public void onPageSelected(int position) {

            updateBottomLinearLayoutSelect(position);
        }
        @Override
        public void onPageScrollStateChanged(int state) {
            Log.d("home","onPageScrollStateChanged");
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if (!isExit) {
            isExit = true;
            TastyToast.makeText(getApplicationContext(), "再按一次退出程序",TastyToast.LENGTH_SHORT,TastyToast.INFO).show();
            // 利用handler延迟发送更改状态信息
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {

            finish();
            System.exit(0);
        }
    }
}
