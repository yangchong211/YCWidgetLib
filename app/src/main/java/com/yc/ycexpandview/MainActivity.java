package com.yc.ycexpandview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.yc.expandlib.LogUtils;
import com.yc.ycexpandview.pager.PagerActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LogUtils.setIsLog(true);
        initView();
        initListener();
    }

    private void initView() {
        tv1 = findViewById(R.id.tv_1);
        tv2 = findViewById(R.id.tv_2);
        tv3 = findViewById(R.id.tv_3);
        tv4 = findViewById(R.id.tv_4);
    }

    private void initListener() {
        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);
        tv4.setOnClickListener(this);
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
            default:
                break;
        }
    }
}
