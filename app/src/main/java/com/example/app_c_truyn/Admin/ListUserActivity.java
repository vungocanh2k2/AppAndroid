package com.example.app_c_truyn.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.app_c_truyn.Adapter.AdapterUser;
import com.example.app_c_truyn.Database.DatabaseStory;
import com.example.app_c_truyn.Model.User;
import com.example.app_c_truyn.R;

import java.util.ArrayList;
import java.util.List;

public class ListUserActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<User> userList = new ArrayList<>();
    private AdapterUser userAdapter;
    private DatabaseStory databaseStory;
    private ImageButton btnAddUser;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);

        btnAddUser = findViewById(R.id.buttonAdd);

        recyclerView = findViewById(R.id.recyclerView);
        userAdapter = new AdapterUser(userList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(userAdapter);

        // Khởi tạo đối tượng DatabaseHelper
        databaseStory = new DatabaseStory(this);

        // Lấy danh sách người dùng từ cơ sở dữ liệu
        List<User> users = databaseStory.getAllUsers();

        // Thêm người dùng vào danh sách hiển thị trên RecyclerView
        userList.addAll(users);

        // Cập nhật giao diện
        userAdapter.notifyDataSetChanged();

        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListUserActivity.this,AddUserActivity.class);
                startActivity(intent);
            }
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onResume() {
        super.onResume();

        // Lấy danh sách người dùng từ cơ sở dữ liệu
        List<User> users = databaseStory.getAllUsers();

        // Xóa toàn bộ danh sách người dùng hiện tại
        userList.clear();

        // Thêm người dùng vào danh sách hiển thị trên RecyclerView
        userList.addAll(users);

        // Cập nhật giao diện
        userAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Giải phóng tài nguyên khi kết thúc activity
        databaseStory.close();
    }
}