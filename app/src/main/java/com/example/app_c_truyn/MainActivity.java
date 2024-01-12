package com.example.app_c_truyn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;
import com.example.app_c_truyn.Adapter.AdapterCategory;
import com.example.app_c_truyn.Adapter.AdapterStory;
import com.example.app_c_truyn.Admin.Story.ListStoryActivity;
import com.example.app_c_truyn.Admin.User.ListUserActivity;
import com.example.app_c_truyn.Database.DatabaseStory;
import com.example.app_c_truyn.Model.Category;
import com.example.app_c_truyn.Model.Story;
import com.example.app_c_truyn.Model.User;
import com.squareup.picasso.Picasso;
import com.google.android.material.navigation.NavigationView;
import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    NavigationView navigationView;
    ListView listView,listViewNew,listViewThongTin;
    DrawerLayout drawerLayout;
    String email;
    String nameUser;
    ArrayList<Category> categoryArrayList;
    ArrayList<Story> TruyenArraylist;
    AdapterStory adapterStory;
    ArrayList<User> userArrayListArrayList;
    AdapterCategory adapterCategory;
    DatabaseStory databaseStory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseStory = new DatabaseStory(this);

        // nhan du lieu o man dang nhap gui
        Intent intentpq = getIntent();
        int i = intentpq.getIntExtra("phanq",0);
        int idd = intentpq.getIntExtra("idd",0);
        email = intentpq.getStringExtra("email");
        nameUser = intentpq.getStringExtra("tentaikhoan");

        toolbar = findViewById(R.id.toolbarmanhinhchinh);
        viewFlipper = findViewById(R.id.viewflipper);
        listViewNew = findViewById(R.id.listviewNew);
        listView = findViewById(R.id.listviewmanhinhchinh);
        listViewThongTin = findViewById(R.id.listviewthongtin);
        navigationView = findViewById(R.id.navigatationView);
        drawerLayout = findViewById(R.id.drawerlayout);

        TruyenArraylist = new ArrayList<>();

        Cursor cursor1 = databaseStory.getData();
        while (cursor1.moveToNext()){
            int id = cursor1.getInt(0);
            String tentruyen = cursor1.getString(1);
            String noidung = cursor1.getString(2);
            String anh = cursor1.getString(3);
            int id_tk = cursor1.getInt(4);

            TruyenArraylist.add(new Story(id,tentruyen,noidung,anh,id_tk));
            adapterStory = new AdapterStory(getApplicationContext(),TruyenArraylist);
            listViewNew.setAdapter(adapterStory);

        }
        cursor1.moveToFirst();
        cursor1.close();

        //thong tin
        userArrayListArrayList = new ArrayList<>();
        userArrayListArrayList.add(new User(nameUser,email));

       /* adapterthongtin = new adapterthongtin(this,R.layout.nagivation_thongtin,taiKhoanArrayList);
        listViewThongTin.setAdapter(adapterthongtin);*/

        boolean isAdmin = false;

        if (i==2) {
            isAdmin = true;
        }

        categoryArrayList = new ArrayList<>();
        categoryArrayList.add(new Category("Thông Tin", R.drawable.baseline_face_24));
        categoryArrayList.add(new Category("Đăng Xuất", R.drawable.baseline_logout_24));
        if (isAdmin) {
            categoryArrayList.add(new Category("Đăng Bài", R.drawable.baseline_post_add_24));
            categoryArrayList.add(new Category("Quản lý người dùng", R.drawable.baseline_logout_24));
        }

        // Tạo adapter cho danh sách chuyên mục
        adapterCategory = new AdapterCategory(this,R.layout.category,categoryArrayList);
        listView.setAdapter(adapterCategory);

        ActionBar();
        ActionViewFlipper();
        // bat su kien click item
        listViewNew.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,ContentActivity.class);

                String tent = TruyenArraylist.get(position).getNameStory();
                String noidungt = TruyenArraylist.get(position).getContent();
                intent.putExtra("tentruyen",tent);
                intent.putExtra("noidung",noidungt);
                startActivity(intent);

            }
        });

        // bat clickitem cho listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //neu vi tri an vao la thong tin thi chuyen qua man hinh thong tin
                if(position == 0){
                    Intent intent = new Intent(MainActivity.this,InformationActivity.class);
                    startActivity(intent);
                }
                else if(position == 1){
                    finish();//dang xuat
                }
                else if(position==2){
                    Intent intent = new Intent(MainActivity.this, ListStoryActivity.class);
                    // gui id tai khoan qua man admin
                    intent.putExtra("Id",idd);
                    startActivity(intent);
                }
                else if(position == 3){
                    Intent intent = new Intent(MainActivity.this, ListUserActivity.class);
                    // gui id tai khoan qua man admin
                    intent.putExtra("Id",idd);
                    startActivity(intent);
                }
            }
        });

    }

    private void ActionBar() {
        // ham ho tro toolbar
        setSupportActionBar(toolbar);
        // set nut cho actiocbar
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        // tao icon cho toolbar
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);

            }
        });
    }

    //phuong thuc cho chay quang cao voi ViewFlipper
    private void ActionViewFlipper(){
        //mang chua tam anh cho quang cao
        ArrayList<String> mangquangcao = new ArrayList<>();
        //add anh vao mang
        mangquangcao.add("https://truyendangian.com/wp-content/uploads/2023/06/hai-quan-day.jpg");
        mangquangcao.add("https://truyendangian.com/wp-content/uploads/2023/05/cau-be-chu-minh.jpg");
        mangquangcao.add("https://truyendangian.com/wp-content/uploads/2023/05/truyen-thuyet-ve-ho-guom.jpg");
        mangquangcao.add("https://truyendangian.com/wp-content/uploads/2023/01/dan-dan-quan.jpg");

        //thuc hien vong lap for gan anh vao imageview, roi tu image view len app
        for(int i=0;i<mangquangcao.size();i++){
            ImageView imageView = new ImageView(getApplicationContext());
            //su dung ham thu vien picasso
            Picasso.get().load(mangquangcao.get(i)).into(imageView);

            //phuong thuc chinh tam hinh vua quang cao
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            //them anh tu image vào view flipper
            viewFlipper.addView(imageView);
        }
        //thiet lap tu dong chay cho viewflipper chay trong 4s
        viewFlipper.setFlipInterval(3000);
        //run auto
        viewFlipper.setAutoStart(true);
        //gọi animation cho vao va ra
        Animation animation_slide_in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_right);

        //goi animation vao flipper
        viewFlipper.setInAnimation(animation_slide_in);
        viewFlipper.setInAnimation(animation_slide_out);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // neu click vao icon tim kiem se chuyen sang man hinh tim kiem
        switch (item.getItemId()){
            case R.id.menu1:
                Intent intent = new Intent(MainActivity.this,SearchActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}