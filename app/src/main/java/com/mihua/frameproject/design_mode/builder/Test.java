package com.mihua.frameproject.design_mode.builder;

/**
 * <pre>
 *     author : wang
 *     e-mail : wm@tairunmh.com
 *     time   : 2017/05/24
 *     desc   :
 * </pre>
 */
public class Test {

    public static void main(String[] args) {

        Person person = new Person.Builder("wang")
                .setAge("12")
                .setSex("男")
                .builder();
        System.out.println(person.toString());
    }
}
