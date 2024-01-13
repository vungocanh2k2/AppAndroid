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

import java.util.ArrayList;
import java.util.List;

public class DatabaseStory extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "doctruyen";
    private static final String TABLE_TAIKHOAN = "taikhoan";
    private static final String ID_TAI_KHOAN = "idtaikhoan";
    private static final String TEN_TAI_KHOAN = "tentaikhoan";
    private static final String MAT_KHAU = "matkhau";
    private static final String PHAN_QUYEN = "phanquyen";
    private static final String EMAIL = "email";
    private static final int VERSION = 1;

    private static final String TABLE_STORY = "story";
    private static final String ID_STORY = "id_story";
    private static final String NAME_STORY = "title";
    private static final String CONTENT = "content";
    private static final String IMAGE = "image";

    private Context context;

    public DatabaseStory(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Tạo bảng user
        String CREATE_TABLE_ACCOUNT = "CREATE TABLE " + TABLE_TAIKHOAN + " ( " + ID_TAI_KHOAN + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TEN_TAI_KHOAN + " TEXT UNIQUE, "
                + MAT_KHAU + " TEXT, "
                + EMAIL + " TEXT, "
                + PHAN_QUYEN + " INTEGER) ";
        db.execSQL(CREATE_TABLE_ACCOUNT);

        //Tạo tài khoản admin
        String CREATE_ADMIN = "INSERT INTO " + TABLE_TAIKHOAN
                + " VALUES (null,'admin12','admin12','admin@gmail.com',2)";
        db.execSQL(CREATE_ADMIN);

        //Tạo bảng truyện
        String CREATE_TABLE_STORY = "CREATE TABLE " + TABLE_STORY + " ( " + ID_STORY + " integer primary key AUTOINCREMENT, "
                + NAME_STORY + " TEXT UNIQUE, "
                + CONTENT + " TEXT, "
                + IMAGE + " TEXT, " + ID_TAI_KHOAN + " INTEGER , FOREIGN KEY ( " + ID_TAI_KHOAN + " ) REFERENCES " +
                TABLE_TAIKHOAN + "(" + ID_TAI_KHOAN + "))";
        db.execSQL(CREATE_TABLE_STORY);

        String SQLQuery4 = "INSERT INTO story VALUES (null,'Hai quan đấy!','Lão quan nọ có tính nịnh vợ. Lão ra lệnh hễ ai gặp cũng phải chào: “Lạy hai quan ạ!”.\n" +
                "\n" +
                "Có chú bé giả vờ hoàn toàn chẳng hay gì về cái lệnh ấy. Thấy vợ quan đi cạnh quan, chú bế chó, đi sát vào.\n" +
                "\n" +
                "Gặp ai đi qua, chú gào to:\n" +
                "\n" +
                "– Hai quan đấy!\n" +
                "\n" +
                "Quan thấy chú bé có ý xỏ mình, quát:\n" +
                "\n" +
                "– Mày dám láo hử?\n" +
                "\n" +
                "Chú chỉ vào chó:\n" +
                "\n" +
                "– Dạ bẩm, chó hai quan, chó đáng giá hai quan, thật đấy ạ!.','https://truyendangian.com/wp-content/uploads/2023/06/hai-quan-day.jpg',1)";
        db.execSQL(SQLQuery4);
        String SQLQuery5 = "INSERT INTO story VALUES (null,'Cậu bé Chu Minh','Người dân vùng sông Mã ở Thanh Hóa vẫn còn kể cho nhau nghe câu chuyện về một cậu bé thời xưa. Tên cậu là Chu Minh. Cậu có tài lặn dưới nước như rái cá.\n" +
                "\n" +
                "Vào thời nhà Hán đô hộ nước ta, thái thú [1] quận Cửu Chân [2] tên là Nghê Thức vô cùng tàn ác, bị người người căm ghét. Một nông dân tên là Chu Đạt đã dìm chết Nghê Thức trên dòng sông Mã. Về sau, bọn quan quân nhà Hán tạc tượng Nghê Thức và lập đền thờ trên bờ sông.\n" +
                "\n" +
                "Từ ngày được ông nội kể cho nghe sự tích trên, Chu Minh rất thích được mang tên dòng họ Chu. Cậu hỏi ông: “Tại sao ta không tạc tượng cụ Chu Đạt mà để người Tàu lập đền thờ Nghê Thức tàn ác?”. Ông nội nghẹn ngào: “Dân ta xưa bị giặc Hán xâm lăng, nay bị giặc Ngô giày xéo. Cháu ơi, người dân mất nước chưa có quyền được sống, thì làm gì có quyền được ca ngợi công đức của ông cha mình? Họa chăng đến đời các cháu.”.\n" +
                "\n" +
                "Từ đó, mỗi khi đi cắt cỏ qua đền Nghê Thức, Chu Minh lại nhớ đến lời ông. Cho đến một hôm không biết là ngày gì, Minh thấy một viên quan Ngô cùng với mấy tên lính đến mở cửa đền và thắp hương cúng vái. Nhân lúc chúng uống rượu ngủ say, Minh lẻn vào đền, nhẹ nhàng ngả pho tượng, vác lên vai, chạy miết ra phía bờ sông.\n" +
                "\n" +
                "Cậu bắt chước cụ Chu Đạt xưa, dìm Nghê Thức xuống đáy sông cho bõ ghét. Nhưng cậu lặn tới năm, sáu hơi, vật lộn với pho tượng đã mệt nhoài mà Nghê Thức gỗ vẫn nổi phềnh phềnh không chìm.\n" +
                "\n" +
                "Bỗng Chu Minh cười: “Ồ, thế mà mình nghĩ không ra. Được, ta sẽ buộc cổ ngươi vào một hòn đá to, xem ngươi còn vùng vẫy được nữa hay không?”.\n" +
                "\n" +
                "Nghê Thức gỗ bị đeo đá chìm nghỉm, Minh còn lặn vài hơi xem thử pho tượng đã chìm tới đáy chưa.\n" +
                "\n" +
                "Sẵn lòng yêu nước và căm thù giặc, ít lâu sau, Chu Minh tham gia nghĩa quân, trở thành người tùy tùng tin cậy của Bà Triệu [3]. Cậu nghĩa quân tí hon ấy có gương trán cao, một chỏm tóc trên đỉnh đầu. Cậu thường mặc chiếc áo da chồn, lại đeo bên hông một bao tên và khoác một cây cung như một anh chàng đi săn ở rừng. Trong nghĩa quân, ai cũng biết câu chuyện Chu Minh đã từng thủy chiến với Nghê Thức gỗ.','https://truyendangian.com/wp-content/uploads/2023/05/cau-be-chu-minh.jpg',1)";
        db.execSQL(SQLQuery5);
        String SQLQuery6 = "INSERT INTO story VALUES (null,'Truyền thuyết về Hồ Gươm','Vào thời giặc Minh đặt ách đô hộ [1] ở nư\u00ADớc Nam, chúng coi dân ta như cỏ rác, làm nhiều điều bạo ngược, thiên hạ căm giận chúng đến xương tủy. Bấy giờ ở vùng Lam Sơn, nghĩa quân nổi dậy chống lại chúng, nhưng trong buổi đầu thế lực còn non yếu nên nhiều lần nghĩa quân bị thua. Thấy vậy, đức Long Quân [2] quyết định cho nghĩa quân mượn thanh gươm thần để họ giết giặc.\n" +
                "\n" +
                "Hồi ấy, ở Thanh Hóa có một người làm nghề đánh cá tên là Lê Thận. Một đêm nọ, Thận thả lưới ở một bến vắng như\u00AD thường lệ. Khi kéo lưới lên, chàng thấy nằng nặng, trong bụng mừng thầm, chắc là có cá to. Nhưng khi thò tay vào bắt cá, Thận chỉ thấy có một thanh sắt; chàng vứt luôn xuống nư\u00ADớc, rồi lại thả lưới ở một chỗ khác.\n" +
                "\n" +
                "Lần thứ hai cất lưới lên cũng thấy nặng tay; Thận không ngờ thanh sắt vừa rồi lại chui vào lư\u00ADới mình. Chàng lại nhặt lên và ném xuống sông. Lần thứ ba, lại vẫn thanh sắt ấy mắc vào lư\u00ADới. Lấy làm quái lạ, Thận đ\u00ADưa lại mồi lửa nhìn xem. Bỗng chàng reo lên:\n" +
                "\n" +
                "– Ha ha! Một l\u00ADưỡi gư\u00ADơm!\n" +
                "\n" +
                "Về sau Thận gia nhập đoàn quân khởi nghĩa Lam Sơn. Chàng hăng hái gan dạ không nề nguy hiểm. Một hôm chủ tư\u00ADớng Lê Lợi cùng mấy ng\u00ADười tùy tùng đến nhà Thận. Trong túp lều tối om, thanh sắt hôm đó tự nhiên sáng rực lên ở xó nhà. Lấy làm lạ, Lê Lợi cầm lên xem và thấy có hai chữ “Thuận Thiên” [3] khắc sâu vào lư\u00ADỡi gươm. Song tất cả mọi ngư\u00ADời vẫn không biết đó là báu vật.\n" +
                "\n" +
                "Một hôm, bị giặc đuổi, Lê Lợi và các t\u00ADướng chạy tháo thân mỗi ngư\u00ADời một ngả. Lúc đi qua một khu rừng, Lê Lợi bỗng thấy một ánh sáng lạ trên ngọn cây đa. Ông trèo lên mới biết đó là một cái chuôi g\u00ADươm nạm ngọc [4]. Nhớ đến lư\u00ADỡi gư\u00ADơm ở nhà Lê Thận, Lê Lợi rút lấy chuôi giắt vào l\u00ADưng.\n" +
                "\n" +
                "Ba ngày sau, Lê Lợi gặp lại tất cả các bạn trong đó có Lê Thận. Lê Lợi mới đem chuyện bắt đư\u00ADợc chuôi g\u00ADươm kể lại cho mọi ngư\u00ADời nghe. Khi đem tra g\u00ADươm vào chuôi thì vừa như\u00AD in.\n" +
                "\n" +
                "Lê Thận nâng g\u00ADươm lên ngang đầu nói với Lê Lợi:\n" +
                "\n" +
                "– Đây là Trời có ý phó thác cho minh công [5] làm việc lớn. Chúng tôi nguyện đem x\u00ADương thịt của mình theo minh công, cùng với thanh g\u00ADươm thần này để báo đền Tổ quốc!\n" +
                "\n" +
                "Từ đó nhuệ khí [6] của nghĩa quân ngày một tăng tiến. Trong tay Lê Lợi, thanh gư\u00ADơm thần tung hoành [7] khắp các trận địa, làm cho quân Minh bạt vía. Uy thanh của nghĩa quân vang khắp nơi. Họ không phải trốn tránh như tr\u00ADước mà xông xáo đi tìm giặc. Họ không phải ăn uống khổ cực như\u00AD trư\u00ADớc nữa, đã có những kho lương của giặc mới cư\u00ADớp đư\u00ADợc tiếp tế cho họ. G\u00ADươm thần đã mở đ\u00ADường cho họ đánh tràn ra mãi, cho đến lúc không còn bóng một tên giặc trên đất n\u00ADước.\n" +
                "\n" +
                "Một năm sau khi đuổi giặc Minh, một hôm Lê Lợi – bấy giờ đã làm vua – cư\u00ADỡi thuyền rồng dạo quanh hồ Tả Vọng tr\u00ADước kinh thành. Nhân dịp đó, Long Quân sai rùa vàng lên đòi lại thanh gư\u00ADơm thần. Khi chiếc thuyền rồng tiến ra giữa hồ, tự nhiên có một con rùa lớn nhô đầu và mai lên khỏi mặt n\u00ADước. Theo lệnh vua, thuyền đi chậm lại. Đứng ở mạn thuyền, vua thấy l\u00ADưỡi g\u00ADươm thần đeo bên ngư\u00ADời tự nhiên động đậy. Con rùa vàng không sợ ng\u00ADười, nhô đầu lên cao nữa và tiến về phía thuyền vua. Nó đứng nổi trên mặt nước và nói: “Xin bệ hạ hoàn g\u00ADươm lại cho Long Quân!”.\n" +
                "\n" +
                "Vua rút g\u00ADươm quẳng về phía rùa vàng. Nhanh như\u00AD cắt, rùa há miệng đớp lấy thanh gư\u00ADơm và lặn xuống nư\u00ADớc. G\u00ADươm và rùa đã chìm đáy n\u00ADước, ngư\u00ADời ta vẫn còn thấy vật gì sáng le lói d\u00ADưới mặt hồ xanh.\n" +
                "\n" +
                "Từ đó hồ Tả Vọng bắt đầu mang tên là Hồ G\u00ADươm hay hồ Hoàn Kiếm.','https://truyendangian.com/wp-content/uploads/2023/05/truyen-thuyet-ve-ho-guom.jpg',1)";
        db.execSQL(SQLQuery6);
        String SQLQuery7 = "INSERT INTO  story VALUES (null,'Dân dần quan','Có hai anh lính hầu hạ quan lâu ngày, thấy quan đã ác lại hay ăn tiền, ai có việc vào cửa quan là y như bị đánh đập tàn tệ, đến lúc xì tiền ra mới thôi.\n" +
                "\n" +
                "Một hôm, rỗi rãi, hai anh ngồi kháo chuyện với nhau nói xấc quan. Một anh bảo:\n" +
                "\n" +
                "– Ác thế thì có ngày dân nó quật lại cho mà xem.\n" +
                "\n" +
                "Quan quán quạn chi quàn quan\n" +
                "Dân dấn dận chi dần dân.\n" +
                "Quan là quan, quan quàn quan\n" +
                "Dân là dân, dân dần quan.\n" +
                "\n" +
                "Chẳng ngờ, quan đi qua nghe được, trợn mắt hỏi:\n" +
                "\n" +
                "– Bay nói gì thế?\n" +
                "\n" +
                "Anh kia nói chữa:\n" +
                "\n" +
                "– Bẩm quan, con bảo: Quan quản dân, dân… cần quan. Không có quan thì ai cai trị dân.','https://truyendangian.com/wp-content/uploads/2023/01/dan-dan-quan.jpg',1)";
        db.execSQL(SQLQuery7);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TAIKHOAN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STORY);
        onCreate(db);
    }

    // phuong thuc lay tat ca tai khoan
    public Cursor getData(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " +TABLE_TAIKHOAN,null);

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

    // Lấy danh sách tất cả người dùng từ bảng người dùng
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_TAIKHOAN;

        Cursor cursor = db.rawQuery(selectQuery, null);

        // Duyệt qua tất cả các bản ghi và thêm chúng vào danh sách người dùng
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(Integer.parseInt(cursor.getString(0)));
                user.setUserName(cursor.getString(1));
                user.setPassWord(cursor.getString(2));
                user.setEmail(cursor.getString(3));

                userList.add(user);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return userList;
    }

    public User getUserById(int userId) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_TAIKHOAN, new String[] { ID_TAI_KHOAN,
                        TEN_TAI_KHOAN, MAT_KHAU, EMAIL }, ID_TAI_KHOAN + "=?",
                new String[] { String.valueOf(userId) }, null, null, null, null);

        User user = null;

        if (cursor.moveToFirst()) {
            user = new User();
            user.setId(Integer.parseInt(cursor.getString(0)));
            user.setUserName(cursor.getString(1));
            user.setPassWord(cursor.getString(2));
            user.setEmail(cursor.getString(3));
        }

        cursor.close();

        return user;
    }

    // Cập nhật thông tin người dùng
    public void updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TEN_TAI_KHOAN, user.getUserName());
        values.put(MAT_KHAU, user.getPassWord());
        values.put(EMAIL, user.getEmail());

        db.update(TABLE_TAIKHOAN, values, ID_TAI_KHOAN + "=?", new String[] { String.valueOf(user.getId()) });

        db.close();
    }

    //Xóa thông tin người dùng
    public void deleteUser(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TAIKHOAN, ID_TAI_KHOAN + "=?", new String[] { String.valueOf(id) });
        db.close();
    }

    // lay 3 truyen moi nhat
    public Cursor getDataStory(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM "+TABLE_STORY+" ORDER BY "+ID_STORY+" DESC LIMIT 4",null);
    }

    //Lấy tất cả truyện có trong csdl
    public Cursor getAllStory(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM "+TABLE_STORY,null);
    }

    //Thêm truyện
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

    //Sửa truyện
    public void updateStory(Story story) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NAME_STORY, story.getNameStory());
        values.put(CONTENT, story.getContent());
        values.put(IMAGE, story.getImage());

        db.update(TABLE_STORY, values, ID_TAI_KHOAN + " = ?",
                new String[]{String.valueOf(story.getID())});
        db.close();
    }

    //Xóa truyện
    public int Delete(int i){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.delete(TABLE_STORY,ID_STORY+" = "+i,null);
    }
}
