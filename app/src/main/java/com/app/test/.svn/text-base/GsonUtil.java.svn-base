package com.app.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import android.content.Context;

/**
 *  * @Description:Gson的封装
 *  * @author:axin
 *  * @time:2016-10-24 下午2:01:23
 */
public class GsonUtil {
    private static GsonUtil gsonUtil = null;
    Context context;

    private GsonUtil(Context context) {
        super();
        gsonUtil = gsonUtil;
        this.context = context;
    }

    public static GsonUtil getInstance(Context context) {
        if (gsonUtil == null) {
            gsonUtil = new GsonUtil(context);
        }
        return gsonUtil;
    }

    /**
     *  * @Description:Gson转string
     *  * @param @param gson
     *  * @param @return
     *  * String
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-24 下午3:47:26
     */
    public String getStringByGson(Gson gson) {
        String string = null;
        string = gson.toString();
        return string;
    }

    /**
     *  * @Description:Gson转Json
     *  * @param @param object
     *  * @param @return
     *  * String
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-24 下午3:52:40
     */
    public String getJsonByGson(Object object) {
        String jsonString = null;
        Gson gson = new Gson();
        jsonString = gson.toJson(object);
        return jsonString;
    }

    /**
     *  * @Description:jsonString转bean
     *  * @param @param jsonString
     *  * @param @param tClass
     *  * @param @return
     *  * T
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-24 下午4:24:57
     */
    public <T> T getBeanByGson(String jsonString, Class<T> tClass) {
        T t = null;
        Gson gson = new Gson();
        t = gson.fromJson(jsonString, tClass);
        return t;
    }

    /**
     *  * @Description:jsonString转list
     *  * @param @param jsonString
     *  * @param @param tClass
     *  * @param @return
     *  * List<T>
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-25 上午10:06:08
     */
    public <T> List<T> getListByGson(String jsonString, Class<T> tClass) {
        Gson gson = new Gson();
        List<T> list = new ArrayList<T>();
        JsonArray array = new JsonParser().parse(jsonString).getAsJsonArray();
        for (final JsonElement elem : array) {
            list.add(gson.fromJson(elem, tClass));
        }
        return list;
    }

    /**
     *  * @Description:jsonString转map
     *  * @param @param jsonString
     *  * @param @return
     *  * Map<String,T>
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-25 上午10:21:00
     */
    public static <T> Map<String, T> getMapByGson(String jsonString) {
        Map<String, T> map = null;
        Gson gson = new Gson();
        if (gson != null) {
            map = gson.fromJson(jsonString, new TypeToken<Map<String, T>>() {
            }.getType());
        }
        return map;
    }
}
