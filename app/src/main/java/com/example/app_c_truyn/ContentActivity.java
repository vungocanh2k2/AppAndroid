package com.example.app_c_truyn;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class ContentActivity extends AppCompatActivity {

    TextView txtName,txtContent;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        //Ánh xạ
        txtContent = findViewById(R.id.noidung);
        txtName = findViewById(R.id.TenTruyen);

        ImageButton backBtn = findViewById(R.id.backContent);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //lay du lieu
        Intent intent = getIntent();
        String nameStory = intent.getStringExtra("tentruyen");
        String content = intent.getStringExtra("noidung");

        txtName.setText(nameStory);
        txtContent.setText(content);

        // cho phep cuon noi dung truyen
        txtContent.setMovementMethod(new ScrollingMovementMethod());

    }
}