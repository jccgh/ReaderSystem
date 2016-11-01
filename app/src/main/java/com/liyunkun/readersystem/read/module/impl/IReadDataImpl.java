package com.liyunkun.readersystem.read.module.impl;

import com.liyunkun.readersystem.both.module.bean.PageBean;
import com.liyunkun.readersystem.read.module.intf.IReadCallBack;
import com.liyunkun.readersystem.read.module.intf.IReadData;
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
 * Created by liyunkun on 2016/11/1 0001.
 */
public class IReadDataImpl implements IReadData {
    @Override
    public void getData(int bookId, final IReadCallBack callBack) {
        Request request = new Request.Builder()
                .url(String.format(NetUtils.bookContentsUrl, bookId))
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
                    List<PageBean> list = pauseJson(json);
                    callBack.onSuccessful(list);
                }
            }
        });
    }

    private List<PageBean> pauseJson(String json) {
        List<PageBean> list = new ArrayList<>();
        try {
            JSONObject object = new JSONObject(json);
            int classId = object.optInt("bookclass");
            int bookId = object.optInt("id");
            JSONArray array = object.optJSONArray("list");
            for (int i = 0; i < array.length(); i++) {
                JSONObject arrayObject = array.optJSONObject(i);
                String title = arrayObject.optString("title");
                String message = arrayObject.optString("message");
                int pageId = arrayObject.optInt("id");
                list.add(new PageBean(pageId, message, title, classId, bookId));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}
