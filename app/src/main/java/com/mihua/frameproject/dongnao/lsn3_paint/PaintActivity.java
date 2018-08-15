package com.mihua.frameproject.dongnao.lsn3_paint;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mihua.frameproject.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.ButterKnife;

public class PaintActivity extends AppCompatActivity {

//    @BindView(R.id.progress)
//    CircleProgressBar mProgress;
        int progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint);
        ButterKnife.bind(this);

        List<String> list = new ArrayList<>();
        Iterator<String> iterator = list.iterator();
    }

//    @OnClick(R.id.progress)
//    public void onClick() {
//
////        new Thread(){
////            @Override
////            public void run() {
////                super.run();
////                 while (progress<100){
////                    progress+=2;
////                    mProgress.setProgress(progress);
////                    try {
////                        Thread.sleep(300);
////                        mProgress.postInvalidate();
////                    } catch (InterruptedException e) {
////                        e.printStackTrace();
////                     }
////                }
////            }
////        }.start();
//
//    }
}
