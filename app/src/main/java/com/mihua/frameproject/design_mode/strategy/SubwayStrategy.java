package com.mihua.frameproject.design_mode.strategy;

/**
 * <pre>
 *     author : wang
 *     e-mail : wm@tairunmh.com
 *     time   : 2017/05/17
 *     desc   : 具体的策略 去实现具体的策略
 * </pre>
 */
public class SubwayStrategy implements Strategy{


    @Override
    public String calculateMoney(int distance) {

        return "计算坐地铁用的钱";
    }
}
