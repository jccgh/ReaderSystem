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
import com.squareup.picasso.Picasso;

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
    public View getView(final int position, View convertView, ViewGroup parent) {
        BookShopHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.book_shop_lv_item, parent, false);
            holder = new BookShopHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (BookShopHolder) convertView.getTag();
        }
        holder.title.setText(list.get(position).getTitle());
        List<BookBean> bookBeanList = this.list.get(position).getList();
        holder.tv1_1.setText(bookBeanList.get(0).getName());
        holder.tv1_2.setText(bookBeanList.get(1).getName());
        holder.tv1_3.setText(bookBeanList.get(2).getName());
        holder.tv2_1.setText(bookBeanList.get(3).getName());
        holder.tv2_2.setText(bookBeanList.get(4).getName());
        holder.tv2_3.setText(bookBeanList.get(5).getName());
        Picasso.with(context).load(bookBeanList.get(0).getBookImg()).into(holder.iv1_1);
        Picasso.with(context).load(bookBeanList.get(1).getBookImg()).into(holder.iv1_2);
        Picasso.with(context).load(bookBeanList.get(2).getBookImg()).into(holder.iv1_3);
        Picasso.with(context).load(bookBeanList.get(3).getBookImg()).into(holder.iv2_1);
        Picasso.with(context).load(bookBeanList.get(4).getBookImg()).into(holder.iv2_2);
        Picasso.with(context).load(bookBeanList.get(5).getBookImg()).into(holder.iv2_3);
        holder.iv1_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClick != null) {
                    onClick.onClick(v, position,0);
                }
            }
        });
        holder.iv1_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClick != null) {
                    onClick.onClick(v, position,1);
                }
            }
        });
        holder.iv1_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClick != null) {
                    onClick.onClick(v, position,2);
                }
            }
        });
        holder.iv2_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClick != null) {
                    onClick.onClick(v, position,3);
                }
            }
        });
        holder.iv2_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClick != null) {
                    onClick.onClick(v, position,4);
                }
            }
        });
        holder.iv2_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClick != null) {
                    onClick.onClick(v, position,5);
                }
            }
        });
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

    private onClick onClick;

    public void setOnClick(BookShopLvAdapter.onClick onClick) {
        this.onClick = onClick;
    }

    public interface onClick {
        void onClick(View v, int position,int childPosition);
    }
}
