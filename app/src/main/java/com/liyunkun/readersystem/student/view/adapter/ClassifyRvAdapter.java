package com.liyunkun.readersystem.student.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.liyunkun.readersystem.R;
import com.liyunkun.readersystem.both.module.bean.BookClassBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by liyunkun on 2016/10/29 0029.
 */
public class ClassifyRvAdapter extends RecyclerView.Adapter{
    private List<BookClassBean> list;
    private Context context;
    private LayoutInflater inflater;

    public ClassifyRvAdapter(List<BookClassBean> list, Context context) {
        this.list = list;
        this.context = context;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.classify_rv_item, parent, false);
        return new ClassifyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ClassifyViewHolder viewHolder = (ClassifyViewHolder) holder;
        BookClassBean bookClassBean = list.get(position);
        viewHolder.title.setText(bookClassBean.getType());
        viewHolder.count.setText(bookClassBean.getTotalBook()+"本");
        Picasso.with(context).load(bookClassBean.getImg()).into(viewHolder.iv);
    }
    private class ClassifyViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        TextView count;
        ImageView iv;
        public ClassifyViewHolder(View itemView) {
            super(itemView);
            title= (TextView) itemView.findViewById(R.id.title);
            count= (TextView) itemView.findViewById(R.id.count);
            iv= (ImageView) itemView.findViewById(R.id.iv);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemListener != null) {
                        onItemListener.onClick(list.get(getLayoutPosition()));
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setOnItemListener(OnItemListener onItemListener) {
        this.onItemListener = onItemListener;
    }

    private OnItemListener onItemListener;
    public interface  OnItemListener{
        void onClick(BookClassBean bookClassBean);
    }
}
