package com.mihua.frameproject.socket.socketbroadcast;

/**
 * <pre>
 *     author : wang
 *     e-mail : wm@tairunmh.com
 *     time   : 2018/04/19
 *     desc   :
 * </pre>
 */

import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 过滤用户输入只能为金额格式
 */
public class MoneyInputFilter implements InputFilter {
    Pattern mPattern;

    //输入的最大金额
    private double maxMoney = Integer.MAX_VALUE;
    //小数点后的位数
    private static final int POINTER_LENGTH = 2;

    private static final String POINTER = ".";

    private static final String ZERO = "0";

    public MoneyInputFilter(double maxMoney) {
        this.maxMoney = maxMoney;
        mPattern = Pattern.compile("([0-9]|\\.)*");
    }

    public MoneyInputFilter() {
        this(Integer.MAX_VALUE);
    }

    /**
     * @param source 新输入的字符串
     * @param start  新输入的字符串起始下标，一般为0
     * @param end    新输入的字符串终点下标，一般为source长度-1
     * @param dest   输入之前文本框内容
     * @param dstart 原内容起始坐标，一般为0
     * @param dend   原内容终点坐标，一般为dest长度-1
     * @return 输入内容
     */
    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        String sourceText = source.toString();
        String destText = dest.toString();


        if (destText.isEmpty()&&(TextUtils.equals(".",sourceText)||TextUtils.equals("00",sourceText))){
            return "";
        }

        if (destText.contains(POINTER)&&TextUtils.equals(".",source.toString())){
            return "";
        }

        //验证删除等按键
        if (TextUtils.isEmpty(sourceText)) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        builder.append(destText);
        builder.insert(dstart == dend ? dstart : dend, sourceText);
        double sumText = Double.parseDouble(builder.toString());

        Matcher matcher = mPattern.matcher(source);
        //已经输入小数点的情况下，只能输入数字
        if (destText.contains(POINTER)) {
            if (!matcher.matches()) {
                return "";
            } else {
                if (POINTER.equals(source.toString())) {  //只能输入一个小数点
                    return "";
                }
            }
            //验证小数点精度，保证小数点后只能输入两位
            String[] pointArr = builder.toString().split("\\.");
            int length = 0;
            if (pointArr.length > 1) {
                length = pointArr[1].length();
            } else {
                length = dend - destText.indexOf(POINTER);
            }
            if (length > POINTER_LENGTH) {
                return dest.subSequence(dstart, dend);
            }
        } else {
            /**
             * 没有输入小数点的情况下，只能输入小数点和数字
             * 1. 首位不能输入小数点
             * 2. 如果首位输入0，则接下来只能输入小数点了
             */
            if (!matcher.matches()) {
                return "";
            } else {
                if ((POINTER.equals(source.toString())) && TextUtils.isEmpty(destText)) {  //首位不能输入小数点
                    return "";
                } else if (!POINTER.equals(source.toString()) && ZERO.equals(destText)) { //如果首位输入0，接下来只能输入小数点
                    return "";
                }
            }
        }
        if (sumText > maxMoney) {
            return dest.subSequence(dstart, dend);
        }

        return dest.subSequence(dstart, dend) + sourceText;
    }
}