package com.mihua.frameproject.annotation;

/**
 * <pre>
 *     author : wang
 *     e-mail : wm@tairunmh.com
 *     time   : 2017/05/10
 *     desc   :
 * </pre>
 */
@Table(value = "boy")
@Subscribe(threadMode = ThreadMode.BackGround)
public class Boy extends Person {

    @Table(getAge = 18, value = "age")
    public int age;

    @Table(isMan = true, value = "gender")
    private boolean isMan;

    @Table(value = "无参",isPublic = true)
    public void getName() {

    }

    @Table(value = "一个参",isPublic = true)
    public void getName(String name) {

    }

    @Table(value = "两个参",isPublic = true)
    public void getName(String name, boolean b) {

    }

    @Table(value = "无参",isPublic = false)
    private void getAge() {

    }

    @Table(value = "一个参",isPublic = false)
    private void getAge(int age) {

    }

    @Table(value = "两个参",isPublic = false)
    private void getAge(int age, boolean b) {

    }

    public class ViewHolder{


    }

    private class ViewHolder1{

    }
}
