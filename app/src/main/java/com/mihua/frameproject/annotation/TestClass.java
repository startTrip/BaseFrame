package com.mihua.frameproject.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * <pre>
 *     author : wang
 *     e-mail : wm@tairunmh.com
 *     time   : 2017/09/26
 *     desc   :
 * </pre>
 */
public class TestClass {

    public static void main(String[] args) throws ClassNotFoundException {

        // 类
        Class<?> aClass = Class.forName("com.mihua.frameproject.annotation.Boy");

        // 强制转换该 Class 对象，以表示指定的 class 对象所表示的类的一个子类。
        Class<? extends Person> boy = aClass.asSubclass(Boy.class);

        // 根据类名得到对象
//        Person boy1 = boy.cast(boy);

        // 以 String 的形式返回（带包名）
        System.out.println("String形式类名"+boy.getName());
        // 返回所定义的底层类的规范化名称 （带包名）
        System.out.println("规范化类名"+boy.getCanonicalName());
        // 返回源代码中给出的底层类的简称
        System.out.println("源代码类名"+boy.getSimpleName());

        System.out.println("\r\n//注解------------------");

        // 返回此元素上存在的所有注释。
        Annotation[] annotations = aClass.getDeclaredAnnotations();
        System.out.println(boy.getSimpleName()+"类上面的注解:\r\n");
        for (int i = 0; i < annotations.length; i++) {
            Annotation annotation = annotations[i];
            Class<? extends Annotation> aClass1 = annotation.annotationType();
            String simpleName = aClass1.getSimpleName();
            System.out.println("\r\r注解名称"+simpleName+"\n");
        }

        //  如果存在该元素的指定类型的注释，则返回这些注释
        Table table = aClass.getAnnotation(Table.class);

        System.out.println("\r\n//内部类------------------");

        //  返回一个包含某些 Class 对象的数组，这些对象表示属于此 Class 对象所表示的类的成员的所有公共类和接口。
        Class<?>[] classes = aClass.getClasses();
        for (int i = 0; i < classes.length; i++) {
            Class<?> aClass1 = classes[i];
            System.out.println();
            System.out.println("公共的内部类"+aClass1.getSimpleName()+"\r\n");
        }

        Class<?>[] declaredClasses = aClass.getDeclaredClasses();
        for (int i = 0; i < declaredClasses.length; i++) {
            Class<?> aClass1 = declaredClasses[i];
            System.out.println("声明的内部类"+aClass1.getSimpleName()+"\r\n");
        }

        System.out.println("\r\n//方法------------------");
        // 得到所有的方法
        Method[] methods = aClass.getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {
            Method method = methods[i];
            System.out.println("第"+i+"个公共方法名"+method.getName()+"\r\n");
            Class<?>[] parameterTypes = method.getParameterTypes();
            for (int i1 = 0; i1 < parameterTypes.length; i1++) {
                Class<?> parameterType = parameterTypes[i1];
                String simpleName = parameterType.getSimpleName();
                System.out.println("方法的参数名"+simpleName+"\r\n");
            }
        }

        ArrayList<Person> list = new ArrayList<>();
        list.add(new Person());
    }

}
