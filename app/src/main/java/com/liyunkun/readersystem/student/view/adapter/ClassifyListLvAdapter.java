package com.liyunkun.readersystem.student.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.liyunkun.readersystem.R;
import com.liyunkun.readersystem.both.module.bean.BookBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by liyunkun on 2016/10/31 0031.
 */
public class ClassifyListLvAdapter extends BaseAdapter {
    private List<BookBean> list;
    private Context context;
    private LayoutInflater inflater;

    public ClassifyListLvAdapter(List<BookBean> list, Context context) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ClassifyListViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.classify_list_lv_item, parent, false);
            holder = new ClassifyListViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ClassifyListViewHolder) convertView.getTag();
        }
        BookBean bookBean = list.get(position);
        Picasso.with(context).load(bookBean.getBookImg()).into(holder.bookImg);
        holder.bookName.setText(bookBean.getName());
        if (bookBean.getAuthor() == null || "".equals(bookBean.getAuthor())) {
            holder.author.setText("未知作者");
        } else {
            holder.author.setText(bookBean.getAuthor() + "  著");
        }
        return convertView;
    }

    private class ClassifyListViewHolder {
        ImageView bookImg;
        TextView bookName;
        TextView author;
        ImageView addImg;

        public ClassifyListViewHolder(final View itemView) {
            bookImg = (ImageView) itemView.findViewById(R.id.book_img);
            bookName = (TextView) itemView.findViewById(R.id.book_name);
            author = (TextView) itemView.findViewById(R.id.author);
            addImg = (ImageView) itemView.findViewById(R.id.add_img);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (listener != null) {
//                        listener.onItemClickListener();
//                    }
//                }
//            });
//            addImg.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    getI
//                }
//            });

        }
    }

//    public void setListener(OnListener listener) {
//        this.listener = listener;
//    }
//
//    private OnListener listener;
//    public interface OnListener {
//        void onItemClickListener(int position);
//
//        void onAddImgClickListener(int position);
//    }
}
