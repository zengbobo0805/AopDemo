package com.bobo.aop.aspect;

import android.util.Log;

import com.bobo.aop.model.Student;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by bobozeng on 17/12/7.
 */

@Aspect
public class FieldAspect {
    private static final String TAG = "FieldAspect";

    private static final String POINTCUT_GET =
            "get(int com.bobo.aop.model.Student.age) || get(int com.bobo.aop.MainActivity.age)";

    @Around(POINTCUT_GET)
    public int aroundFieldGet(ProceedingJoinPoint joinPoint) throws Throwable {
        // 执行原代码
        Object obj = joinPoint.proceed();
        Log.e(TAG, "aroundFieldGet obj: " + obj);
        int age = Integer.parseInt(obj.toString());
        Log.e(TAG, "aroundFieldGet age: " + age);
        return 100;
    }

    @Around("get(com.bobo.aop.model.Student com.bobo.aop.MainActivity.stu)")
    public Student aroundFieldGetObject(ProceedingJoinPoint joinPoint) throws Throwable {
        // 执行原代码
        Object obj = null;
        obj = joinPoint.proceed();
        Student stu = (Student) obj;
        Log.e(TAG, "aroundFieldGetObject stu:" + stu == null ? "" :
                "name:" + stu.getName() + "  ,age:" + stu.getAge());
        return new Student("jointPoint", 222);

    }


    @Around("set(int com.bobo.aop.model.Student.age)  && !withincode(com.bobo.aop.model.*.new(..))")
    public void aroundFieldSet(ProceedingJoinPoint joinPoint) throws Throwable {
        Log.e(TAG, "aroundFieldSet around->" + joinPoint.getTarget().toString()
                + "#" + joinPoint.getSignature().getName());
    }

}
