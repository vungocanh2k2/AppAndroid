package com.example.app_c_truyn.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.app_c_truyn.Model.Category;
import com.example.app_c_truyn.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterCategory extends BaseAdapter {

    private final Context context;
    private final int layout;
    private final List<Category> categoryList;

    public AdapterCategory(Context context, int layout, List<Category> categoryList) {
        this.context = context;
        this.layout = layout;
        this.categoryList = categoryList;
    }

    @Override
    public int getCount() {
        return categoryList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(layout,null);

        ImageView img = (ImageView) convertView.findViewById(R.id.imgCate);

        TextView txt = (TextView) convertView.findViewById(R.id.nameCate);

        Category cm = categoryList.get(position);
        txt.setText(cm.getNameCategory());
        Picasso.get().load(cm.getImageCategory())
                .placeholder(R.drawable.ic_load)
                .error(R.drawable.ic_image).into(img);

        return convertView;
    }
}
