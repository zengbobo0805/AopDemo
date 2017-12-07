package com.bobo.aop.aspect.log;

/**
 * Created by bobozeng on 17/12/7.
 */

import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect   //必须使用@AspectJ标注，这样class LogActivityLiftAspect aspect LogActivityLiftAspect 了
public class LogExecution2Aspect {
    static final String TAG = "Execution2";

    private static final String POINTCUT_METHOD =
            "execution(@com.bobo.aop.annotation.LogTrace * *(..))";

    private static final String POINTCUT_CONSTRUCTOR =
            "execution(@com.bobo.aop.annotation.LogTrace  *.new(..))";

    @Pointcut(POINTCUT_METHOD)
    public void callMethod2() {

    }

    @Around("callMethod2()")
    public Object weaveJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();

        Object result = joinPoint.proceed();
        Log.e(TAG, "className:" + className + "  ,methodName:" + methodName);

        return result;

    }


    @Before("callMethod2()")
    public void beforeTojoinPoint(JoinPoint joinPoint) {
        Log.e(TAG, "beforeTojoinPoint:");

    }
}

