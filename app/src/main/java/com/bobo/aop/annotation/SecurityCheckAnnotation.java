package com.bobo.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by bobozeng on 17/12/7.
 */

//第一个@Target表示这个注解只能给函数使用
//第二个@Retention表示注解内容需要包含的Class字节码里，属于运行时需要的。
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SecurityCheckAnnotation {//@interface用于定义一个注解。

     String declaredPermission();  //declarePermssion是一个函数，其实代表了注解里的参数
}
