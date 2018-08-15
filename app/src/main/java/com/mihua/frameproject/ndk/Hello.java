package com.mihua.frameproject.ndk;

/**
 * <pre>
 *     author : wang
 *     e-mail : wm@tairunmh.com
 *     time   : 2018/05/24
 *     desc   :
 * </pre>
 */
public class Hello {

    public native static String getStringFromC();

    static {
        System.loadLibrary("hello");
    }

    public static void main(String[] args) {

        System.out.printf(getStringFromC());

    }

}
