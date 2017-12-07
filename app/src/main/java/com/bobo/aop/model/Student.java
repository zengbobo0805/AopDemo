package com.bobo.aop.model;

import com.bobo.aop.annotation.LogTrace;

/**
 * Created by bobozeng on 17/12/7.
 */

public class Student {
    private String name;
    private int age;

    @LogTrace
    public Student() {
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
}
