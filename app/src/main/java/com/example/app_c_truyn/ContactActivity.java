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
        Ax();

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
                intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                intent.putExtra(Intent.EXTRA_TEXT, "Body");
                try {
                    startActivity(Intent.createChooser(intent, "Send Email"));
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(ContactActivity.this, "No email app found.", Toast.LENGTH_SHORT).show();
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
                String address = "https://maps.app.goo.gl/obUW8sgyRBC1MjBh9"; // Địa chỉ cần hiển thị
                Uri uri = Uri.parse("geo:0,0?q=" + Uri.encode(address));
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        btnGit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gitUrl = "https://maps.app.goo.gl/obUW8sgyRBC1MjBh9"; // URL của trang Git
                Uri uri = Uri.parse(gitUrl);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

    }

    private void Ax() {
        btnEmail = findViewById(R.id.email);
        btnPhone = findViewById(R.id.phone);
        btnAddress = findViewById(R.id.address);
        btnGit = findViewById(R.id.git);
        backCT = findViewById(R.id.backCt);
    }
}