package com.example.app_c_truyn.Admin.Story;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.app_c_truyn.Adapter.AdapterStory;
import com.example.app_c_truyn.Database.DatabaseStory;
import com.example.app_c_truyn.Model.Story;
import com.example.app_c_truyn.R;

import java.util.ArrayList;

public class ListStoryActivity extends AppCompatActivity {

    ListView listView;
    Button buttonAdd;
    ArrayList<Story> storyArrayList;
    AdapterStory adapterStory;
    DatabaseStory databaseStory;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        listView = findViewById(R.id.listviewAdmin);
        buttonAdd = findViewById(R.id.buttonThemtruyen);

        initList();

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the ID of the logged-in admin account
                Intent intent1 = getIntent();
                int id = intent1.getIntExtra("Id", 0);

                // Pass the ID to the AddStoryActivity
                Intent intent = new Intent(ListStoryActivity.this, AddStoryActivity.class);
                intent.putExtra("Id", id);
                startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                showOptionsDialog(position);
                return false;
            }
        });
    }

    private void showOptionsDialog(int position) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_optical);
        dialog.setCanceledOnTouchOutside(false);

        Button btnEdit = dialog.findViewById(R.id.buttonEdit);
        Button btnDelete = dialog.findViewById(R.id.buttonDelete);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the ID of the selected story
                int storyId = storyArrayList.get(position).getID();

                // Pass the story ID to the EditStoryActivity
                Intent intent = new Intent(ListStoryActivity.this, EditStoryActivity.class);
                intent.putExtra("story_id", storyId);
                startActivity(intent);

                dialog.dismiss();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDeleteConfirmationDialog(position);
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void showDeleteConfirmationDialog(int position) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialogdelete);
        dialog.setCanceledOnTouchOutside(false);

        Button btnYes = dialog.findViewById(R.id.btn_save);
        Button btnNo = dialog.findViewById(R.id.btn_cancel);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int storyId = storyArrayList.get(position).getID();
                databaseStory.Delete(storyId);

                Intent intent = new Intent(ListStoryActivity.this, ListStoryActivity.class);
                finish();
                startActivity(intent);

                Toast.makeText(ListStoryActivity.this, "Xóa truyện thành công", Toast.LENGTH_SHORT).show();

                dialog.dismiss();
            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void initList() {
        storyArrayList = new ArrayList<>();
        databaseStory = new DatabaseStory(this);

        Cursor cursor1 = databaseStory.getAllStory();
        while (cursor1.moveToNext()) {
            int id = cursor1.getInt(0);
            String tentruyen = cursor1.getString(1);
            String noidung = cursor1.getString(2);
            String anh = cursor1.getString(3);
            int id_tk = cursor1.getInt(4);

            storyArrayList.add(new Story(id, tentruyen, noidung, anh, id_tk));
        }
        cursor1.close();

        adapterStory = new AdapterStory(getApplicationContext(), storyArrayList);
        listView.setAdapter(adapterStory);
    }
}