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
import com.liyunkun.readersystem.student.module.bean.BookShopLvBean;

import java.util.List;

/**
 * Created by liyunkun on 2016/10/28 0028.
 */
public class BookShopLvAdapter extends BaseAdapter {
    private List<BookShopLvBean> list;
    private Context context;
    private LayoutInflater inflater;

    public BookShopLvAdapter(List<BookShopLvBean> list, Context context) {
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
        BookShopHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.book_shop_lv_item, parent, false);
            holder = new BookShopHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder= (BookShopHolder) convertView.getTag();
        }
        holder.title.setText(list.get(position).getTitle());
        List<BookBean> bookBeanList = this.list.get(position).getList();
        return convertView;
    }

    private class BookShopHolder {
        TextView title;
        ImageView iv1_1;
        ImageView iv1_2;
        ImageView iv1_3;
        ImageView iv2_1;
        ImageView iv2_2;
        ImageView iv2_3;
        TextView tv1_1;
        TextView tv1_2;
        TextView tv1_3;
        TextView tv2_1;
        TextView tv2_2;
        TextView tv2_3;

        public BookShopHolder(View itemView) {
            title = (TextView) itemView.findViewById(R.id.tv);
            iv1_1 = (ImageView) itemView.findViewById(R.id.iv1_1);
            iv1_2 = (ImageView) itemView.findViewById(R.id.iv1_2);
            iv1_3 = (ImageView) itemView.findViewById(R.id.iv1_3);
            iv2_1 = (ImageView) itemView.findViewById(R.id.iv2_1);
            iv2_2 = (ImageView) itemView.findViewById(R.id.iv2_2);
            iv2_3 = (ImageView) itemView.findViewById(R.id.iv2_3);
            tv1_1 = (TextView) itemView.findViewById(R.id.tv1_1);
            tv1_2 = (TextView) itemView.findViewById(R.id.tv1_2);
            tv1_3 = (TextView) itemView.findViewById(R.id.tv1_3);
            tv2_1 = (TextView) itemView.findViewById(R.id.tv2_1);
            tv2_2 = (TextView) itemView.findViewById(R.id.tv2_2);
            tv2_3 = (TextView) itemView.findViewById(R.id.tv2_3);
        }
    }
}
