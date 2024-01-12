package com.example.app_c_truyn.Adapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.app_c_truyn.Model.User;
import com.example.app_c_truyn.R;

import java.util.List;

public class AdapterInformation  extends BaseAdapter {
    private final Context context;
    private final int layout;

    private final List<User> userList;

    // Constructor: khởi tạo adapter với context, layout và danh sách các đối tượng TaiKhoan
    public AdapterInformation(Context context, int layout, List<User> userList) {
        this.context = context;
        this.layout = layout;
        this.userList = userList;
    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    // Trả về một View hiển thị phần tử tại vị trí được chỉ định
    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Tạo đối tượng LayoutInflater để inflate layout
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout,null);

        // Lấy TextView để hiển thị tên tài khoản và email
        TextView txtTenTaiKhoan = (TextView) convertView.findViewById(R.id.TEXT_NAME);
        TextView txtEmail = (TextView) convertView.findViewById(R.id.TEXT_GMAIL);

        // Lấy đối tượng TaiKhoan tại vị trí được chỉ định
        User user = userList.get(position);

        // Gán giá trị cho các TextView
        txtTenTaiKhoan.setText(user.getUserName());
        txtEmail.setText(user.getEmail());

        return convertView;
    }
}
