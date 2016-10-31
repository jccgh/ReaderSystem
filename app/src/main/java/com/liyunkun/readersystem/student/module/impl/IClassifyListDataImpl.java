package com.liyunkun.readersystem.student.module.impl;

import com.liyunkun.readersystem.both.module.bean.BookBean;
import com.liyunkun.readersystem.student.module.intf.IBookCallBack;
import com.liyunkun.readersystem.student.module.intf.IClassifyListData;
import com.liyunkun.readersystem.utils.NetUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by liyunkun on 2016/10/31 0031.
 */
public class IClassifyListDataImpl implements IClassifyListData {
    @Override
    public void getData(int classId, int totalCount, final IBookCallBack callBack) {
        Request request = new Request.Builder()
                .url(String.format(NetUtils.bookListUrl, classId, totalCount))
                .build();
        NetUtils.client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onFailed(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String json = response.body().string();
                    List<BookBean> list = pauseJson(json);
                    callBack.onSuccessful(list);
                }
            }
        });
    }

    private List<BookBean> pauseJson(String json) {
        List<BookBean> list=new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONObject(json).optJSONArray("list");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.optJSONObject(i);
                String author = object.optString("author");
                int classId = object.optInt("bookclass");
                int rcount = object.optInt("rcount");
                int count = object.optInt("count");
                int fcount = object.optInt("fcount");
                int id = object.optInt("id");
                String img = "http://tnfs.tngou.net/image"+object.optString("img");
                String name = object.optString("name");
                String summary = object.optString("summary");
                list.add(new BookBean(name,img,id,author,null,summary,count,fcount,rcount,classId));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}
