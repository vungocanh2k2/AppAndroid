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

public class AdapterUser extends RecyclerView.Adapter<AdapterUser.UserViewHolder> {
    private final List<User> userList;
    private DatabaseStory db;

    public AdapterUser(List<User> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = userList.get(position);
        holder.textViewUsername.setText(user.getUserName());
        holder.textViewEmail.setText(user.getEmail());
        holder.textViewPassword.setText(user.getPassWord());

        holder.buttonMore.setOnClickListener(v -> {
            Context context = holder.itemView.getContext();
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Chức năng");
            String[] options = {"Sửa", "Xoá"};
            builder.setItems(options, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (which == 0) {
                        Toast.makeText(context, "Sửa user: " + user.getUserName(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context, EditUserActivity.class);
                        intent.putExtra("userId", user.getId());
                        context.startActivity(intent);
                    } else if (which == 1) {
                        AlertDialog.Builder confirmDeleteDialog = new AlertDialog.Builder(context);
                        confirmDeleteDialog.setMessage("Bạn có chắc chắn muốn xóa người dùng này?");
                        confirmDeleteDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int pos = holder.getAdapterPosition();
                                int userId = userList.get(pos).getId();
                                db = new DatabaseStory(context);
                                db.deleteUser(userId);
                                userList.remove(pos);
                                notifyItemRemoved(pos);
                            }
                        });
                        confirmDeleteDialog.setNegativeButton("No", null);
                        confirmDeleteDialog.show();
                    }
                }
            });
            builder.show();
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView textViewUsername;
        TextView textViewEmail;
        TextView textViewPassword;
        ImageButton buttonMore;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewUsername = itemView.findViewById(R.id.textViewUsername);
            textViewEmail = itemView.findViewById(R.id.textViewEmail);
            textViewPassword = itemView.findViewById(R.id.textViewPassword);
            buttonMore = itemView.findViewById(R.id.buttonEdit);
        }
    }
}