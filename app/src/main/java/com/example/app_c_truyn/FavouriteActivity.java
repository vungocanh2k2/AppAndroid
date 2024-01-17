package com.example.app_c_truyn;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.app_c_truyn.Adapter.AdapterStory;
import com.example.app_c_truyn.Database.DatabaseStory;
import com.example.app_c_truyn.Model.Story;

import java.util.ArrayList;
import java.util.List;

public class FavouriteActivity extends AppCompatActivity {
    ImageButton backBtn;
    ListView listView;
    AdapterStory adapterStory;
    List<Story> favoriteList;
    DatabaseStory db;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);

        // Initialize variables
        listView = findViewById(R.id.listviewFav);
        backBtn = findViewById(R.id.backFav);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        db = new DatabaseStory(this);

        favoriteList = new ArrayList<>();

        Cursor cursor1 = db.getAllFavoriteStoriesList();

        while (cursor1.moveToNext()) {
            int id = cursor1.getInt(0);
            String nameStory = cursor1.getString(1);
            String content = cursor1.getString(2);
            String image = cursor1.getString(3);
            int id_tk = cursor1.getInt(4);

            favoriteList.add(new Story(id, nameStory, content, image, id_tk));
            adapterStory = new AdapterStory(getApplicationContext(), (ArrayList<Story>) favoriteList);
            listView.setAdapter(adapterStory);

        }
        cursor1.moveToFirst();
        cursor1.close();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(FavouriteActivity.this, ContentActivity.class);

                String nameStory = favoriteList.get(position).getNameStory();
                String content = favoriteList.get(position).getContent();
                String image = favoriteList.get(position).getImage();
                String ID_User = String.valueOf(favoriteList.get(position).getID_TK());

                intent.putExtra("nameStory", nameStory);
                intent.putExtra("content", content);
                intent.putExtra("image", image);
                intent.putExtra("ID_User", ID_User);
                startActivity(intent);

            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                showDeleteConfirmationDialog(position);
                return true;
            }
        });
    }

    // Show delete confirmation dialog
    private void showDeleteConfirmationDialog(int position) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialogdelete);
        dialog.setCanceledOnTouchOutside(false);

        Button btnYes = dialog.findViewById(R.id.buttonYes);
        Button btnNo = dialog.findViewById(R.id.buttonNo);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int storyId = favoriteList.get(position).getID();
                db.DeleteStoryFavo(storyId);

                // Refresh the activity
                Intent intent = new Intent(FavouriteActivity.this, FavouriteActivity.class);
                finish();
                startActivity(intent);

                Toast.makeText(FavouriteActivity.this, "Xóa truyện thành công", Toast.LENGTH_SHORT).show();

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
}
