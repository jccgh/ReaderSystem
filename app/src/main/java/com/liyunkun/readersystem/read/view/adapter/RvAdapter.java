package com.liyunkun.readersystem.read.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liyunkun.readersystem.R;
import com.liyunkun.readersystem.both.module.bean.PageBean;

import java.util.List;

/**
 * Created by liyunkun on 2016/11/1 0001.
 */
public class RvAdapter extends RecyclerView.Adapter {
    private List<PageBean> list;
    private Context context;
    private LayoutInflater inflater;
    private int currentPosition = 0;

    public RvAdapter(List<PageBean> list, Context context) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.read_rv_item, parent, false);
        return new ReadViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        currentPosition = position;
        ReadViewHolder viewHolder = (ReadViewHolder) holder;
        PageBean pageBean = list.get(position);
        viewHolder.contents.setText(Html.fromHtml(pageBean.getMessage()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    private class ReadViewHolder extends RecyclerView.ViewHolder {

        TextView contents;

        public ReadViewHolder(final View itemView) {
            super(itemView);
            contents = (TextView) itemView.findViewById(R.id.contents);
            itemView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    v.getParent().requestDisallowInterceptTouchEvent(false);
                    return true;
                }
            });
        }
    }

//    private OnItemViewClickListener onItemViewClickListener;
//
//    public void setOnItemViewClickListener(OnItemViewClickListener onItemViewClickListener) {
//        this.onItemViewClickListener = onItemViewClickListener;
//    }
//
//    public interface OnItemViewClickListener {
//        void onItemViewClick(View v, int position);
//    }
}
