package com.example.app_c_truyn.Login_Register;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app_c_truyn.Database.DatabaseStory;
import com.example.app_c_truyn.MainActivity;
import com.example.app_c_truyn.R;
import com.example.app_c_truyn.SettingActivity;

import java.util.Locale;
import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    EditText edtTaiKhoan, edtMatKhau;
    Button btnDangNhap;
    TextView btnDangKy;
    CheckBox rememberCheckbox;
    DatabaseStory db;

    // SharedPreferences để lưu trữ thông tin đăng nhập
    private static final String PREF_NAME = "LoginPrefs";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_REMEMBER = "remember";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AnhXa();

        // Khởi tạo SharedPreferences
        SharedPreferences loginPrefs = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        boolean remember = loginPrefs.getBoolean(KEY_REMEMBER, false);

        //Nếu "Ghi nhớ " được chọn, hãy điền vào trường tên người dùng và mật khẩu
        if (remember) {
            String savedUsername = loginPrefs.getString(KEY_USERNAME, "");
            String savedPassword = loginPrefs.getString(KEY_PASSWORD, "");

            edtTaiKhoan.setText(savedUsername);
            edtMatKhau.setText(savedPassword);
            rememberCheckbox.setChecked(true);
        }

        db = new DatabaseStory(this);

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = edtTaiKhoan.getText().toString();
                String passWord = edtMatKhau.getText().toString();
               //
                Cursor cursor = db.getData();
                boolean isLoginSuccessful = false;

                while (cursor.moveToNext()) {
                    String dataUsername = cursor.getString(1);
                    String dataPassword = cursor.getString(2);

                    if (dataUsername.equals(userName) && dataPassword.equals(passWord)) {
                        isLoginSuccessful = true;

                        //// Lưu thông tin đăng nhập vào SharedPreferences nếu "Ghi nhớ " được chọn
                        if (rememberCheckbox.isChecked()) {
                            SharedPreferences.Editor editor = loginPrefs.edit();
                            editor.putString(KEY_USERNAME, userName);
                            editor.putString(KEY_PASSWORD, passWord);
                            editor.putBoolean(KEY_REMEMBER, true);
                            editor.apply();
                        }

                        // Rest of your login logic
                        int role = cursor.getInt(4);
                        int id = cursor.getInt(0);
                        String email = cursor.getString(3);
                        String username = cursor.getString(1);

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("role", role);
                        intent.putExtra("id", id);
                        intent.putExtra("email", email);
                        intent.putExtra("username", username);
                        startActivity(intent);

                        break;
                    }
                }

                if (isLoginSuccessful) {
                    Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                }

                cursor.moveToFirst();
                cursor.close();
            }
        });
    }


    private void AnhXa() {
        edtMatKhau = findViewById(R.id.matkhau);
        edtTaiKhoan = findViewById(R.id.taikhoan);
        btnDangKy = findViewById(R.id.dangky);
        btnDangNhap = findViewById(R.id.dangnhap);
        rememberCheckbox = findViewById(R.id.remember_checkbox);
    }
}
