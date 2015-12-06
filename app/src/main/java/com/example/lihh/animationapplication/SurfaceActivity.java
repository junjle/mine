package com.example.lihh.animationapplication;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;


public class SurfaceActivity extends Activity {

    private SurfaceHolder holder;
    private Paint paint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surface);

        paint = new Paint();
        SurfaceView surfaceView = (SurfaceView)findViewById(R.id.show);
        holder = surfaceView.getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                Canvas canvas = holder.lockCanvas();
                Bitmap back = BitmapFactory.decodeResource(SurfaceActivity.this.getResources(),
                        R.drawable.sun);
                canvas.drawBitmap(back, 0, 0, null);
                holder.unlockCanvasAndPost(canvas);
                holder.lockCanvas(new Rect(0,0,0,0));
                holder.unlockCanvasAndPost(canvas);
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });

        surfaceView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    int cx = (int)event.getX();
                    int cy = (int)event.getY();
                    Canvas canvas = holder.lockCanvas(new Rect(cx - 50, cy - 50,
                            cx + 50, cy + 50 ));
                    canvas.save();
                    canvas.rotate(30, cx, cy);
                    paint.setColor(Color.RED);
                    canvas.drawRect(cx - 40, cy - 40, cx, cy, paint);
                    canvas.restore();
                    paint.setColor(Color.GREEN);
                    canvas.drawRect(cx, cy, cx + 40, cy + 40, paint);
                    holder.unlockCanvasAndPost(canvas);
                }
                return false;
            }
        });


    }

}
