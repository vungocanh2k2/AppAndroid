package com.example.app_c_truyn.Admin.Story;

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
import com.example.app_c_truyn.MainActivity;
import com.example.app_c_truyn.Model.Story;
import com.example.app_c_truyn.R;

public class AddStoryActivity extends AppCompatActivity {

    EditText edtTenTruyen,edtNoiDung,edtAnh;
    Button btnDangBai;
    DatabaseStory db;
    ImageButton btnBack;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_story);

        edtAnh = findViewById(R.id.dbimg);
        edtTenTruyen = findViewById(R.id.dbTentruyen);
        edtNoiDung = findViewById(R.id.dbnoidung);
        btnDangBai = findViewById(R.id.dbdangbai);
        btnBack = findViewById(R.id.backAddStory);

        db = new DatabaseStory(this);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddStoryActivity.this, ListStoryActivity.class);
                startActivity(intent);
            }
        });

        btnDangBai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameStory = edtTenTruyen.getText().toString();
                String Content = edtNoiDung.getText().toString();
                String image = edtAnh.getText().toString();

                Story story = CreateStory();

                if(nameStory.equals("") || Content.equals("")|| image.equals("")){
                    Toast.makeText(AddStoryActivity.this, "Vui lòng nhập đầy đủ thông tin.", Toast.LENGTH_SHORT).show();
                }
                else {
                    db.AddStory(story);
                    Toast.makeText(AddStoryActivity.this, "Thêm truyện thành công.",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddStoryActivity.this, ListStoryActivity.class);
                    startActivity(intent);

                }


            }
        });

    }

    // phuong thuc tao truyen
    private Story CreateStory(){
        String nameStory = edtTenTruyen.getText().toString();
        String Content = edtNoiDung.getText().toString();
        String image = edtAnh.getText().toString();

        Intent intent = getIntent();

        int id = intent.getIntExtra("Id",0);

        return new Story(nameStory,Content,image,id);
    }
}