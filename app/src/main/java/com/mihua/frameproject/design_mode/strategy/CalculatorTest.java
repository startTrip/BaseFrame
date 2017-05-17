package com.mihua.frameproject.design_mode.strategy;

/**
 * <pre>
 *     author : wang
 *     e-mail : wm@tairunmh.com
 *     time   : 2017/05/17
 *     desc   : 计算 类扮演 Context 的角色
 *
 *     策略模式表示一个问题可以通过几种不同的方式去实现。
 * </pre>
 */
public class CalculatorTest {

    public static void main(String[] args) {
        CalculatorTest calculator = new CalculatorTest();
        calculator.setStrategy(new BusStrategy());
        System.out.println(calculator.calculator(1));
        calculator.setStrategy(new SubwayStrategy());
        System.out.println(calculator.calculator(2));
    }

    public Strategy mStrategy;

    public void setStrategy(Strategy strategy) {
        mStrategy = strategy;
    }

    public String calculator(int i){
       return mStrategy.calculateMoney(i);
    }
}


