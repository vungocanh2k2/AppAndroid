package com.example.app_c_truyn.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app_c_truyn.Database.DatabaseStory;
import com.example.app_c_truyn.Model.Story;
import com.example.app_c_truyn.R;

public class AddStoryActivity extends AppCompatActivity {

    EditText edtTenTruyen,edtNoiDung,edtAnh;
    Button btnDangBai;
    DatabaseStory databaseStory;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_story);

        edtAnh = findViewById(R.id.dbimg);
        edtTenTruyen = findViewById(R.id.dbTentruyen);
        edtNoiDung = findViewById(R.id.dbnoidung);
        btnDangBai = findViewById(R.id.dbdangbai);

        databaseStory = new DatabaseStory(this);

        btnDangBai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameStory = edtTenTruyen.getText().toString();
                String Content = edtNoiDung.getText().toString();
                String image = edtAnh.getText().toString();

                Story story = CreateStory();

                if(nameStory.equals("") || Content.equals("")|| image.equals("")){
                    Toast.makeText(AddStoryActivity.this, "Yeu cau nhap day du", Toast.LENGTH_SHORT).show();
                }
                else {
                    databaseStory.AddStory(story);

                    Intent intent = new Intent(AddStoryActivity.this,AdminActivity.class);
                    startActivity(intent);

                }


            }
        });

    }

    // phuong thuc tao truyen
    private Story CreateStory(){
        String tentruyen = edtTenTruyen.getText().toString();
        String noidung = edtNoiDung.getText().toString();
        String img = edtAnh.getText().toString();

        Intent intent = getIntent();

        int id = intent.getIntExtra("Id",0);

        Story story = new Story(tentruyen,noidung,img,id);

        return story;

    }
}