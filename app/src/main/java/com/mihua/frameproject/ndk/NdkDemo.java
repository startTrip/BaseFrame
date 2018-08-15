package com.mihua.frameproject.ndk;

/**
 * <pre>
 *     author : wang
 *     e-mail : wm@tairunmh.com
 *     time   : 2018/04/08
 *     desc   :
 * </pre>
 */
public class NdkDemo {


    static {
        System.loadLibrary("libhello");

    }

    public static void main(String[] args) {

        System.out.printf(Hello.getStringFromC());

    }

}
