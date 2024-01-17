package com.example.app_c_truyn;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.app_c_truyn.Database.DatabaseStory;
import com.example.app_c_truyn.Model.User;

public class ProfileActivity extends AppCompatActivity {

    private EditText edtUsername,edtPassword,edtEmail;
    private User user;
    private DatabaseStory db;

    @SuppressLint({"MissingInflatedId", "LocalSuppress"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        edtUsername = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password);
        edtEmail = findViewById(R.id.edt_email);
        Button btnSaveChanges = findViewById(R.id.btn_save);
        ImageButton btnCancel = findViewById(R.id.back_EditUser);

        db = new DatabaseStory(this);

        // Lấy thông tin người dùng từ activity trước đó
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int userId = extras.getInt("Id");
            user = db.getUserById(userId);

            edtUsername.setText(user.getUserName());
            edtPassword.setText(user.getPassWord());
            edtEmail.setText(user.getEmail());
        }

        btnSaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lấy thông tin mới từ EditText
                String newUsername = edtUsername.getText().toString().trim();
                String newPassword = edtPassword.getText().toString().trim();
                String newEmail = edtEmail.getText().toString().trim();
                String newImage = edtEmail.getText().toString().trim();

                // Kiểm tra tài khoản, mật khẩu và email có đúng định dạng không
                if (newUsername.length() < 6) {
                    Toast.makeText(ProfileActivity.this, "Tài khoản phải có ít nhất 6 ký tự", Toast.LENGTH_LONG).show();
                } else if (newPassword.length() < 6) {
                    Toast.makeText(ProfileActivity.this, "Mật khẩu phải có ít nhất 6 ký tự", Toast.LENGTH_LONG).show();
                } else if (!newUsername.matches("[a-zA-Z0-9]+")) {
                    Toast.makeText(ProfileActivity.this, "Tài khoản chỉ được chứa các ký tự chữ cái và số", Toast.LENGTH_LONG).show();
                } else if (!newPassword.matches("[a-zA-Z0-9]+")) {
                    Toast.makeText(ProfileActivity.this, "Mật khẩu chỉ được chứa các ký tự chữ cái và số", Toast.LENGTH_LONG).show();
                } else if (!newEmail.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")) {
                    Toast.makeText(ProfileActivity.this, "Email không hợp lệ", Toast.LENGTH_LONG).show();
                } else {
                    // Cập nhật thông tin người dùng trong cơ sở dữ liệu
                    user.setUserName(newUsername);
                    user.setPassWord(newPassword);
                    user.setEmail(newEmail);
                    user.setEmail(newImage);
                    db.updateUser(user);

                    // Hiển thị thông báo thành công
                    Toast.makeText(getApplicationContext(),
                            "Thông tin người dùng được cập nhật thành công", Toast.LENGTH_LONG).show();
                    // Kết thúc activity
                    finish();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Kết thúc activity mà không lưu lại thông tin
                finish();
            }
        });
    }
}