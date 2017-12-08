package com.bobo.aop.aspect;

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * Created by bobozeng on 2017/12/8.
 */

@Aspect
public class AdivceAspect {
    private static final String TAG = "AdivceAspect";

    /**
     * 这里讲的正常结束，指的是有返回值的方法
     * <p>
     * 我们想要拿到 getHeight 的返回值，做一些其他事情（比如，数据统计、缓存等），可以这样做：
     * <p>
     * 如果你只对getHeight(int sex)感兴趣，有两种做法：
     * 1、Pointcut 中表示任意参数的 .. 改为 int
     *
     * @param height
     * @AfterReturning(pointcut = "execution(* android.aspectjdemo.animal.Animal.getHeight(int))", returning = "height")
     * 2、 && args(int)
     * @AfterReturning(pointcut = "execution(* android.aspectjdemo.animal.Animal.getHeight(..)) && args(int)", returning = "height")
     */
    @AfterReturning(pointcut = "execution(* com.bobo.aop.model.Student.getHeight(..))", returning = "height")
    public void getHeight(int height) {
        Log.d(TAG, "getHeight: " + height);
    }

    /**
     * ProceedingJoinPoint 方法；
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("execution(* com.bobo.aop.model.Student.getHeight(int))")
    public int getHeight(ProceedingJoinPoint joinPoint) throws  Throwable{
      int obj = (int) joinPoint.proceed();
        Log.d(TAG, "getArgs: " + joinPoint.getArgs()[0]);
        Log.d(TAG, "getSignature: " + joinPoint.getSignature());
        Log.d(TAG, "getTarget: " + joinPoint.getTarget());
        Log.d(TAG, "getThis: " + joinPoint.getThis());
        Log.d(TAG, "getKind: " + joinPoint.getKind());
        Log.d(TAG, "getSourceLocation: " + joinPoint.getSourceLocation());

        return 200;
    }
}
