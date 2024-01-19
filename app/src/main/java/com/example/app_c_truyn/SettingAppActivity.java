package com.example.app_c_truyn;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class SettingAppActivity extends AppCompatActivity {

    LinearLayout layoutLang,layoutVer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_setting);

        layoutLang = findViewById(R.id.language);
        layoutVer = findViewById(R.id.version);
        ImageButton imageButton = findViewById(R.id.backSetting);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        layoutLang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Thực hiện chuyển đổi ngôn ngữ
                changeLanguage();
            }
        });
        layoutVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingAppActivity.this, VersionActivity.class));
            }
        });
    }

    private void changeLanguage() {
        final String[] listItem = {"English", "Tiếng Việt"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(SettingAppActivity.this);
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
}
