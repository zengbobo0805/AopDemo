package com.bobo.aop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bobo.aop.annotation.SecurityCheckAnnotation;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static  final  String TAG ="MainActivity";
    private TextView tv_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_1 = findViewById(R.id.tv_1);
        tv_1.setOnClickListener(this);
        checkPhoneState();
    }


    @Override
    public void onClick(View view) {
        initTvData();
    }

    private void initTvData(){
        tv_1.setText(DateUtils.formatDateTime(this,System.currentTimeMillis(),-1));
    }

    //为checkPhoneState使用SecurityCheckAnnotation注解，并指明调用该函数的人需要声明的权限
    @SecurityCheckAnnotation(declaredPermission="android.permission.READ_PHONE_STATE")
    public void checkPhoneState(){
        Log.e(TAG,"Read Phone State succeed");
        return;
    }
}
