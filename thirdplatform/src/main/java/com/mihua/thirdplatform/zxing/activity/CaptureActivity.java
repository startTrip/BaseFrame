package com.mihua.thirdplatform.zxing.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.mihua.thirdplatform.R;


/**
 * Initial the camera
 *
 * 默认的二维码扫描Activity
 */
public class CaptureActivity extends AppCompatActivity implements View.OnClickListener {


    private Button mCamar;
    private Button mFlash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera);
        CaptureFragment captureFragment = new CaptureFragment();
        captureFragment.setAnalyzeCallback(analyzeCallback);
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_zxing_container, captureFragment).commit();

        mCamar = (Button) findViewById(R.id.camar);
        mFlash = (Button) findViewById(R.id.flash);

        mCamar.setOnClickListener(this);
        mFlash.setOnClickListener(this);
    }

    /**
     * 二维码解析回调函数
     */
    ZXingUtils.AnalyzeCallback analyzeCallback = new ZXingUtils.AnalyzeCallback() {
        @Override
        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
            Intent resultIntent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putInt(ZXingUtils.RESULT_TYPE, ZXingUtils.RESULT_SUCCESS);
            bundle.putString(ZXingUtils.RESULT_STRING, result);
            resultIntent.putExtras(bundle);
            CaptureActivity.this.setResult(RESULT_OK, resultIntent);
            CaptureActivity.this.finish();
        }

        @Override
        public void onAnalyzeFailed() {
            Intent resultIntent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putInt(ZXingUtils.RESULT_TYPE, ZXingUtils.RESULT_FAILED);
            bundle.putString(ZXingUtils.RESULT_STRING, "");
            resultIntent.putExtras(bundle);
            CaptureActivity.this.setResult(RESULT_OK, resultIntent);
            CaptureActivity.this.finish();
        }
    };

    public static boolean isOpen = false;

    @Override
    public void onClick(View v) {
        int id = v.getId();

        // 点击 闪关灯
        if(id==R.id.flash){

            if (!isOpen) {
                ZXingUtils.isLightEnable(true);
                isOpen = true;
            } else {
                ZXingUtils.isLightEnable(false);
                isOpen = false;
            }
        }else if(id==R.id.camar){  // 从相册中选择
            // 选中图片并解析
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("image/*");
            startActivityForResult(intent,1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            Uri uri = data.getData();
            try {
                // 去解析图片
                ZXingUtils.analyzeBitmap(ZXingUtils.getImageAbsolutePath(this, uri), new ZXingUtils.AnalyzeCallback() {
                    @Override
                    public void onAnalyzeSuccess(Bitmap mBitmap, String result) {

                        Intent resultIntent = new Intent();
                        Bundle bundle = new Bundle();
                        bundle.putInt(ZXingUtils.RESULT_TYPE, ZXingUtils.RESULT_SUCCESS);
                        bundle.putString(ZXingUtils.RESULT_STRING, result);
                        resultIntent.putExtras(bundle);
                        CaptureActivity.this.setResult(RESULT_OK, resultIntent);
                        CaptureActivity.this.finish();
                    }

                    @Override
                    public void onAnalyzeFailed() {
                        Intent resultIntent = new Intent();
                        Bundle bundle = new Bundle();
                        bundle.putInt(ZXingUtils.RESULT_TYPE, ZXingUtils.RESULT_FAILED);
                        bundle.putString(ZXingUtils.RESULT_STRING, "");
                        resultIntent.putExtras(bundle);
                        CaptureActivity.this.setResult(RESULT_OK, resultIntent);
                        CaptureActivity.this.finish();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}