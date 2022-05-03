package com.yc.ycexpandview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.yc.expandlib.ExpandLogUtils;
import com.yc.ycexpandview.pager.PagerActivity;
import com.yc.ycexpandview.red.RedViewActivity;
import com.yc.ycexpandview.shadow.first.FirstActivity;
import com.yc.ycexpandview.shadow.second.SecondActivity;
import com.yc.ycexpandview.shadow.second.ShadowViewDemo3Activity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private TextView tv5;
    private TextView tv6;
    private TextView tv7;
    private TextView tv8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ExpandLogUtils.setIsLog(true);
        initView();
        initListener();
    }

    private void initView() {
        tv1 = findViewById(R.id.tv_1);
        tv2 = findViewById(R.id.tv_2);
        tv3 = findViewById(R.id.tv_3);
        tv4 = findViewById(R.id.tv_4);
        tv5 = findViewById(R.id.tv_5);
        tv6 = findViewById(R.id.tv_6);
        tv7 = findViewById(R.id.tv_7);
        tv8 = findViewById(R.id.tv_8);
    }

    private void initListener() {
        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);
        tv4.setOnClickListener(this);
        tv5.setOnClickListener(this);
        tv6.setOnClickListener(this);
        tv7.setOnClickListener(this);
        tv8.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_1:
                startActivity(new Intent(this,TextExpandActivity.class));
                break;
            case R.id.tv_2:
                startActivity(new Intent(this,TextExpandActivity2.class));
                break;
            case R.id.tv_3:
                startActivity(new Intent(this,TextExpandActivity3.class));
                break;
            case R.id.tv_4:
                startActivity(new Intent(this, PagerActivity.class));
                break;
            case R.id.tv_5:
                startActivity(new Intent(this, RedViewActivity.class));
                break;
            case R.id.tv_6:
                startActivity(new Intent(this, FirstActivity.class));
                break;
            case R.id.tv_7:
                startActivity(new Intent(this, SecondActivity.class));
                break;
            case R.id.tv_8:
                break;
            default:
                break;
        }
    }
}
