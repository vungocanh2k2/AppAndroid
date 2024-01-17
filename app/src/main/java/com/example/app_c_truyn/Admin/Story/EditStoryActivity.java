package com.example.app_c_truyn.Admin.Story;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.app_c_truyn.Database.DatabaseStory;
import com.example.app_c_truyn.Model.Story;
import com.example.app_c_truyn.R;

public class EditStoryActivity extends AppCompatActivity {

    private EditText etTitle, etContent, etImage;
    private DatabaseStory db;
    private int storyId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_story);

        etTitle = findViewById(R.id.et_title);
        etContent = findViewById(R.id.et_content);
        etImage = findViewById(R.id.et_image);
        Button btnSave = findViewById(R.id.btn_save);
        ImageView imageView = findViewById(R.id.iv_back);

        db = new DatabaseStory(this);

        // Lấy thông tin truyện từ Intent
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            storyId = bundle.getInt("story_id", 0); // Use "story_id" instead of "Id"
            String title = bundle.getString("nameStory");
            String content = bundle.getString("content");
            String image = bundle.getString("image");

            etTitle.setText(title);
            etContent.setText(content);
            etImage.setText(image);
        }

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnSave.setOnClickListener(view -> {
            // Lấy thông tin từ EditText
            String nameStory = etTitle.getText().toString();
            String content = etContent.getText().toString();
            String image = etImage.getText().toString();

            // Kiểm tra xem các trường dữ liệu có rỗng hay không
            if (nameStory.isEmpty() || content.isEmpty() || image.isEmpty()) {
                Toast.makeText(EditStoryActivity.this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
            } else {
                // Tạo một đối tượng Story mới
                Story updatedStory = new Story(nameStory, content, image, 0);
                updatedStory.setID(storyId);
                updatedStory.setNameStory(nameStory);
                updatedStory.setContent(content);
                updatedStory.setImage(image);

                // Cập nhật truyện vào trong cơ sở dữ liệu
                if (db.updateStory(updatedStory)) {
                    Toast.makeText(EditStoryActivity.this, "Cập nhật truyện " + nameStory + " thành công", Toast.LENGTH_SHORT).show();
                    finish(); // Kết thúc activity và quay trở lại màn hình trước đó
                } else {
                    Toast.makeText(EditStoryActivity.this, "Cập nhật truyện" + nameStory + " không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
