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

        initList();
        // bat clickcho item
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SearchActivity.this,ContentActivity.class);
                String nameStory = arrayList.get(position).getNameStory();
                String content = arrayList.get(position).getContent();
                intent.putExtra("tentruyen",nameStory);
                intent.putExtra("noidung",content);
                startActivity(intent);
            }
        });
        // editText search
        edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void afterTextChanged(Editable s) {filter(s.toString());}
        });
    }
    // search
    private void filter(String text) {
        // Xóa mảng arrayList
        arrayList.clear();

        ArrayList<Story> filteredList = new ArrayList<>();

        for (Story item : storyArrayList) {
            if (item.getNameStory() != null && item.getNameStory().toLowerCase().contains(text.toLowerCase())) {
                // Thêm item vào filteredList
                filteredList.add(item);
                // Thêm vào mảng arrayList
                arrayList.add(item);
            }
        }
        adapterStory.filterList(filteredList);
    }

    // phuong thuc lay du lieu ,gan vao listview
    private void initList() {
        storyArrayList = new ArrayList<>();

        arrayList = new ArrayList<>();

        db = new DatabaseStory(this);

        Cursor cursor = db.getAllStory();

        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String nameStory = cursor.getString(1);
            String content = cursor.getString(2);
            String image = cursor.getString(3);
            int id_tk = cursor.getInt(4);

            storyArrayList.add(new Story(id,nameStory,content,image,id_tk));

            arrayList.add(new Story(id,nameStory,content,image,id_tk));

            adapterStory = new AdapterStory(getApplicationContext(),storyArrayList);

            listView.setAdapter(adapterStory);

        }
        cursor.moveToFirst();
        cursor.close();
    }
}