package com.example.app_c_truyn.Admin;

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

public class AdminActivity extends AppCompatActivity {

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
                // lay id tai khoan da biet tai khoan admin nao da vao chinh sua
                Intent intent1 = getIntent();
                int id = intent1.getIntExtra("Id",0);
                //tiep tuc gui id qua man hinh them truyeb
                Intent intent = new Intent(AdminActivity.this,AddStoryActivity.class);
                intent.putExtra("Id",id);
                startActivity(intent);
            }
        });

        //click item long se xoa item
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                DialogDelete(position);
                int idtruyen = storyArrayList.get(position).getID();
                // xoa du lieu
                databaseStory.Delete(idtruyen);
                // cap nhat lai activiti
                Intent intent = new Intent(AdminActivity.this,AdminActivity.class);
                finish();
                startActivity(intent);

                Toast.makeText(AdminActivity.this, "Xoa truyen thanh cong", Toast.LENGTH_SHORT).show();

                return false;
            }
        });
    }
    // phuong thuc dialog hien thi cua so xoa
    private void DialogDelete(int position){
        Dialog dialog = new Dialog(this);
        // nap layout vao dialog

        dialog.setContentView(R.layout.dialogdelete);
        //tat click ra ngoai la dong ,chi click ra no moi dong
        dialog.setCanceledOnTouchOutside(false);
        // anh xa
        Button btnYes = dialog.findViewById(R.id.buttonYes);
        Button btnNo = dialog.findViewById(R.id.buttonNo);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idtruyen = storyArrayList.get(position).getID();
                // xoa du lieu
                databaseStory.Delete(idtruyen);
                // cap nhat lai activiti
                Intent intent = new Intent(AdminActivity.this,AdminActivity.class);

                startActivity(intent);
                finish();
                Toast.makeText(AdminActivity.this, "Xoa truyen thanh cong", Toast.LENGTH_SHORT).show();


            }
        });
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //thuc hien doc dialog
                dialog.cancel();
            }
        });
        // run dialog
        dialog.show();


    }
    // gan du lieu cho list view
    private void initList() {
        storyArrayList = new ArrayList<>();
        databaseStory = new DatabaseStory(this);

        Cursor cursor1 = databaseStory.getData2();
        while (cursor1.moveToNext()){
            int id = cursor1.getInt(0);
            String tentruyen = cursor1.getString(1);
            String noidung = cursor1.getString(2);
            String anh = cursor1.getString(3);
            int id_tk = cursor1.getInt(4);

            storyArrayList.add(new Story(id,tentruyen,noidung,anh,id_tk));

            adapterStory = new AdapterStory(getApplicationContext(),storyArrayList);

            listView.setAdapter(adapterStory);

        }
        cursor1.moveToFirst();
        cursor1.close();
    }
}