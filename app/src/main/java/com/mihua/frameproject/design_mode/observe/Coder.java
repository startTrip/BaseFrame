package com.mihua.frameproject.design_mode.observe;

import java.util.Observable;
import java.util.Observer;

/**
 * <pre>
 *     author : wang
 *     e-mail : wm@tairunmh.com
 *     time   : 2017/05/24
 *     desc   : 观察者对象
 * </pre>
 */
public class Coder implements Observer{

    String name;

    public Coder(String name) {
        this.name = name;
    }

    @Override
    public void update(Observable o, Object arg) {

        System.out.println("被观察者"+o.getClass().getName()+",观察者"+name+",收到的的消息:"+arg.toString());
    }
}
