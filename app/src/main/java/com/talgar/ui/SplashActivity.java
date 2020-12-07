package com.talgar.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.talgar.R;

public class SplashActivity extends AppCompatActivity {

    private static final long delayTime = 1000;
    private Context context;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        this.context = SplashActivity.this;
        handler.postDelayed(postTask, delayTime);
    }

    @Override
    protected void onDestroy() {
        handler.removeCallbacks(postTask);
        super.onDestroy();
    }
    Runnable postTask = new Runnable() {
        @Override
        public void run() {
            startActivity(new Intent(context, MainActivity.class));
            overridePendingTransition(0, 0);
            finish();
        }
    };

}