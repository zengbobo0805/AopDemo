package com.bobo.aop.aspect;

import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * Created by bobozeng on 2017/12/8.
 */

@Aspect
public class StaticInitializationAspect {
    private static final String TAG = "StaticAspect";

    @Before("staticinitialization(com.bobo.aop.model.Student)")
    public void beforeStaticBlock(JoinPoint joinPoint) {
        Log.d(TAG, "beforeStaticBlock: ");
    }
}

