package com.mihua.frameproject.pay;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.mihua.frameproject.R;
import com.mihua.frameproject.frame_code.andfix.DxManager;
import com.mihua.frameproject.pay.utils.AlipayHelper;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PayActivity extends AppCompatActivity {

    @BindView(R.id.alipay)
    TextView mAlipay;
    @BindView(R.id.weixin_pay)
    TextView mWeixinPay;

    // 商户PID  必须以 2088 开头
    public static final String PARTNER = "2088111278561763";
    // 收款人，支付宝账号，账号是固定的，一个商户一个账号
    public static final String SELLER = "gaoyandingzhi@126.com";
    // 客户端使用的私钥，对于请求进行数字签名，传给服务器，服务器使用公钥进行验证

    /** 支付宝支付业务：入参app_id */
    public static final String APPID = "2088111278561763";

    /** 支付宝账户登录授权业务：入参pid值 */
    public static final String PID = "";
    /** 支付宝账户登录授权业务：入参target_id值 */
    public static final String TARGET_ID = "";

    /** 商户私钥，pkcs8格式 */
    /** 如下私钥，RSA2_PRIVATE 或者 RSA_PRIVATE 只需要填入一个 */
    /** 如果商户两个都设置了，优先使用 RSA2_PRIVATE */
    /** RSA2_PRIVATE 可以保证商户交易在更加安全的环境下进行，建议使用 RSA2_PRIVATE */
    /** 获取 RSA2_PRIVATE，建议使用支付宝提供的公私钥生成工具生成， */
    /** 工具地址：https://doc.open.alipay.com/docs/doc.htm?treeId=291&articleId=106097&docType=1 */
    public static final String RSA2_PRIVATE = "";
    public static final String RSA_PRIVATE = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBANEClM9ja39OuhbiFcPYG8nUt19TIGvnBjC2CGMV3BKY2pTolVuicMfM0yyxvwtewe7Wkk+06Zl8fjgIWZS8SsfOeznQZbJq236CbcFYIhDsorDllDwQ0Uk409WSjaOCDJamOjGeQjYqy3D7v+z+Z48ZvCOPleX2h415mHQeHWVdAgMBAAECgYB6FrHqOr7uTIRzHXltPu1shi7fJeWIYhjBl3NqvbghvNvho8KrFkYez8yDDQj1kVJjOz+YA6t4lrn77RS2xw4+fRJgBy/LD9ILectaThysuFt84yKooSuFAv1AQKMeVXkpnFuzzBFtxyuRPtPUYXftSvEm/9BapFHGEVCuT7RvAQJBAP9yq18VFhPQAfngld9n0NwmCO33kdbFYqVIWBNKZdvVZIqwIvnmTqsgQacrvWutsWauukKT7VzySkht/uE63j0CQQDRdjgqx4H7SfMjkaZK5nJ6ptuFgR19HkakOJZSIM78Ot3PzfHcnfYuCRjs8lIEWmhYqj2FE+BcZ9cejphGuTWhAkB0XimBXBq9ldGAonXD2whDcbQ5q8EtJKgmgUlWKFs0hQaTQ1/7lZYa0Mv3uq5EwlCBZXGGaNsFr351dl5Y/jdFAkA6D2DmSsL22rqwo1DK9jHJWbMDwJRh+CBwqNbSERIOzGprjZR7KLXycMcd9tVRK5Y87YN7/dR1CLuSVshS4kfBAkAW6ls9/RlBA6gOpDuq+Qn4CZUng3h7OJsDgzCY95RtuMISJNuVFcGC/XVKB+urkyfhR/H7I8HIPXQtNJenH9f2";

    private static final int SDK_PAY_FLAG = 1;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(PayActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(PayActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                default:
                    break;
            }
        };
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.alipay, R.id.weixin_pay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.alipay:
                zhifubaoPay();
                break;
            case R.id.weixin_pay:
                DxManager dxManager = new DxManager(this);

                File dexPathFile = new File(Environment.getExternalStorageDirectory(), "out.dex");
                Logger.d(dexPathFile.getAbsolutePath());
                dxManager.loadDex(dexPathFile);
                break;
        }
    }

    private void zhifubaoPay() {

        // 订单  ！！！！ 需要设置为自己的信息，在下面
        String orderInfo =
                AlipayHelper.getOrderInfo
                        ("测试的商品", // 商品名称
                                "该测试商品的详细描述", // 商品描述
                                "0.01","1111");// 商品价格

        Logger.d(orderInfo);
        /**
         * 特别注意，这里的签名逻辑需要放在服务端，切勿将私钥泄露在代码中！
         */
        String sign = AlipayHelper.sign(orderInfo);
        try {
            /**
             * 仅需对sign 做URL编码
             */
            sign = URLEncoder.encode(sign, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        /**
         * 完整的符合支付宝参数规范的订单信息
         * 最终，支付宝使用 payInfo 来进行支付的操作
         */
        final String payInfo = orderInfo + "&sign=\"" + sign + "\"&" + AlipayHelper.getSignType();

//        /**   新的支付
//         * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
//         * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
//         * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
//         *
//         * orderInfo的获取必须来自服务端；
//         */
//        boolean rsa2 = (RSA2_PRIVATE.length() > 0);
//        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, rsa2);
//        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);
//
//        String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
//        String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
//        final String orderInfo = orderParam + "&" + sign;

        // 支付宝支付
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(PayActivity.this);
                String result = alipay.pay(payInfo, true);
//                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("msp", result.toString());

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
        }

}
