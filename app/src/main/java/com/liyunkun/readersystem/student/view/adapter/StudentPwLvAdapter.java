package com.liyunkun.readersystem.student.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.liyunkun.readersystem.R;
import com.liyunkun.readersystem.student.module.bean.StudentPwLvBean;

import java.util.List;

/**
 * Created by liyunkun on 2016/10/19 0019.
 */
public class StudentPwLvAdapter extends BaseAdapter{
    private List<StudentPwLvBean> lvBeen;
    private Context context;
    private LayoutInflater inflater;

    public StudentPwLvAdapter(List<StudentPwLvBean> lvBeen, Context context) {
        this.lvBeen = lvBeen;
        this.context = context;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return lvBeen.size();
    }

    @Override
    public Object getItem(int position) {
        return lvBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            convertView=inflater.inflate(R.layout.student_home_toolbar_more_pw_lv_item,parent,false);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        StudentPwLvBean studentPwLvBean = lvBeen.get(position);
        holder.iv.setImageResource(studentPwLvBean.getImg());
        holder.tv.setText(studentPwLvBean.getContent());
        return convertView;
    }
    class ViewHolder{
        TextView tv;
        ImageView iv;

        public ViewHolder(View itemView) {
            tv=((TextView) itemView.findViewById(R.id.tv));
            iv= (ImageView) itemView.findViewById(R.id.iv);
        }
    }
}
