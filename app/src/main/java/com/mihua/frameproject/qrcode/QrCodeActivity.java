package com.mihua.frameproject.qrcode;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mihua.code.base.mvp.BaseActivity;
import com.mihua.frameproject.R;
import com.mihua.thirdplatform.zxing.activity.CaptureActivity;
import com.mihua.thirdplatform.zxing.activity.ZXingUtils;
import com.tbruyelle.rxpermissions.RxPermissions;

import butterknife.BindView;
import butterknife.OnClick;

public class QrCodeActivity extends BaseActivity<QrCodeContract.QrCodePresenter> implements QrCodeContract.QrCodeView {


    @BindView(R.id.btn_scan_barcode)
    Button mBtnScanBarcode;
    @BindView(R.id.tv_scan_result)
    TextView mTvScanResult;
    @BindView(R.id.et_qr_string)
    EditText mEtQrString;
    @BindView(R.id.btn_add_qrcode)
    Button mBtnAddQrcode;
    @BindView(R.id.iv_qr_image)
    ImageView mIvQrImage;


    @Override
    protected int getLayout() {
        return R.layout.activity_qr_code;
    }

    @Override
    protected QrCodeContract.QrCodePresenter getPresenter() {
        return new QrPresenter();
    }

    @Override
    protected void initEventAndData(Bundle bundle) {

    }

    @Override
    protected void setListener() {

    }

    @OnClick({R.id.btn_scan_barcode, R.id.btn_add_qrcode})
    public void onClick(View view) {
        switch (view.getId()) {
            // 点击扫一扫
            case R.id.btn_scan_barcode:

                mPresenter.CheckCamaraPermission(new RxPermissions(this));

                break;
            // 点击生成二维码
            case R.id.btn_add_qrcode:
                String string = mEtQrString.getText().toString();
                mPresenter.showQr_Code(string);

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode==RESULT_OK){
            if(requestCode==0){
                if(data!=null){
                    Bundle extras = data.getExtras();
                    if (extras != null) {
                        int stringExtra = extras.getInt(ZXingUtils.RESULT_TYPE);

                        if(stringExtra == ZXingUtils.RESULT_SUCCESS){

                            mTvScanResult.setText(extras.getString(ZXingUtils.RESULT_STRING));

                        }else if (stringExtra == ZXingUtils.RESULT_FAILED){

                            mTvScanResult.setText("解析二维码失败");

                        }
                    }
                }
            }
        }
    }

    // 提醒用户
    @Override
    public void notifyUser(){

        Toast.makeText(this,"没有权限",Toast.LENGTH_SHORT).show();

    }

    // 显示二维码
    @Override
    public void showQrCode(Bitmap bitmap) {
        mIvQrImage.setImageBitmap(bitmap);
    }


    // 跳转到二维码界面
    @Override
    public void NavigationToCapture() {

        Intent i = new Intent(this, CaptureActivity.class);
        startActivityForResult(i,0);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
