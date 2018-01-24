package com.xiaojun.xungengguanliyuan.ui;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.VideoView;

import com.xiaojun.xungengguanliyuan.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoActivity extends Activity {

    @BindView(R.id.videoview)
    VideoView videoview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        ButterKnife.bind(this);
        String url=getIntent().getStringExtra("url");
        if (url!=null)
        videoview.setVideoPath(url);
        videoview.start();
        videoview.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                finish();
            }
        });


    }
}
