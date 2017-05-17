package com.mihua.frameproject.andfix.fix;

import com.mihua.frameproject.andfix.Replace;

/**
 * <pre>
 *     author : wang
 *     e-mail : wm@tairunmh.com
 *     time   : 2017/05/11
 *     desc   :
 * </pre>
 */
public class Caculator {

    @Replace(methodName = "caculator",className = "com.mihua.frameproject.andfix.Caculator")
    public int caculator(){
        int i = 10;
        int j = 1;
        return i/j;
    }


}
