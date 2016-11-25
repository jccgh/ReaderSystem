package com.liyunkun.readersystem.both.view.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by liyunkun on 2016/10/13 0013.
 * 引导界面中viewpager的适配器
 */
public class LoadingVpAdapter extends PagerAdapter {
    private int[] img;
    private Context context;

    public LoadingVpAdapter(int[] img, Context context) {
        this.img = img;
        this.context = context;
    }

    @Override
    public int getCount() {
        return img.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView iv = new ImageView(context);
        iv.setImageResource(img[position]);
        iv.setPadding(50,50,50,50);
        container.addView(iv);
        return iv;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
