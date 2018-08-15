package com.mihua.frameproject.pay.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

/**
 * Project: TRShop_v1.36
 * Author: wm
 * Data:   2016/10/12
 */
public class AlipayHelper {

    // 商户PID  必须以 2088 开头
    public static final String PARTNER = "2088111278561763";
    // 收款人，支付宝账号，账号是固定的，一个商户一个账号
    public static final String SELLER = "gaoyandingzhi@126.com";
    // 客户端使用的私钥，对于请求进行数字签名，传给服务器，服务器使用公钥进行验证
    public static final String RSA_PRIVATE = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBANEClM9ja39OuhbiFcPYG8nUt19TIGvnBjC2CGMV3BKY2pTolVuicMfM0yyxvwtewe7Wkk+06Zl8fjgIWZS8SsfOeznQZbJq236CbcFYIhDsorDllDwQ0Uk409WSjaOCDJamOjGeQjYqy3D7v+z+Z48ZvCOPleX2h415mHQeHWVdAgMBAAECgYB6FrHqOr7uTIRzHXltPu1shi7fJeWIYhjBl3NqvbghvNvho8KrFkYez8yDDQj1kVJjOz+YA6t4lrn77RS2xw4+fRJgBy/LD9ILectaThysuFt84yKooSuFAv1AQKMeVXkpnFuzzBFtxyuRPtPUYXftSvEm/9BapFHGEVCuT7RvAQJBAP9yq18VFhPQAfngld9n0NwmCO33kdbFYqVIWBNKZdvVZIqwIvnmTqsgQacrvWutsWauukKT7VzySkht/uE63j0CQQDRdjgqx4H7SfMjkaZK5nJ6ptuFgR19HkakOJZSIM78Ot3PzfHcnfYuCRjs8lIEWmhYqj2FE+BcZ9cejphGuTWhAkB0XimBXBq9ldGAonXD2whDcbQ5q8EtJKgmgUlWKFs0hQaTQ1/7lZYa0Mv3uq5EwlCBZXGGaNsFr351dl5Y/jdFAkA6D2DmSsL22rqwo1DK9jHJWbMDwJRh+CBwqNbSERIOzGprjZR7KLXycMcd9tVRK5Y87YN7/dR1CLuSVshS4kfBAkAW6ls9/RlBA6gOpDuq+Qn4CZUng3h7OJsDgzCY95RtuMISJNuVFcGC/XVKB+urkyfhR/H7I8HIPXQtNJenH9f2";

    // 支付宝公钥
    public static final String RSA_PUBLIC = "";

    /**
     * create the order info. 创建订单信息
     *
     */
    public static String getOrderInfo(String subject, String body, String price, String pay_sn) {

        // 签约合作者身份ID
        String orderInfo = "partner=" + "\"" + PARTNER + "\"";

        // 签约卖家支付宝账号
        orderInfo += "&seller_id=" + "\"" + SELLER + "\"";

        // 商户网站唯一订单号
        // TODO 这个订单号，是我们自己服务器提供的；
        orderInfo += "&out_trade_no=" + "\"" + pay_sn + "\"";

        // 商品名称
        orderInfo += "&subject=" + "\"" + subject + "\"";

        // 商品详情
        orderInfo += "&body=" + "\"" + body + "\"";

        // 商品金额
        orderInfo += "&total_fee=" + "\"" + price + "\"";

        // 服务器异步通知页面路径
        // 当支付宝支付成功，支付宝服务器会自动调用这个网址告诉我们的商城支付是否成功
        orderInfo += "&notify_url=" + "\""+"?act=wnj_payment&op=notify_url" + "\"";

        // 服务接口名称， 固定值
        orderInfo += "&service=\"mobile.securitypay.pay\"";

        // 支付类型， 固定值
        orderInfo += "&payment_type=\"1\"";

        // 参数编码， 固定值
        orderInfo += "&_input_charset=\"utf-8\"";

        // 设置未付款交易的超时时间
        // 默认30分钟，一旦超时，该笔交易就会自动被关闭。
        // 取值范围：1m～15d。
        // m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
        // 该参数数值不接受小数点，如1.5h，可转换为90m。
        orderInfo += "&it_b_pay=\"30m\"";

        // extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
        // orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

        // 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
        orderInfo += "&return_url=\"m.alipay.com\"";

        return orderInfo;
    }

    /**
     * get the out_trade_no for an order. 生成商户订单号，该值在商户端应保持唯一（可自定义格式规范）
     *
     */
    private String getOutTradeNo() {
        SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss", Locale.getDefault());
        Date date = new Date();
        String key = format.format(date);

        Random r = new Random();
        key = key + r.nextInt();
        key = key.substring(0, 15);
        return key;
    }

    /**
     * sign the order info. 对订单信息进行签名
     *
     * @param content
     *            待签名订单信息
     */
    public static String sign(String content) {
        return SignUtil.sign(content, RSA_PRIVATE);
    }

    /**
     * get the sign type we use. 获取签名方式
     *
     */
    public static String getSignType() {
        return "sign_type=\"RSA\"";
    }
}
