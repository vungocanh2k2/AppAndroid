package com.example.app_c_truyn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app_c_truyn.Adapter.AdapterStory;
import com.example.app_c_truyn.Database.DatabaseStory;
import com.example.app_c_truyn.Model.Story;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    ListView listView;
    EditText edt;
    ImageButton back;
    ArrayList<Story> storyArrayList;
    ArrayList<Story> arrayList;
    AdapterStory adapterStory;
    DatabaseStory db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        listView = findViewById(R.id.lvSearch);
        edt = findViewById(R.id.search);
        back = findViewById(R.id.backSearch);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Danh sách truyện hiện có trong csdl
        initList();

        // Bắt sự kiện khi nhấp vào một item trong danh sách
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SearchActivity.this, ContentActivity.class);
                String nameStory = arrayList.get(position).getNameStory();
                String content = arrayList.get(position).getContent();
                intent.putExtra("nameStory", nameStory);
                intent.putExtra("content", content);
                startActivity(intent);
            }
        });

        // Bắt sự kiện khi thay đổi nội dung của EditText tìm kiếm
        edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });
    }

    // Tìm kiếm và lọc danh sách
    private void filter(String text) {
        ArrayList<Story> filteredList = new ArrayList<>();

        for (Story item : storyArrayList) {
            if (item.getNameStory() != null && item.getNameStory().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        // Kiểm tra nếu danh sách rỗng
        if (filteredList.isEmpty()) {
            Toast.makeText(SearchActivity.this, "Không tìm thấy kết quả", Toast.LENGTH_SHORT).show();
        }

        // Cập nhật danh sách hiển thị trong Adapter
        adapterStory.filterList(filteredList);
    }

    // Khởi tạo danh sách và gán vào ListView
    private void initList() {
        storyArrayList = new ArrayList<>();
        arrayList = new ArrayList<>();

        db = new DatabaseStory(this);
        Cursor cursor = db.getAllStory();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String nameStory = cursor.getString(1);
            String content = cursor.getString(2);
            String image = cursor.getString(3);
            int id_tk = cursor.getInt(4);

            storyArrayList.add(new Story(id, nameStory, content, image, id_tk));
            arrayList.add(new Story(id, nameStory, content, image, id_tk));
        }

        cursor.close();

        adapterStory = new AdapterStory(getApplicationContext(), storyArrayList);
        listView.setAdapter(adapterStory);
    }
}