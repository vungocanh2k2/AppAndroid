package com.example.app_c_truyn.Admin.Story;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.app_c_truyn.Database.DatabaseStory;
import com.example.app_c_truyn.Model.Story;
import com.example.app_c_truyn.R;

public class AddStoryActivity extends AppCompatActivity {

    private EditText edtnameStory, edtContent, edtImage;
    private DatabaseStory db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_story);

        edtImage = findViewById(R.id.imageAddStory);
        edtnameStory = findViewById(R.id.nameAddStory);
        edtContent = findViewById(R.id.contentAddStory);
        Button btnAddStory = findViewById(R.id.btnAddStory);
        ImageView btnBack = findViewById(R.id.backAddStory);

        db = new DatabaseStory(this);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddStoryActivity.this, ListStoryActivity.class);
                startActivity(intent);
            }
        });

        btnAddStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameStory = edtnameStory.getText().toString();
                String Content = edtContent.getText().toString();
                String image = edtImage.getText().toString();

                Story story = CreateStory();

                if (nameStory.equals("") || Content.equals("") || image.equals("")) {
                    Toast.makeText(AddStoryActivity.this, "Vui lòng nhập đầy đủ thông tin.", Toast.LENGTH_SHORT).show();
                } else {
                    db.AddStory(story);
                    Toast.makeText(AddStoryActivity.this, "Thêm truyện thành công.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AddStoryActivity.this, ListStoryActivity.class));
                }
            }
        });

    }

    // Phương thức tạo truyện
    private Story CreateStory() {
        String nameStory = edtnameStory.getText().toString();
        String content = edtContent.getText().toString();
        String image = edtImage.getText().toString();

        Intent intent = getIntent();

        int id = intent.getIntExtra("Id", 0);

        return new Story(nameStory, content, image, id);
    }
}