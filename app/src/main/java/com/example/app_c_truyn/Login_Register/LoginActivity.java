package com.example.app_c_truyn.Login_Register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app_c_truyn.Database.DatabaseStory;
import com.example.app_c_truyn.MainActivity;
import com.example.app_c_truyn.R;

public class LoginActivity extends AppCompatActivity {

    //tao bien cho man dang nhap
    EditText edtTaiKhoan,edtMatKhau;
    Button btnDangNhap;
    TextView btnDangKy;
    //tao doi tuong cho databasedoctruyen
   DatabaseStory db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);//thiết lập giao diện cho Activity bằng cách sử dụng một tệp XML
        AnhXa();
        // doi tuong database doc truyen
        db = new DatabaseStory(this); //tham chiếu đến Activity hiện tại
        // tao su kien click chuyen sang man hinh dang ky voi intent
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = edtTaiKhoan.getText().toString();
                String passWord = edtMatKhau.getText().toString();
                //su dung con tro de lay du lieu
                Cursor cursor = db.getData();

                boolean isLoginSuccessful = false; // Biến kiểm tra trạng thái đăng nhập

                //thuc hien vong lap de lay du lieu tu cursor voi movetonext() di chuyen tiep
                while (cursor.moveToNext()){
                    //du lieu tai khoan o o 1,mat khau o 2,0 id tai khoan , 3 emall,4 phanquyen
                    String datatentaikhoan = cursor.getString(1);
                    String datamatkhau = cursor.getString(2);

                    if(datatentaikhoan.equals(userName) && datamatkhau.equals(passWord)){
                        isLoginSuccessful = true; // Đăng nhập thành công
                        int phanquyen = cursor.getInt(4);
                        int idd = cursor.getInt(0);

                        String email = cursor.getString(3);
                        String tentk = cursor.getString(1);
                        //chuyen qua mainactivity
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        //gui du lieu qua activity la mainactivity
                        intent.putExtra("phanq",phanquyen);
                        intent.putExtra("idd",idd);
                        intent.putExtra("email",email);
                        intent.putExtra("tentaikhoan",tentk);

                        startActivity(intent);
                        break; // Thoát khỏi vòng lặp while sau khi đăng nhập thành công
                    }

                }

                if (isLoginSuccessful) {
                    Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                }

                //thuc hien tra cursor ve dau
                cursor.moveToFirst();
                //dong khi k dung
                cursor.close();
            }
        });

    }
    private void AnhXa(){
        edtMatKhau = findViewById(R.id.matkhau);
        edtTaiKhoan = findViewById(R.id.taikhoan);
        btnDangKy = findViewById(R.id.dangky);
        btnDangNhap = findViewById(R.id.dangnhap);


    }
}