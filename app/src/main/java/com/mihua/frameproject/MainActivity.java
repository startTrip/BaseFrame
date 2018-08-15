package com.mihua.frameproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.mihua.frameproject.bluetooth.BlueToothActivity;
import com.mihua.frameproject.dongnao.lsn4_paint_shader.ShaderActivity;
import com.mihua.frameproject.pay.PayActivity;
import com.mihua.frameproject.qrcode.QrCodeActivity;
import com.mihua.frameproject.request.RequestActivity;
import com.mihua.frameproject.sharesdk.LoginActivity;
import com.mihua.frameproject.ui.elm.ShoppingCartActivity;
import com.mihua.frameproject.vlayout.VLayoutActivity;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.tv_params)
    TextView mTextView;

    public native String getStringFromC();

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("hello");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

//        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
//        float density = displayMetrics.density;
//        int densityDpi = displayMetrics.densityDpi;
//        int heightPixels = displayMetrics.heightPixels;
//        float scaledDensity = displayMetrics.scaledDensity;
//        int widthPixels = displayMetrics.widthPixels;
//        float xdpi = displayMetrics.xdpi;
//        float ydpi = displayMetrics.ydpi;
//        String params = "density" + density + "\r\n"
//                + "densityDpi" + densityDpi + "\r\n"
//                + "heightPixels" + heightPixels + "\r\n"
//                + "scaledDensity" + scaledDensity + "\r\n"
//                + "widthPixels" + widthPixels + "\r\n"
//                + "xdpi" + xdpi + "\r\n"
//                + "ydpi" + ydpi + "\r\n";
//        Logger.d(params);
//        mTextView.setText(params);
        mTextView.setText(getStringFromC());
        Thread thread = Thread.currentThread();
        String name = thread.getName();
        Logger.d(name);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Thread thread = Thread.currentThread();
                String name = thread.getName();
                Logger.d(name);
            }
        },1000);

        Thread thread1 = new Thread(){
            @Override
            public void run() {
                super.run();
                Thread thread2 = Thread.currentThread();
                Logger.d("th2"+thread2.getName());
            }
        };
        thread1.start();

        String name1 = thread1.getName();
        Logger.d("H"+name1);
    }

    @OnClick({R.id.vlayout
//            ,R.id.request,R.id.share_sdk,R.id.qr_code,R.id.pay,R.id.paint,R.id.elm,
//                R.id.tv_bluetooth
    })
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.vlayout:
                gotoActivity(VLayoutActivity.class);
                break;
            case R.id.request:
              gotoActivity(RequestActivity.class);
                break;
            case R.id.share_sdk:
                gotoActivity(LoginActivity.class);
                break;
            case R.id.qr_code:
                gotoActivity(QrCodeActivity.class);
                break;
            case R.id.pay:
                gotoActivity(PayActivity.class);
                break;
            case R.id.paint:
                gotoActivity(ShaderActivity.class);
                break;
            case R.id.elm:
                gotoActivity(ShoppingCartActivity.class);
                break;
            case R.id.tv_bluetooth:
                gotoActivity(BlueToothActivity.class);

        }
    }

    public void gotoActivity(Class c){
        Intent intent = new Intent(this, c);
        startActivity(intent);
    }

}
