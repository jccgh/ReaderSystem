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
import com.liyunkun.readersystem.utils.MyConstants;

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
        switch (MyConstants.reading_bg) {
            case 0://default
               viewHolder.contents.setTextColor(context.getResources().getColor(R.color.read_tv_color_default));
                break;
            case 1://eye
                viewHolder.contents.setTextColor(context.getResources().getColor(R.color.read_tv_color_eye));
                break;
            case 2://kraft
                viewHolder.contents.setTextColor(context.getResources().getColor(R.color.read_tv_color_kraft));
                break;
            case 3://night1
                viewHolder.contents.setTextColor(context.getResources().getColor(R.color.read_tv_color_night1));
                break;
            case 4://night2
                viewHolder.contents.setTextColor(context.getResources().getColor(R.color.read_tv_color_night2));
                break;
            case 5://powerless
                viewHolder.contents.setTextColor(context.getResources().getColor(R.color.read_tv_color_powerless));
                break;
            case 6://soft
                viewHolder.contents.setTextColor(context.getResources().getColor(R.color.read_tv_color_soft));
                break;
            case 7://4
                viewHolder.contents.setTextColor(context.getResources().getColor(R.color.read_tv_color_4));
                break;
            case 8://5
                viewHolder.contents.setTextColor(context.getResources().getColor(R.color.read_tv_color_5));
                break;
        }
        viewHolder.contents.setTextSize(MyConstants.default_text_size);
        viewHolder.contents.setLineSpacing(50, MyConstants.line_height);
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
