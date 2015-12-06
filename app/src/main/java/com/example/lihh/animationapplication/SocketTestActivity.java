package com.example.lihh.animationapplication;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;


public class SocketTestActivity extends Activity {

    TextView socketResult;
    Handler handler= new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            String result = bundle.getString("data");
            socketResult.setText("resukt:"+result);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket_test);
        socketResult = (TextView) findViewById(R.id.socketResult);
        new Thread(){
            @Override
            public void run(){
                try {
                    Socket socket = new Socket("182.92.226.85", 9501);
                    BufferedReader br = new BufferedReader(
                            new InputStreamReader(socket.getInputStream()));
                    String line = br.readLine();
                    Message msg = handler.obtainMessage();
                    Bundle bundle = new Bundle();
                    bundle.putString("data", line);
                    msg.setData(bundle);
                    handler.sendMessage(msg);
//                    socketResult.setText(line);
                    br.close();
                    socket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

}
