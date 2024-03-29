package com.example.app_c_truyn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class ContactActivity extends AppCompatActivity {

    Button btnEmail, btnPhone, btnAddress, btnGit;
    ImageButton backCT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        btnEmail = findViewById(R.id.email);
        btnPhone = findViewById(R.id.phone);
        btnAddress = findViewById(R.id.address);
        btnGit = findViewById(R.id.git);
        backCT = findViewById(R.id.backCt);

        backCT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Gửi email
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("message/rfc822");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"vehoang2k2@gmail.com"});
                intent.putExtra(Intent.EXTRA_SUBJECT, "Góp Ý Cho Chúng Tôi ");
                intent.putExtra(Intent.EXTRA_TEXT, "");
                try {
                    startActivity(Intent.createChooser(intent, "Send Email"));
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(ContactActivity.this, "Không tìm thấy ứng dụng nào.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = "0829214822"; // Số điện thoại của bạn

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + phoneNumber));
                startActivity(intent);
            }
        });

        btnAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gitUrl = "https://maps.app.goo.gl/4QG1LGNByjysMjac7";
                Uri uri = Uri.parse(gitUrl);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        btnGit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gitUrl = "https://github.com/";
                Uri uri = Uri.parse(gitUrl);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

    }




}