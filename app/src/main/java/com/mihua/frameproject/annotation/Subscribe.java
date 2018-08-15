package com.mihua.frameproject.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <pre>
 *     author : wang
 *     e-mail : wm@tairunmh.com
 *     time   : 2017/05/05
 *     desc   :
 * </pre>
 */

// 作用目标
@Target({ElementType.TYPE,ElementType.FIELD,ElementType.METHOD})
// 什么时候有效
@Retention(RetentionPolicy.RUNTIME)
// 可以继承
@Inherited

public @interface Subscribe {

    ThreadMode threadMode() default ThreadMode.Main;

    int priority() default 0;

    boolean sticky() default false;

    String value() default "subscribe";
}
