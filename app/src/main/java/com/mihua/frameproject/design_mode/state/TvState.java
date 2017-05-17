package com.mihua.frameproject.design_mode.state;

/**
 * <pre>
 *     author : wang
 *     e-mail : wm@tairunmh.com
 *     time   : 2017/05/17
 *     desc   :  抽象接口类，包含 里面的抽象行为
 * </pre>
 */
public interface TvState {

    // 上一个台
    void preChannel();

    // 下一个台
    void nextChannel();

    // 调高音量
    void upSound();

    // 降低音量
    void downSound();
}
