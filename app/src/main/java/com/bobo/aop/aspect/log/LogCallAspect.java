package com.bobo.aop.aspect.log;

/**
 * Created by bobozeng on 17/12/7.
 */

import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect   //必须使用@AspectJ标注，这样class LogActivityLiftAspect aspect LogActivityLiftAspect 了
public class LogCallAspect {
    static final String TAG ="Call";

    /*
    @Pointcut：pointcut也变成了一个注解，这个注解是针对一个函数的，比如此处的logForActivity()
    其实它代表了这个pointcut的名字。如果是带参数的pointcut，则把参数类型和名字放到
    代表pointcut名字的logForActivity中，然后在@Pointcut注解中使用参数名。
    基本和以前一样，只是写起来比较奇特一点。后面我们会介绍带参数的例子
    */
    @Pointcut("call(* com.bobo.aop.activity.MethodActivity.on*(..)) ||"
            + "call(* com.bobo.aop.activity.MethodActivity.initTvData(..))")
    public void callMethod() {
    }


//    @Pointcut("call(* com.bobo.aop..*(..))")
//    public void logForCallMethod() {
//    }

    /**
     *  @Pointcut： 针对一个构造函数
     *
     *  此处要注意：
     *  1."call(*.new(..))" 构造函数没有返回值；
     *  2."call(@＊ *.new(..))"  @带注解的构造函数；
     */
    @Pointcut("call(*.new(..))")
    public void callConstructor() {
    }



    ;  //注意，这个函数必须要有实现，否则Java编译器会报错

    /*
    @Before：这就是Before的advice，对于after，after -returning，和after-throwing。对于的注解格式为
    @After，@AfterReturning，@AfterThrowing。Before后面跟的是pointcut名字，然后其代码块由一个函数来实现。比如此处的log。
    */
    @Before("callMethod()")
    public void callMethodToBefore(JoinPoint joinPoint) {
        //对于使用Annotation的AspectJ而言，JoinPoint就不能直接在代码里得到多了，而需要通过
        //参数传递进来。
        Log.e(TAG, joinPoint.toShortString());
    }

    @After("callMethod()")
    public void callMethodToAfter(JoinPoint joinPoint) {
        //对于使用Annotation的AspectJ而言，JoinPoint就不能直接在代码里得到多了，而需要通过
        //参数传递进来。
        Log.e(TAG, joinPoint.toShortString());
    }

    @Before("callConstructor()")
    public void callConstructor(JoinPoint joinPoint) {
        //对于使用Annotation的AspectJ而言，JoinPoint就不能直接在代码里得到多了，而需要通过
        //参数传递进来。
        Log.e(TAG, joinPoint.toShortString());
    }
}

