package com.bobo.aop.model;

import android.util.Log;

import com.bobo.aop.annotation.LogTrace;

/**
 * Created by bobozeng on 17/12/7.
 */

public class Student {
    private static final String TAG = "Student";
    private String name;
    private int age;

    static {
        Log.e(TAG, "static block");
    }

    @LogTrace
    public Student() {
        name = "name";
        age = 999;
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    /*******************九、Advice 之 @AfterThrowing**************************/
    public void hurt() {
        try {
            int i = 4 / 0;
        } catch (ArithmeticException e) {
            e.printStackTrace();
        }
    }

    public void hurtThrows() {
        int i = 4 / 0;
    }

    /****************十、Advice 之 @AfterReturning & args *****************************/
    public int getHeight() {
        return 0;
    }

    public int getHeight(int sex) {
        switch (sex) {
            case 0:
                return 163;
            case 1:
                return 173;
        }
        return 173;
    }
    /*********************************************/

}
