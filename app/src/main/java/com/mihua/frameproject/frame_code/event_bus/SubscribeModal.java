package com.mihua.frameproject.frame_code.event_bus;

import com.mihua.frameproject.annotation.ThreadMode;

import java.lang.reflect.Method;

/**
 * <pre>
 *     author : wang
 *     e-mail : wm@tairunmh.com
 *     time   : 2017/06/01
 *     desc   :
 * </pre>
 */
public class SubscribeModal {

    public ThreadMode mThreadMode;

    public Method mMethod;

    public Class classType;

    public SubscribeModal(ThreadMode threadMode, Method method, Class classType) {
        mThreadMode = threadMode;
        mMethod = method;
        this.classType = classType;
    }

    public ThreadMode getThreadMode() {
        return mThreadMode;
    }

    public void setThreadMode(ThreadMode threadMode) {
        mThreadMode = threadMode;
    }

    public Method getMethod() {
        return mMethod;
    }

    public void setMethod(Method method) {
        mMethod = method;
    }

    public Class getClassType() {
        return classType;
    }

    public void setClassType(Class classType) {
        this.classType = classType;
    }
}
