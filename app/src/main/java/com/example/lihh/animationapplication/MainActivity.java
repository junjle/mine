package com.example.lihh.animationapplication;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends Activity {

    Button btn_play;
    Button btn_stop;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView)findViewById(R.id.imageView);
        btn_play = (Button)findViewById(R.id.play);

        final Animation anim = AnimationUtils.loadAnimation(this,R.anim.anim);
        anim.setFillAfter(true);
        final Animation reverse = AnimationUtils.loadAnimation(this,R.anim.reverse);
        reverse.setFillAfter(true);

        final Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what==0x123){
                    imageView.startAnimation(reverse);
                }
            }
        };

        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.startAnimation(anim);
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        handler.sendEmptyMessage(0x123);
                    }
                }, 3500);
            }
        });

    }

}
