package com.example.app_c_truyn.Adapter;

import android.annotation.SuppressLint;
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

import de.hdodenhof.circleimageview.CircleImageView;


public class AdapterStory extends BaseAdapter{
        private final Context context;
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

        public static class ViewHolder{
            TextView txtNameStory;
            CircleImageView imageStory;
        }

        @SuppressLint({"ViewHolder", "InflateParams"})
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_story,null);

            viewHolder.txtNameStory = convertView.findViewById(R.id.textviewTentruyenNew);
            viewHolder.imageStory = convertView.findViewById(R.id.imgStory);
            convertView.setTag(viewHolder);

            Story story = (Story) getItem(position);
            viewHolder.txtNameStory.setText(story.getNameStory());

            Picasso.get().load(story.getImage()).placeholder(R.drawable.ic_load)
                    .error(R.drawable.logo_image).into(viewHolder.imageStory);
            return convertView;
        }
    }


