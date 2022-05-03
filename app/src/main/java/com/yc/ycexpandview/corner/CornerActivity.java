package com.yc.ycexpandview.corner;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.yc.roundcorner.view.CircleImageView;
import com.yc.roundcorner.view.RoundTextView;
import com.yc.ycexpandview.R;


public class CornerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corner_view);

        final RoundTextView tv = findViewById(R.id.tv);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setRadius(10, 0, 0, 0);
                tv.setStrokeWidthColor(5, getResources().getColor(android.R.color.holo_green_dark));
            }
        });

        final CircleImageView civ = findViewById(R.id.civ);
        civ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                civ.setSelected(!civ.isSelected());
            }
        });
    }
}