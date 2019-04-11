package com.lj.rgreader.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.lj.rgreader.R;

public class ContentActivity extends AppCompatActivity {

    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        textView = findViewById(R.id.content);
        Intent intent = getIntent();
        String data = intent.getStringExtra("extra_data");
        textView.setText(data);
    }
}
