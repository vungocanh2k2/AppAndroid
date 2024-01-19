package com.example.app_c_truyn;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.app_c_truyn.Login_Register.LoginActivity;

import java.util.Locale;
import java.util.Set;

public class SettingActivity extends AppCompatActivity {

    TextView txtLang, txtColor;
    ImageButton BtEX;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_setting);
        ax();
        BtEX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        txtLang.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                // Thực hiện chuyển đổi ngôn ngữ
                changeLanguage();
            }
           });
    }

    private void ax() {
        BtEX = findViewById(R.id.exitSetting);
        txtLang = findViewById(R.id.language);
    }

    private void changeLanguage() {
        final String[] listItem = {"English", "Tiếng Việt"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(SettingActivity.this);

        //tiêu đề
        mBuilder.setTitle("Chọn ngôn ngữ");

        mBuilder.setSingleChoiceItems(listItem, -1, new DialogInterface.OnClickListener() {
            //-1 chưa đối tượng nào được chọn
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

        Locale.setDefault(locale);//Đặt ngôn ngữ mặc định của ứng dụng thành locale đã tạo.
        Configuration config = new Configuration();
        config.locale = locale;//Đặt ngôn ngữ của config thành locale đã tạo.
        //Cập nhật cấu hình ngôn ngữ của ứng dụng với config đã được thiết lập.
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
        //tạo 1 đối tượng  để chỉnh sửa cài đặt được lưu trong tệp "Settings".
        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My_Lang", lang);//Đặt giá trị của khóa "My_Lang" trong tệp cài đặt thành lang.
        editor.apply();//Áp dụng các thay đổi đã chỉnh sửa vào tệp cài đặt.
    }

    public void loadLocale() {

        //cho phép lấy giá trị cài đặt từ tệp SharedPreferences.
        SharedPreferences preferences = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        //Lấy giá trị của khóa "My_Lang" từ SharedPreferences.
        String language = preferences.getString("My_Lang", "");
        setLocale(language);
    }
}