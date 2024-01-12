package com.example.app_c_truyn.Login_Register;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app_c_truyn.Database.DatabaseStory;
import com.example.app_c_truyn.Model.User;
import com.example.app_c_truyn.R;

public class RegisterActivity extends AppCompatActivity {

    EditText edtDKTaiKhoan, edtDKMatKhau, edtDKEmail;
    Button btnDKDangKy, btnDKDangNhap;
    DatabaseStory db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DatabaseStory(this);

        AnhXa();

        btnDKDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = edtDKTaiKhoan.getText().toString();
                String passWord = edtDKMatKhau.getText().toString();
                String email = edtDKEmail.getText().toString();
                User user = CreateUser();

                if (userName.length() < 6) {
                    Toast.makeText(RegisterActivity.this, "Tài khoản phải có ít nhất 6 ký tự", Toast.LENGTH_LONG).show();
                } else if (passWord.length() < 6) {
                    Toast.makeText(RegisterActivity.this, "Mật khẩu phải có ít nhất 6 ký tự", Toast.LENGTH_LONG).show();
                } else if (!userName.matches("[a-zA-Z0-9]+")) {
                    Toast.makeText(RegisterActivity.this, "Tài khoản chỉ được chứa các ký tự chữ cái và số", Toast.LENGTH_LONG).show();
                } else if (!passWord.matches("[a-zA-Z0-9]+")) {
                    Toast.makeText(RegisterActivity.this, "Mật khẩu chỉ được chứa các ký tự chữ cái và số", Toast.LENGTH_LONG).show();
                } else if (!email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")) {
                    Toast.makeText(RegisterActivity.this, "Email không hợp lệ", Toast.LENGTH_LONG).show();
                } else if (db.CheckUser(userName, email)) {
                    Toast.makeText(RegisterActivity.this, "Tài khoản hoặc email đã tồn tại!", Toast.LENGTH_LONG).show();
                } else {
                    db.AddTaiKhoan(user);
                    Toast.makeText(RegisterActivity.this, "Đăng kí thành công", Toast.LENGTH_LONG).show();
                }

            }
        });
        btnDKDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }


    private User CreateUser() {
        String userName = edtDKTaiKhoan.getText().toString();
        String passWord = edtDKMatKhau.getText().toString();
        String email = edtDKEmail.getText().toString();
        int phanquyen = 1;

        User user = new User();
        return user;
    }

    private void AnhXa() {
        edtDKEmail = findViewById(R.id.dkemail);
        edtDKTaiKhoan = findViewById(R.id.dktaikhoan);
        edtDKMatKhau = findViewById(R.id.dkmatkhau);
        btnDKDangKy = findViewById(R.id.dkdangky);
        btnDKDangNhap = findViewById(R.id.dkdangnhap);
    }
}