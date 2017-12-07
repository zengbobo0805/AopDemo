package com.bobo.aop.aspect.log;

import android.util.Log;

import com.bobo.aop.model.Student;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by bobozeng on 17/12/7.
 */

@Aspect
public class LogSetAspect {
//    private static final String TAG = "LogSetAspect";
//
//    private static final String POINTCUT_SET =
//            "set(Student*.stu)";
//
//    @Pointcut(POINTCUT_SET)
//    public void setPointcutSet() {
//    }
//
//    @After("setPointcutSet")
//    public void myset(Student stu) {
//        Log.e(TAG, "stu:" + stu == null ? "" :
//                "name:" + stu.getName() + "  ,age:" + stu.getAge());
//    }
}
