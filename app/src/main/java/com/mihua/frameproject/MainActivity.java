package com.mihua.frameproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.mihua.frameproject.pay.PayActivity;
import com.mihua.frameproject.qrcode.QrCodeActivity;
import com.mihua.frameproject.request.RequestActivity;
import com.mihua.frameproject.sharesdk.LoginActivity;
import com.mihua.frameproject.vlayout.VLayoutActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.vlayout,R.id.request,R.id.share_sdk,R.id.qr_code,R.id.pay})
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
        }
    }

    public void gotoActivity(Class c){
        Intent intent = new Intent(this, c);
        startActivity(intent);
    }

}
