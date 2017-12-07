package com.bobo.aop.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bobo.aop.R;
import com.bobo.aop.annotation.SecurityCheckAnnotation;
import com.bobo.aop.model.Student;

public class MethodActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView tv_1;
    private Student stu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_1 = findViewById(R.id.tv_1);
        tv_1.setOnClickListener(this);
//        new Student();
       stu= new Student("11",11);
    }


    @Override
    public void onClick(View view) {
        initTvData();
    }

    private void initTvData(){
        tv_1.setText(DateUtils.formatDateTime(this,System.currentTimeMillis(),-1));
    }


}
