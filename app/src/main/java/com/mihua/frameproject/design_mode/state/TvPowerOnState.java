package com.mihua.frameproject.design_mode.state;

/**
 * <pre>
 *     author : wang
 *     e-mail : wm@tairunmh.com
 *     time   : 2017/05/17
 *     desc   : 具体的状态拥有 开机的行为
 * </pre>
 */
public class TvPowerOnState implements TvState{

    @Override
    public void preChannel() {

        System.out.println("切换到上一个频道");
    }

    @Override
    public void nextChannel() {
        System.out.println("切换到下一个频道");
    }

    @Override
    public void upSound() {
        System.out.println("调高音量");
    }

    @Override
    public void downSound() {
        System.out.println("调低音量");
    }

}
