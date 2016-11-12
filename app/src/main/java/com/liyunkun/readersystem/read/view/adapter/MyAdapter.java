package com.liyunkun.readersystem.read.view.adapter;

import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.liyunkun.readersystem.R;
import com.liyunkun.readersystem.read.module.bean.BookMark;
import com.liyunkun.readersystem.read.module.bean.Note;

import java.util.List;

/**
 * Created by liyunkun on 2016/11/4 0004.
 */
public class MyAdapter<T> extends BaseAdapter {
    private List<T> list;
    private Context context;
    private LayoutInflater inflater;
    private String type;


    public MyAdapter(List<T> list, Context context, String type) {
        this.list = list;
        this.context = context;
        this.type = type;
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
        MyAdapterHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.note_mark_lv_item, parent, false);
            holder = new MyAdapterHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (MyAdapterHolder) convertView.getTag();
        }

        if ("note".equals(type)) {
            Note note = (Note) list.get(position);
            holder.pageTitle.setText(note.getPageTitle());
            holder.content.setText(note.getContent());
            holder.createTime.setText(note.getCreateTime());
        } else if ("mark".equals(type)) {
            BookMark bookMark = (BookMark) list.get(position);
            holder.pageTitle.setText(bookMark.getPageTitle());
            holder.content.setMaxLines(5);
            holder.content.setEllipsize(TextUtils.TruncateAt.END);
            holder.content.setText(Html.fromHtml(bookMark.getContent()));
            holder.createTime.setText(bookMark.getCreateTime());
        }
        return convertView;
    }

    private class MyAdapterHolder {
        TextView pageTitle;
        TextView content;
        TextView createTime;

        public MyAdapterHolder(View itemView) {
            pageTitle = (TextView) itemView.findViewById(R.id.page_title);
            content = (TextView) itemView.findViewById(R.id.content);
            createTime = (TextView) itemView.findViewById(R.id.create_time);
        }
    }
}
