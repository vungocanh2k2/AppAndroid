package com.example.app_c_truyn.Admin.User;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.app_c_truyn.Database.DatabaseStory;
import com.example.app_c_truyn.Model.User;
import com.example.app_c_truyn.R;

public class AddUserActivity extends AppCompatActivity {

    private EditText edtUsername;
    private EditText edtPassword;
    private EditText edtEmail;
    private DatabaseStory db;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        edtUsername = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password);
        edtEmail = findViewById(R.id.edt_email);
        Button btnSave = findViewById(R.id.btn_save);
        ImageButton btnCancel = findViewById(R.id.back_AddUser);

        db = new DatabaseStory(this);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lấy thông tin mới từ EditText
                String userName = edtUsername.getText().toString().trim();
                String passWord = edtPassword.getText().toString().trim();
                String email = edtEmail.getText().toString().trim();
                User user = CreateUser();

                if (userName.length() < 6) {
                    Toast.makeText(AddUserActivity.this, "Tài khoản phải có ít nhất 6 ký tự", Toast.LENGTH_LONG).show();
                } else if (passWord.length() < 6) {
                    Toast.makeText(AddUserActivity.this, "Mật khẩu phải có ít nhất 6 ký tự", Toast.LENGTH_LONG).show();
                } else if (!userName.matches("[a-zA-Z0-9]+")) {
                    Toast.makeText(AddUserActivity.this, "Tài khoản chỉ được chứa các ký tự chữ cái và số", Toast.LENGTH_LONG).show();
                } else if (!passWord.matches("[a-zA-Z0-9]+")) {
                    Toast.makeText(AddUserActivity.this, "Mật khẩu chỉ được chứa các ký tự chữ cái và số", Toast.LENGTH_LONG).show();
                } else if (!email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")) {
                    Toast.makeText(AddUserActivity.this, "Email không hợp lệ", Toast.LENGTH_LONG).show();
                } else if (db.CheckUser(userName, email)) {
                    Toast.makeText(AddUserActivity.this, "Tài khoản hoặc email đã tồn tại!", Toast.LENGTH_LONG).show();
                } else {
                    db.AddTaiKhoan(user);
                    Toast.makeText(AddUserActivity.this, "Thêm mới thành công", Toast.LENGTH_LONG).show();
                    finish();
                }

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddUserActivity.this,ListUserActivity.class);
                startActivity(intent);
            }
        });
    }

    private User CreateUser() {
        String userName = edtUsername.getText().toString();
        String passWord = edtPassword.getText().toString();
        String email = edtEmail.getText().toString();
        int role = 1;
        return new User(userName, passWord, email, role);
    }
}