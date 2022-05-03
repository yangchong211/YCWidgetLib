package com.yc.ycexpandview.shadow.second;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.yc.ycexpandview.R;


public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        findViewById(R.id.text_card).setOnClickListener(this);
        findViewById(R.id.text_shadow_view).setOnClickListener(this);
        findViewById(R.id.text_shadow_view3).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text_card:
                startActivity(new Intent(this, CardViewActivity.class));
                break;
            case R.id.text_shadow_view:
                startActivity(new Intent(this, ShadowViewActivity.class));
                break;
            case R.id.text_shadow_view3:
                startActivity(new Intent(this, ShadowViewDemo3Activity.class));
                break;
            default:
                break;
        }
    }
}
