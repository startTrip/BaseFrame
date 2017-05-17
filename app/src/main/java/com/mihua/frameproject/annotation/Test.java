package com.mihua.frameproject.annotation;

import java.lang.reflect.Method;

/**
 * <pre>
 *     author : wang
 *     e-mail : wm@tairunmh.com
 *     time   : 2017/05/05
 *     desc   :
 * </pre>
 */
public class Test {


    public static void main(String[] args) throws ClassNotFoundException {

        Class<?> aClass = Class.forName("com.mihua.frameproject.annotation.Person");
        Subscribe annotation = aClass.getAnnotation(Subscribe.class);
        int priority = annotation.priority();
        boolean sticky = annotation.sticky();
        ThreadMode threadMode = annotation.threadMode();
        String value = annotation.value();
        System.out.println("类名"+aClass.getCanonicalName());
        System.out.print(priority+"\n");
        System.out.print(sticky+"\n");
        System.out.print(threadMode+"\n");
        System.out.print(value+"\n");
        Method[] declaredMethods = aClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            // 获取方法参数类型
            Class<?>[] parameterTypes = declaredMethod.getParameterTypes();
            System.out.print("方法名"+declaredMethod.getName()+"\n");
            for (Class<?> parameterType : parameterTypes) {
                System.out.println("paramenter"+parameterType.getCanonicalName());
            }
            Subscribe annotation1 = declaredMethod.getAnnotation(Subscribe.class);
            if (annotation1 == null) {
                continue;
            }
            int priority1 = annotation1.priority();
            boolean sticky1 = annotation1.sticky();
            ThreadMode threadMode1 = annotation1.threadMode();
            String value1 = annotation1.value();

            System.out.print(priority1+"\n");
            System.out.print(sticky1+"\n");
            System.out.print(threadMode1+"\n");
            System.out.print(value1+"\n");
        }


    }

}
