package com.example.app_c_truyn;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app_c_truyn.Database.DatabaseStory;
import com.example.app_c_truyn.Model.Story;

public class ContentActivity extends AppCompatActivity {

    TextView txtName,txtContent;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        //Ánh xạ
        txtContent = findViewById(R.id.noidung);
        txtName = findViewById(R.id.TenTruyen);

        ImageButton backBtn = findViewById(R.id.backContent);
        ImageButton save = findViewById(R.id.saveContent);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the data
                Intent intent = getIntent();
                String nameStory = intent.getStringExtra("nameStory");
                String content = intent.getStringExtra("content");
                String image = intent.getStringExtra("image");
                String userID = intent.getStringExtra("ID_User");

                // Create a Story object from the data
                Story favoriteStory = new Story();
                favoriteStory.setNameStory(nameStory);
                favoriteStory.setContent(content);
                favoriteStory.setImage(image);
                assert userID != null;
                favoriteStory.setID_TK(Integer.parseInt(userID));

                // Save the story to the favorite table
                DatabaseStory databaseStory = new DatabaseStory(ContentActivity.this);
                databaseStory.addFavoriteStory(favoriteStory);

                // Display a toast message to the user
                Toast.makeText(ContentActivity.this, "Đã lưu vào mục yêu thích", Toast.LENGTH_SHORT).show();
            }
        });


        //lay du lieu
        Intent intent = getIntent();
        String nameStory = intent.getStringExtra("nameStory");
        String content = intent.getStringExtra("content");

        txtName.setText(nameStory);
        txtContent.setText(content);

        // cho phep cuon noi dung truyen
        txtContent.setMovementMethod(new ScrollingMovementMethod());

    }
}