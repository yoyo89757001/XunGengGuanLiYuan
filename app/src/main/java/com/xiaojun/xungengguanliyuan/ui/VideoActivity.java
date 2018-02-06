package com.xiaojun.xungengguanliyuan.ui;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.VideoView;

import com.xiaojun.xungengguanliyuan.MyAppLaction;
import com.xiaojun.xungengguanliyuan.R;
import com.xiaojun.xungengguanliyuan.beans.DengLuBean;
import com.xiaojun.xungengguanliyuan.beans.DengLuBeanDao;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoActivity extends Activity {

    @BindView(R.id.videoview)
    VideoView videoview;
    private int sss=0;
    private DengLuBean dengLuBean = null;
    private DengLuBeanDao dengLuBeanDao = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sss=getIntent().getIntExtra("sss",0);
        dengLuBeanDao = MyAppLaction.myAppLaction.getDaoSession().getDengLuBeanDao();
        dengLuBean = dengLuBeanDao.load(123456L);
        setContentView(R.layout.activity_video);
        ButterKnife.bind(this);
        String url=getIntent().getStringExtra("url");
        if (url!=null){
            if (sss==1){
                Log.d("VideoActivity", dengLuBean.getZhuji() + "patrolImage" + "/" + url);
                videoview.setVideoURI(Uri.parse(dengLuBean.getZhuji().replaceAll("api/","")+"patrolImages"+"/"+url));
            }else {

                videoview.setVideoPath(url);
            }

        }

        videoview.start();
        videoview.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                finish();
            }
        });


    }
}
