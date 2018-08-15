package com.mihua.frameproject.design_mode.observe;

/**
 * <pre>
 *     author : wang
 *     e-mail : wm@tairunmh.com
 *     time   : 2017/05/24
 *     desc   :
 * </pre>
 */
public class Test {

    public static void main(String[] args) {

        PublishInfo publishInfo = new PublishInfo();

        Coder coder1 = new Coder("观察者1");
        Coder coder2 = new Coder("观察者2");
        Coder coder3 = new Coder("观察者3");

        publishInfo.addObserver(coder1);
        publishInfo.addObserver(coder2);
        publishInfo.addObserver(coder3);

        publishInfo.publishInformation("发送给观察者的消息");

        publishInfo.deleteObserver(coder1);
        publishInfo.publishInformation("再次发送给观察者的消息");
    }
}
