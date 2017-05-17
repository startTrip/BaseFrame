package com.mihua.frameproject.design_mode.strategy;

/**
 * <pre>
 *     author : wang
 *     e-mail : wm@tairunmh.com
 *     time   : 2017/05/17
 *     desc   :
 * </pre>
 */
public class BusStrategy implements Strategy{


    @Override
    public String calculateMoney(int distance) {
        return "计算坐公交用的钱";
    }
}
