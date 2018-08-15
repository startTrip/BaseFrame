package com.mihua.frameproject.design_mode.state;

/**
 * <pre>
 *     author : wang
 *     e-mail : wm@tairunmh.com
 *     time   : 2017/05/17
 *     desc   :  具体的电视机控制
 * </pre>
 */
public class TvStateController implements StateController {

    public TvState mTvState;

    public void setTvState(TvState tvState) {
        mTvState = tvState;
    }

    // 设置开的状态
    @Override
    public void powerOn() {
        setTvState(new TvPowerOnState());
        System.out.println("已经开机了");
    }

    // 设置关的状态
    @Override
    public void powerOff() {
        setTvState(new TvPowerOffState());
        System.out.println("已经关机了");
    }

    public void preChannel() {
        mTvState.preChannel();
    }

    public void nextChannel() {
        mTvState.nextChannel();
    }

    public void upSound() {
        mTvState.upSound();
    }

    public void downSound() {
        mTvState.downSound();
    }
}
