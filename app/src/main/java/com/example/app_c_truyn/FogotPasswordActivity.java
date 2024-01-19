package com.example.app_c_truyn;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class FogotPasswordActivity extends AppCompatActivity {
    ImageButton backFP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        AnhX();

        backFP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void AnhX() {
        backFP =findViewById(R.id.back_EditFP);
    }

}
