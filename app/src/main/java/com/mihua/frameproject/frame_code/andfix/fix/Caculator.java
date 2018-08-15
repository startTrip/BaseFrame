package com.mihua.frameproject.frame_code.andfix.fix;

import com.mihua.frameproject.frame_code.andfix.Replace;

/**
 * <pre>
 *     author : wang
 *     e-mail : wm@tairunmh.com
 *     time   : 2017/05/11
 *     desc   :
 * </pre>
 */
public class Caculator {

    @Replace(methodName = "caculator",className = "com.mihua.frameproject.frame_code.andfix.Caculator")
    public int caculator(){
        int i = 10;
        int j = 1;
        return i/j;
    }


}
