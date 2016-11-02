package com.liyunkun.readersystem.student.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.liyunkun.readersystem.R;
import com.liyunkun.readersystem.both.module.bean.MyBook;
import com.liyunkun.readersystem.utils.MyConstants;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by liyunkun on 2016/11/1 0001.
 */
public class MyBookFgRvAdapter extends RecyclerView.Adapter {
    private int mode;
    private List<MyBook> list;
    private Context context;
    private LayoutInflater inflater;

    public MyBookFgRvAdapter(int mode, List<MyBook> list, Context context) {
        this.mode = mode;
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (MyConstants.BOOK_MODE == mode) {
            View view = inflater.inflate(R.layout.my_book_fg_rv_item_book_mode, parent, false);
            return new BookModeHolder(view);
        } else if (MyConstants.LIST_MODE == mode) {
            View view = inflater.inflate(R.layout.my_book_fg_rv_item_list_mode, parent, false);
            return new ListModeHolder(view);
        } else {
            return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder != null) {
            MyBook myBook = list.get(position);
            if (MyConstants.BOOK_MODE == mode) {
                BookModeHolder bookModeHolder = (BookModeHolder) holder;
                Picasso.with(context).load(myBook.getBookImg()).into(bookModeHolder.bookImg);
                bookModeHolder.bookName.setText(myBook.getName());
            } else if (MyConstants.LIST_MODE == mode) {
                ListModeHolder listModeHolder = (ListModeHolder) holder;
                Picasso.with(context).load(myBook.getBookImg()).into(listModeHolder.bookImg);
                listModeHolder.bookName.setText(myBook.getName());
                listModeHolder.author.setText(myBook.getAuthor());
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private class BookModeHolder extends RecyclerView.ViewHolder {

        ImageView bookImg;
        TextView bookName;

        public BookModeHolder(View itemView) {
            super(itemView);
            bookImg = (ImageView) itemView.findViewById(R.id.book_img);
            bookName = (TextView) itemView.findViewById(R.id.book_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemViewListener != null) {
                        onItemViewListener.onItemClickListener(v, getLayoutPosition());
                    }
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (onItemViewListener != null) {
                        onItemViewListener.onItemLongClickListener(v,getLayoutPosition());
                    }
                    return true;
                }
            });
        }
    }

    private class ListModeHolder extends RecyclerView.ViewHolder {
        ImageView bookImg;
        TextView bookName;
        TextView author;

        public ListModeHolder(View itemView) {
            super(itemView);
            bookImg = (ImageView) itemView.findViewById(R.id.book_img);
            bookName = (TextView) itemView.findViewById(R.id.book_name);
            author = (TextView) itemView.findViewById(R.id.author);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemViewListener != null) {
                        onItemViewListener.onItemClickListener(v, getLayoutPosition());
                    }
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (onItemViewListener != null) {
                        onItemViewListener.onItemLongClickListener(v,getLayoutPosition());
                    }
                    return true;
                }
            });
        }
    }

    public void setOnItemViewListener(OnItemViewListener onItemViewListener) {
        this.onItemViewListener = onItemViewListener;
    }

    private OnItemViewListener onItemViewListener;

    public interface OnItemViewListener {
        void onItemClickListener(View v, int position);
        void onItemLongClickListener(View v, int position);
    }
}
