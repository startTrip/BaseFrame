package com.mihua.frameproject.annotation;

/**
 * <pre>
 *     author : wang
 *     e-mail : wm@tairunmh.com
 *     time   : 2017/05/05
 *     desc   :
 * </pre>
 */

@Subscribe(threadMode = ThreadMode.Async,priority = 1,sticky = true,value = "你好")
public class Person {

    @Subscribe(threadMode = ThreadMode.BackGround, priority = 2, sticky = true , value = "hehe")
    String name;

    @Subscribe(threadMode = ThreadMode.Async, priority =2 , sticky =false , value ="方法" )
    void eat(){
        int i;
    }

    @Subscribe(threadMode = ThreadMode.Async, priority =2 , sticky =false , value ="有参方法" )
    void eat(int i1){
        int i;
    }
}
