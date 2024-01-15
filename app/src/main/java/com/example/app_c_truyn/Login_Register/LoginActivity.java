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

    // SharedPreferences for storing login credentials
    private static final String PREF_NAME = "LoginPrefs";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_REMEMBER = "remember";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_login);



        ImageButton buttonSetting = findViewById(R.id.thaydoingonngu);
        buttonSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Thực hiện chuyển đổi ngôn ngữ
                changeLanguage();
            }
        });
        AnhXa();

        // Initialize SharedPreferences
        SharedPreferences loginPrefs = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        boolean remember = loginPrefs.getBoolean(KEY_REMEMBER, false);

        // If "Remember Me" is checked, populate the username and password fields
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

                Cursor cursor = db.getData();
                boolean isLoginSuccessful = false;

                while (cursor.moveToNext()) {
                    String datausername = cursor.getString(1);
                    String datapassword = cursor.getString(2);

                    if (datausername.equals(userName) && datapassword.equals(passWord)) {
                        isLoginSuccessful = true;

                        // Save login credentials to SharedPreferences if "Remember Me" is checked
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

    private void changeLanguage() {
        final String[] listItem = {"English", "Tiếng Việt"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(LoginActivity.this);
        mBuilder.setTitle("Chọn ngôn ngữ");
        mBuilder.setSingleChoiceItems(listItem, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0) {
                    setLocale("en");
                } else if (i == 1) {
                    setLocale("vi");
                }
                dialogInterface.dismiss();

                // Tạo lại SettingActivity và các Activity khác
                recreate();
            }
        });
        AlertDialog dialog = mBuilder.create();
        dialog.show();
    }

    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());

        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My_Lang", lang);
        editor.apply();
    }

    public void loadLocale() {
        SharedPreferences preferences = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = preferences.getString("My_Lang", "");
        setLocale(language);
    }
    private void AnhXa() {
        edtMatKhau = findViewById(R.id.matkhau);
        edtTaiKhoan = findViewById(R.id.taikhoan);
        btnDangKy = findViewById(R.id.dangky);
        btnDangNhap = findViewById(R.id.dangnhap);
        rememberCheckbox = findViewById(R.id.remember_checkbox);
    }
}
