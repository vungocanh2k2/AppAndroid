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
        String info = "Bản quyền thuộc Hoàng Đình Dũng - 62PM2 - 2051063940";
        txtInfo.setText(info);
    }
}