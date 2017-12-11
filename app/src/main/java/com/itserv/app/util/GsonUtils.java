package com.itserv.app.util;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.util.Arrays;
import java.util.List;

import static android.R.attr.type;

/**
 * 作者： 周作威 on 2017/4/2.
 * 邮箱： 2780450026@qq.com
 * 类描述：gson处理
 */

public class GsonUtils {
    // 将Json数据解析成相应的映射对象
    public static <T> T parseJsonWithGson(String jsonData, Class<T> type) {
        try {
            Gson gson = new Gson();
            T result = gson.fromJson(jsonData, type);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 将Json数组解析成相应的映射对象列表
    public static <T> List<T> parseJsonArrayWithGson(String json, Class<T[]> type) {
        try {
            T[] list = new Gson().fromJson(json, type);
            return Arrays.asList(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
