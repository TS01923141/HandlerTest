package com.example.tyc.handlertest;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

/**
 * 流程
 * 1.Thread2設定Message
 * 2.Thread2的Handler用sendMessage方法發出message至Thread1的MessageQueue
 * 3.Thread1的Handler用handleMessage方法透過Looper從MessageQueue接收Message
 * 4.Thread1的Handler用取得的Message資料設定進度條
 */

//Thread1(MainThread)
public class MainActivity extends AppCompatActivity {

    Thread thread;
    Handler handler;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressBar);
        //建立Thread2
        thread = new Thread(new MyThread());
        //執行Thread2
        thread.start();
        //建立Thread1(MainThread)的Handler
        handler = new Handler(){
            //3.Thread1用Handler用handleMessage方法透過Looper從MessageQueue接收Message
            @Override
            public void handleMessage(Message msg) {
                //4.Handler用取得的Message資料設定進度條
                progressBar.setProgress(msg.arg1);
            }
        };
    }
    //Thread2
    class MyThread implements Runnable{
        @Override
        public void run() {
            for (int i =0; i<100; i++){
                //1.設定Message，arg1為進度條的進度
                Message message = Message.obtain();
                message.arg1=i;
                //2.發出message(送往Thread1的MessageQueue)
                handler.sendMessage(message);
                try {
                    //間隔100毫秒
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
