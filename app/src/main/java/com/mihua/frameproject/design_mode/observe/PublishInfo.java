package com.mihua.frameproject.design_mode.observe;

import java.util.Observable;

/**
 * <pre>
 *     author : wang
 *     e-mail : wm@tairunmh.com
 *     time   : 2017/05/24
 *     desc   : 被观察者，当改变的时候发送消息给订阅者
 * </pre>
 */
public class PublishInfo extends Observable{

    public  void publishInformation(Object o){
        // 设置改变
        setChanged();
        //通知所有的观察者
        notifyObservers(o);
    }

}
