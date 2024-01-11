package com.example.app_c_truyn.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.app_c_truyn.Model.Story;
import com.example.app_c_truyn.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class AdapterStory extends BaseAdapter{
        private Context context;
        private ArrayList<Story> listTruyen;

        public AdapterStory(Context context, ArrayList<Story> listTruyen) {
            this.context = context;
            this.listTruyen = listTruyen;
        }

        @Override
        public int getCount() {
            return listTruyen.size();
        }

        @Override
        // i la positon
        public Object getItem(int position) {
            return listTruyen.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }
        // filter
        public void filterList(ArrayList<Story> filteredList) {
            listTruyen = filteredList;
            notifyDataSetChanged();
        }

        public class ViewHolder{
            TextView txtTenTruyen;
            ImageView imgtruyen;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.newtruyen,null);

            viewHolder.txtTenTruyen = convertView.findViewById(R.id.textviewTentruyenNew);
            viewHolder.imgtruyen = convertView.findViewById(R.id.imgNewTruyen);
            convertView.setTag(viewHolder);

            Story truyen =(Story) getItem(position);
            viewHolder.txtTenTruyen.setText(truyen.getNameStory());

            Picasso.get().load(truyen.getImage()).placeholder(R.drawable.ic_load).error(R.drawable.ic_image).into(viewHolder.imgtruyen);
            return convertView;
        }
    }


