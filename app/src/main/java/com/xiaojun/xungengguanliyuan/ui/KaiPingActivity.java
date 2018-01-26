package com.xiaojun.xungengguanliyuan.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;

import com.xiaojun.xungengguanliyuan.R;


public class KaiPingActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kai_ping);

        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(3000);
                startActivity(new Intent(KaiPingActivity.this,LogingActivity.class));
                finish();

            }
        }).start();


    }
}
