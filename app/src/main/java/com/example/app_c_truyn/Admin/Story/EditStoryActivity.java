package com.example.app_c_truyn.Admin.Story;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app_c_truyn.Database.DatabaseStory;
import com.example.app_c_truyn.Model.Story;
import com.example.app_c_truyn.R;

public class EditStoryActivity extends AppCompatActivity {

    private EditText etTitle, etContent, etImage;
    private DatabaseStory databaseStory;
    private int storyId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_story);

        etTitle = findViewById(R.id.et_title);
        etContent = findViewById(R.id.et_content);
        etImage = findViewById(R.id.et_image);
        Button btnSave = findViewById(R.id.btn_save);

        databaseStory = new DatabaseStory(this);

        // Lấy thông tin truyện từ Intent
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            storyId = bundle.getInt("story_id");
            String title = bundle.getString("title");
            String content = bundle.getString("content");
            String image = bundle.getString("image");

            etTitle.setText(title);
            etContent.setText(content);
            etImage.setText(image);
        }

        btnSave.setOnClickListener(view -> {
            // Lấy thông tin từ EditText
            String nameStory = etTitle.getText().toString();
            String Content = etContent.getText().toString();
            String image = etImage.getText().toString();

            // Kiểm tra xem các trường dữ liệu có rỗng hay không
            if (nameStory.isEmpty() || Content.isEmpty() || image.isEmpty()) {
                Toast.makeText(EditStoryActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            } else {
                // Tạo một đối tượng Story mới
                int id = 0;
                Story updatedStory = new Story(nameStory, Content, image, id);
                updatedStory.setID(storyId);
                updatedStory.setNameStory(nameStory);
                updatedStory.setContent(Content);
                updatedStory.setImage(image);

                // Cập nhật truyện trong cơ sở dữ liệu
                databaseStory.updateStory(updatedStory);

                Toast.makeText(EditStoryActivity.this, "Cập nhật truyện thành công", Toast.LENGTH_SHORT).show();
                finish(); // Kết thúc activity và quay trở lại màn hình trước đó
            }
        });
    }
}