package com.mihua.frameproject.design_mode.state;

/**
 * <pre>
 *     author : wang
 *     e-mail : wm@tairunmh.com
 *     time   : 2017/05/17
 *     desc   :
 * </pre>
 */
public class StateTest {

    public static void main(String[] args) {
        TvStateController stateController = new TvStateController();
        // 先开机
        stateController.powerOn();
        stateController.upSound();
        stateController.nextChannel();

        // 关机
        stateController.powerOff();
        stateController.upSound();
        stateController.nextChannel();

    }


}
