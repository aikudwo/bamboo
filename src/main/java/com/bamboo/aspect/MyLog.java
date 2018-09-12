package com.bamboo.aspect;

import java.lang.annotation.*;

/**
 * 定义一个自定义注解
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyLog {
    String value() default "";
}
