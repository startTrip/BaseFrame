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
 *     time   : 2017/05/10
 *     desc   :
 * </pre>
 */


@Target({ElementType.FIELD,ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.CLASS)
@Inherited
public @interface Table {

    String value();

    int getAge() default 18;

    boolean isMan() default true;

    boolean isPublic() default true;

}
