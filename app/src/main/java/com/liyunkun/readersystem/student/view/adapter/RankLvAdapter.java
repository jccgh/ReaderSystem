package com.liyunkun.readersystem.student.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.liyunkun.readersystem.R;
import com.liyunkun.readersystem.student.module.bean.RankLvBean;

import java.util.List;

/**
 * Created by liyunkun on 2016/10/28 0028.
 */
public class RankLvAdapter extends BaseAdapter{
    private List<RankLvBean> list;
    private Context context;
    private LayoutInflater inflater;

    public RankLvAdapter(List<RankLvBean> list, Context context) {
        this.list = list;
        this.context = context;
        inflater=LayoutInflater.from(context);
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
        RankHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.rank_lv_item, parent, false);
            holder = new RankHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder= (RankHolder) convertView.getTag();
        }
        holder.iv.setImageResource(list.get(position).getImgId());
        holder.tv.setText(list.get(position).getContents());
        return convertView;
    }
    private class RankHolder{
        ImageView iv;
        TextView tv;

        public RankHolder(View itemView) {
            iv= (ImageView) itemView.findViewById(R.id.iv);
            tv= (TextView) itemView.findViewById(R.id.tv);
        }
    }
}
