package com.example.app_c_truyn;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

public class InformationActivity extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        TextView txtInfo = findViewById(R.id.txtInfo);
        String info = "Hoàng Đình Dũng \nLớp: 62PM2 \nMã sinh viên: 2051063940";

        txtInfo.setText(info);
    }
}