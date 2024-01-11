package com.example.app_c_truyn.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.app_c_truyn.Model.Story;
import com.example.app_c_truyn.Model.User;

public class DatabaseStory extends SQLiteOpenHelper {
    private static String DATABASE_NAME = "doctruyen";
    private static String TABLE_TAIKHOAN = "taikhoan";
    private static String ID_TAI_KHOAN = "idtaikhoan";
    private static String TEN_TAI_KHOAN = "tentaikhoan";
    private static String MAT_KHAU = "matkhau";
    private static String PHAN_QUYEN = "phanquyen";
    private static String EMAIL = "email";
    private static int VERSION = 1;

    private static String TABLE_STORY = "story";
    private static String ID_STORY = "id_story";
    private static String NAME_STORY = "title";
    private static String CONTENT = "content";
    private static String IMAGE = "image";

    private Context context;
    private final String CREATE_TABLE_ACCOUNT = "CREATE TABLE "+ TABLE_TAIKHOAN +" ( "+ID_TAI_KHOAN+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +TEN_TAI_KHOAN+" TEXT UNIQUE, "
            +MAT_KHAU+" TEXT, "
            +EMAIL+" TEXT, "
            + PHAN_QUYEN+" INTEGER) ";
    private final String CREATE_TABLE_STORY = "CREATE TABLE "+ TABLE_STORY +" ( "+ID_STORY+" integer primary key AUTOINCREMENT, "
            +NAME_STORY +" TEXT UNIQUE, "
            +CONTENT+" TEXT, "
            +IMAGE+" TEXT, "+ID_TAI_KHOAN+" INTEGER , FOREIGN KEY ( "+ ID_TAI_KHOAN +" ) REFERENCES "+
            TABLE_TAIKHOAN+"("+ID_TAI_KHOAN+"))";
    private final String CREATE_ADMIN = "INSERT INTO " + TABLE_TAIKHOAN
            + " VALUES (null,'admin12','admin12','admin@gmail.com',2)";

    public DatabaseStory(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ACCOUNT);
        db.execSQL(CREATE_ADMIN);
        db.execSQL(CREATE_TABLE_STORY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // phuong thuc lay tat ca tai khoan
    public Cursor getData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " +TABLE_TAIKHOAN,null);
        return res;

    }
    //phuong thuc add tai khoan vao database
    public void AddTaiKhoan(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        if (CheckUser(user.getUserName(), user.getEmail())) {
            Log.e("Add Tk", "Username already exists");
            return;
        }
        ContentValues values = new ContentValues();
        values.put(TEN_TAI_KHOAN, user.getUserName());
        values.put(MAT_KHAU, user.getPassWord());
        values.put(EMAIL, user.getEmail());
        values.put(PHAN_QUYEN, user.getRoles());

        db.insert(TABLE_TAIKHOAN, null, values);

        db.close();
        Log.e("Add Tk", "TC");
    }

    public boolean CheckUser(String taikhoan, String email) {
        SQLiteDatabase db = this.getReadableDatabase();

        // Truy vấn tài khoản và email từ bảng TaiKhoan
        Cursor cursor = db.query(TABLE_TAIKHOAN, new String[] {TEN_TAI_KHOAN, EMAIL},
                TEN_TAI_KHOAN + "=? OR " + EMAIL + "=?",
                new String[] {taikhoan, email}, null, null, null, null);

        // Kiểm tra xem con trỏ có dữ liệu hay không
        boolean tonTai = false;
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                // Tài khoản hoặc email đã tồn tại trong cơ sở dữ liệu
                tonTai = true;
            }
            cursor.close();
        }

        // Trả về kết quả kiểm tra tài khoản và email
        return tonTai;
    }

    // lay 3 truyen moi nhat
    public Cursor getData1(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM "+TABLE_STORY+" ORDER BY "+ID_STORY+" DESC LIMIT 4",null);
    }

    //Lay tat ca truyen
    public Cursor getData2(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM "+TABLE_STORY,null);
    }

    //add chuyen
    public void AddStory(Story story){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NAME_STORY,story.getNameStory());
        values.put(CONTENT,story.getContent());
        values.put(IMAGE,story.getImage());
        values.put(ID_TAI_KHOAN,story.getID_TK());

        db.insert(TABLE_STORY,null,values);
        db.close();


    }
    //delete truyen
    public int Delete(int i){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.delete(TABLE_STORY,ID_STORY+" = "+i,null);
    }
}
