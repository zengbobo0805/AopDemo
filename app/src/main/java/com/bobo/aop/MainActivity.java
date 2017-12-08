package com.bobo.aop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bobo.aop.annotation.SecurityCheckAnnotation;
import com.bobo.aop.model.Student;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private TextView tv_1;
    private Student stu;
    private int age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_1 = findViewById(R.id.tv_1);
        tv_1.setOnClickListener(this);
        checkPhoneState();


        stu = getStu();

        Log.e("MainActivity", "stu:" +( stu == null ? "" :
                "name:" + stu.getName() + "  ,age:" + stu.getAge()));


    }


    @Override
    public void onClick(View view) {
        initTvData();

//        stu.hurt();
//        stu.hurtThrows();
        int height =  stu.getHeight();
        Log.d(TAG,"1  height:"+height);
        height =  stu.getHeight(333);
        Log.d(TAG,"2   height:"+height);
    }

    private void initTvData() {
        tv_1.setText(DateUtils.formatDateTime(this, System.currentTimeMillis(), -1));
    }

    //为checkPhoneState使用SecurityCheckAnnotation注解，并指明调用该函数的人需要声明的权限
    @SecurityCheckAnnotation(declaredPermission = "android.permission.READ_PHONE_STATE")
    public void checkPhoneState() {

        Log.e(TAG, "Read Phone State succeed");
        return;
    }

    public Student getStu() {
        Student stu =new Student("11", 11);
        return stu;
    }

    public int getAge() {
        return age;
    }
}
