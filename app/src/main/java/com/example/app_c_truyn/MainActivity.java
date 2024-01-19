package com.example.app_c_truyn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
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
import com.example.app_c_truyn.Adapter.AdapterInformation;
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
    ListView listView, listViewNew, listViewThongTin;
    DrawerLayout drawerLayout;
    String email, nameUser, image_profile;

    ArrayList<Category> categoryArrayList;
    ArrayList<Story> storyArrayList;
    ArrayList<User> userArrayList;

    //adapter
    AdapterStory adapterStory;
    AdapterCategory adapterCategory;
    AdapterInformation adapterInformation;

    DatabaseStory db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseStory(this);

        // nhan du lieu o man dang nhap gui
        Intent intent = getIntent();
        int role = intent.getIntExtra("role", 0);
        int id_login = intent.getIntExtra("id", 0);
        email = intent.getStringExtra("email");
        image_profile = intent.getStringExtra("image_profile");
        nameUser = intent.getStringExtra("username");

        initUI();

        storyArrayList = new ArrayList<>();

        Cursor cursor1 = db.getAllStory();

        while (cursor1.moveToNext()) {
            int id = cursor1.getInt(0);
            String nameStory = cursor1.getString(1);
            String content = cursor1.getString(2);
            String image = cursor1.getString(3);
            int id_tk = cursor1.getInt(4);

            storyArrayList.add(new Story(id, nameStory, content, image, id_tk));
            adapterStory = new AdapterStory(getApplicationContext(), storyArrayList);
            listViewNew.setAdapter(adapterStory);

        }
        cursor1.moveToFirst();
        cursor1.close();

        // bat su kien click item
        listViewNew.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, ContentActivity.class);

                String nameStory = storyArrayList.get(position).getNameStory();
                String content = storyArrayList.get(position).getContent();
                String image = storyArrayList.get(position).getImage();
                String ID_User = String.valueOf(storyArrayList.get(position).getID_TK());

                intent.putExtra("nameStory", nameStory);
                intent.putExtra("content", content);
                intent.putExtra("image", image);
                intent.putExtra("ID_User", ID_User);
                startActivity(intent);

            }
        });

        //thong tin man navi
        userArrayList = new ArrayList<>();
        userArrayList.add(new User(nameUser, email));

        adapterInformation = new AdapterInformation(this, R.layout.item_nagivation, userArrayList);
        listViewThongTin.setAdapter(adapterInformation);

        boolean isAdmin = role == 2;

        categoryArrayList = new ArrayList<>();
        if (isAdmin) {
            categoryArrayList.add(new Category("Thông tin cá nhân", R.drawable.baseline_person_24));
            categoryArrayList.add(new Category("Truyện yêu thích", R.drawable.baseline_favorite_24));
            categoryArrayList.add(new Category("Cài Đặt", R.drawable.baseline_settings_24));
            categoryArrayList.add(new Category("Thông Tin App", R.drawable.baseline_info_24));
            categoryArrayList.add(new Category("Liên Hệ", R.drawable.baseline_send_24));
            categoryArrayList.add(new Category("Đăng Xuất", R.drawable.baseline_logout_24));
            categoryArrayList.add(new Category("Quản lý truyện", R.drawable.ic_post_add));
            categoryArrayList.add(new Category("Quản lý người dùng", R.drawable.baseline_manage_accounts_24));
        } else {
            categoryArrayList.add(new Category("Thông tin cá nhân", R.drawable.baseline_person_24));
            categoryArrayList.add(new Category("Truyện yêu thích", R.drawable.baseline_favorite_24));
            categoryArrayList.add(new Category("Cài Đặt", R.drawable.baseline_settings_24));
            categoryArrayList.add(new Category("Thông Tin App", R.drawable.baseline_info_24));
            categoryArrayList.add(new Category("Liên Hệ", R.drawable.baseline_send_24));
            categoryArrayList.add(new Category("Đăng Xuất", R.drawable.baseline_logout_24));
        }


        // Tạo adapter cho danh sách chuyên mục
        adapterCategory = new AdapterCategory(this, R.layout.item_category, categoryArrayList);
        listView.setAdapter(adapterCategory);

        ActionBar();
        ActionViewFlipper();

        // bat click item cho listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //neu vi tri an vao la thong tin thi chuyen qua man hinh thong tin
                if (position == 0) {
                    Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                    // gui id tai khoan qua man thong tin ca nhan
                    intent.putExtra("Id", id_login);
                    startActivity(intent);
                } else if (position == 1) {
                    startActivity(new Intent(MainActivity.this, FavouriteActivity.class));
                } else if (position == 2) {
                    startActivity(new Intent(MainActivity.this, SettingAppActivity.class));
                } else if (position == 3) {
                    startActivity(new Intent(MainActivity.this, InformationActivity.class));
                } else if (position == 4) {
                    startActivity(new Intent(MainActivity.this, ContactActivity.class));
                } else if (position == 5) {
                    finish();//đăng xuất
                } else if (position == 6) {
                    Intent intent = new Intent(MainActivity.this, ListStoryActivity.class);
                    // gui id tai khoan qua man admin
                    intent.putExtra("Id", id_login);
                    startActivity(intent);
                } else if (position == 7) {
                    Intent intent = new Intent(MainActivity.this, ListUserActivity.class);
                    // gui id tai khoan qua man admin
                    intent.putExtra("Id", id_login);
                    startActivity(intent);
                }
            }
        });

    }

    // Thanh actionbar với Toolbar
    private void ActionBar() {
        // ham ho tro toolbar
        setSupportActionBar(toolbar);

        // set nut cho actionbar
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        // tao icon cho toolbar
        toolbar.setNavigationIcon(R.drawable.baseline_menu_24);

        // Bắt sự kiện click
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    //phuong thuc cho chay quang cao voi ViewFlipper
    private void ActionViewFlipper() {
        //mang chua tam anh cho quang cao
        ArrayList<String> mangquangcao = new ArrayList<>();
        //add anh vao mang
        mangquangcao.add("https://truyendangian.com/wp-content/uploads/2023/06/hai-quan-day.jpg");
        mangquangcao.add("https://truyendangian.com/wp-content/uploads/2023/05/cau-be-chu-minh.jpg");
        mangquangcao.add("https://truyendangian.com/wp-content/uploads/2023/05/truyen-thuyet-ve-ho-guom.jpg");
        mangquangcao.add("https://truyendangian.com/wp-content/uploads/2023/01/dan-dan-quan.jpg");
        mangquangcao.add("https://media.istockphoto.com/id/1087508538/vi/anh/m%E1%BB%9F-s%C3%A1ch-v%E1%BB%9Bi-phong-" +
                "c%E1%BA%A3nh-v%E1%BA%BD-tay.jpg?s=612x612&w=0&k=20&c=rRcq56RaxtIEJZ7JPyLGmPwVNTsInhFOphp8ou7CYqY=");


        //thuc hien vong lap for gan anh vao imageview, roi tu image view len app
        for (int i = 0; i < mangquangcao.size(); i++) {
            ImageView imageView = new ImageView(getApplicationContext());
            //su dung ham thu vien picasso
            Picasso.get().load(mangquangcao.get(i)).into(imageView);

            //phuong thuc chinh tam hinh vua quang cao
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            //them anh tu image vào view flipper
            viewFlipper.addView(imageView);
        }
        //thiet lap tu dong chay cho viewflipper chay trong 3s
        viewFlipper.setFlipInterval(3000);
        //run auto
        viewFlipper.setAutoStart(true);
        //gọi animation cho vao va ra
        Animation animation_slide_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);

        //goi animation vao flipper
        viewFlipper.setInAnimation(animation_slide_in);
        viewFlipper.setInAnimation(animation_slide_out);


    }

    //Nạp một menu tìm kiếm vào Actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // neu click vao icon tim kiem se chuyen sang man hinh tim kiem
        if (item.getItemId() == R.id.menu1) {
            Intent intent = new Intent(MainActivity.this, SearchActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    private void initUI() {
        toolbar = findViewById(R.id.toolbarmanhinhchinh);
        viewFlipper = findViewById(R.id.viewflipper);
        listViewNew = findViewById(R.id.listviewNew);
        listView = findViewById(R.id.listviewmanhinhchinh);
        listViewThongTin = findViewById(R.id.listviewthongtin);
        navigationView = findViewById(R.id.navigatationView);
        drawerLayout = findViewById(R.id.drawerlayout);
    }
}