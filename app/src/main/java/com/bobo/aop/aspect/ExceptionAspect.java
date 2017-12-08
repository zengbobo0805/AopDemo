package com.bobo.aop.aspect;

import android.util.Log;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * Created by bobozeng on 2017/12/8.
 */


@Aspect
public class ExceptionAspect {
    private static final String TAG = "ExceptionAspect";

    /**
     * handler
     * 不支持@After、@Around
     * **************************************
     * 注意 handler 不支持 @After 与 @Around，且异常只支持编译时匹配，
     * 也就是handler(java.lang.Exception)无法匹配java.lang.ArithmeticException，
     * 虽然ArithmeticException继承自Exception。
     */
    @Before("handler(java.lang.ArithmeticException)")
    public void handler() {
        Log.e(TAG, "handler");
    }

    /**
     * call(* *..*(..))表示任意类的任意方法，被调用的 JPoint。
     * throwing = "throwable"描述了异常参数的名称，也就是anyFuncThrows方法中的参数throwable。
     * <p>
     * 你可以通过 Throwable 收集方法调用栈的信息，这里就不做过多讲解了。
     * <p>
     * 这里，需要强调几点：
     * 1、@AfterThrowing 不支持 Field -> get & set，一般用在 Method 和 Constructor，其他暂时没测试过；
     * 2、捕获的是抛出异常的方法，即使这个方法的调用方已经处理了此异常，比如：
     * <p>
     * @param throwable
     */
    @AfterThrowing(pointcut = "call(* *..*(..))", throwing = "throwable")
    public void anyFuncThrows(Throwable throwable) {
        Log.e(TAG, "hurtThrows: ", throwable);
    }

}
