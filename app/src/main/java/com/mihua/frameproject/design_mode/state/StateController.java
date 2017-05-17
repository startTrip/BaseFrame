package com.mihua.frameproject.design_mode.state;

/**
 * <pre>
 *     author : wang
 *     e-mail : wm@tairunmh.com
 *     time   : 2017/05/17
 *     desc   : 状态控制类，里面包含一些状态
 * </pre>
 */
public interface StateController {

    // 开机状态
     void powerOn();
    // 关机状态
     void powerOff();

}
