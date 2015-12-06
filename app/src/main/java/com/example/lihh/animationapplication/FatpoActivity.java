package com.example.lihh.animationapplication;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class FatpoActivity extends Activity {

    Button btn_play;
    Button btn_stop;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fatpo);

        btn_play = (Button)findViewById(R.id.po_play);
        btn_stop = (Button)findViewById(R.id.po_stop);
        imageView = (ImageView)findViewById(R.id.po_imageView);

        final AnimationDrawable anim = (AnimationDrawable)imageView.getBackground();
        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anim.start();
            }
        });

        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anim.stop();
            }
        });

    }

}
