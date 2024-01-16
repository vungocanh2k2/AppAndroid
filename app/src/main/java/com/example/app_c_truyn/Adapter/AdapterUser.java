package com.example.app_c_truyn.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_c_truyn.Admin.User.EditUserActivity;
import com.example.app_c_truyn.Database.DatabaseStory;
import com.example.app_c_truyn.Model.User;
import com.example.app_c_truyn.R;

import java.util.List;

public class AdapterUser extends RecyclerView.Adapter<AdapterUser.UserViewHolder>{
    private final List<User> userList;

    public AdapterUser(List<User> userList) {
        this.userList = userList;
    }

    @NonNull
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        // Lấy thông tin người dùng tại vị trí position trong danh sách userList
        User user = userList.get(position);
        // Thiết lập các TextView trong UserViewHolder hiển thị thông tin của người dùng
        holder.textViewUsername.setText(user.getUserName());
        holder.textViewEmail.setText(user.getEmail());
        holder.textViewPassword.setText(user.getPassWord());

        // Xử lý sự kiện khi người dùng click vào nút Edit
        holder.buttonEdit.setOnClickListener(v -> {
            Context context = holder.itemView.getContext();
            Toast.makeText(context, "Sửa tài khoản " + user.getUserName(), Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(context, EditUserActivity.class);
            intent.putExtra("userId", user.getId());
            context.startActivity(intent);
        });

        // Xử lý sự kiện khi người dùng click vào nút Delete
        holder.buttonDelete.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(holder.itemView.getContext());
            builder.setMessage("Bạn có chắc chắn muốn xóa người dùng này không?");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Xóa người dùng khỏi cơ sở dữ liệu và danh sách userList
                    int pos = holder.getAdapterPosition();
                    int userId = userList.get(pos).getId();
                    DatabaseStory databaseStory = new DatabaseStory(holder.itemView.getContext());
                    databaseStory.deleteUser(userId);
                    userList.remove(pos);
                    // Cập nhật danh sách hiển thị trên RecyclerView
                    notifyItemRemoved(pos);
                }
            });
            builder.setNegativeButton("Cancel", null);
            builder.show();
        });

        // Kiểm tra xem tài khoản hiện tại có phải là tài khoản đầu tiên trong danh sách không
        boolean isFirstUser = (position == 0);

        // Ẩn hoặc hiển thị nút xóa tùy thuộc vào giá trị của biến isFirstUser
        if (isFirstUser) {
            holder.buttonDelete.setVisibility(View.INVISIBLE);
        } else {
            holder.buttonDelete.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        //UserViewHolder đại diện cho một item view trong RecyclerView
        TextView textViewUsername;
        TextView textViewEmail;
        TextView textViewPassword;
        ImageButton buttonEdit;
        ImageButton buttonDelete;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewUsername = itemView.findViewById(R.id.textViewUsername);
            textViewEmail = itemView.findViewById(R.id.textViewEmail);
            textViewPassword = itemView.findViewById(R.id.textViewPassword);
            buttonEdit = itemView.findViewById(R.id.buttonEdit);
            buttonDelete = itemView.findViewById(R.id.buttonDelete);
        }
    }
}
