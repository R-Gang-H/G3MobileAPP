package com.app.test;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.JsonArray;

import android.content.Context;

/**
 *  * @Description:Json工具类(json数据与其他数据类型互相转换)
 *  * @author:axin
 *  * @time:2016-10-8 下午5:03:08
 */
public class JsonUtil {
    private static JsonUtil jsonUtil = null;
    private Context context;

    private JsonUtil(Context context) {
        super();
        this.context = context;
    }

    public static JsonUtil getInstance(Context context) {
        if (jsonUtil == null) {
            jsonUtil = new JsonUtil(context);
        }
        return jsonUtil;
    }

    /**
     *  * @Description:string转为jsonobj
     *  * @param @param json
     *  * @param @return
     *  * JSONObject
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-18 下午4:22:37
     */
    public JSONObject getJsonObjByString(String json) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     *  * @Description:string转为jsonarray
     *  * @param @param json
     *  * @param @return
     *  * JSONArray
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-18 下午4:24:21
     */
    public JSONArray getJsonArrayByString(String json) {
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    /**
     *  * @Description:是否有某键
     *  * @param @param json
     *  * @param @param jsonKey
     *  * @param @return
     *  * boolean
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-18 下午3:59:04
     */
    public boolean hasString(String json, String jsonKey) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            if (jsonObject.has(jsonKey)) {
                return true;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     *  * @Description:键映射的值转为jsonobj
     *  * @param @param json
     *  * @param @param jsonKey
     *  * @param @return
     *  * JSONObject
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-18 下午4:17:45
     */
    public JSONObject getJsonObjByJsonObj(String json, String jsonKey) {
        JSONObject getJsonObject = null;
        try {
            JSONObject jsonObject = new JSONObject(json);
            getJsonObject = jsonObject.getJSONObject(jsonKey);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return getJsonObject;
    }

    /**
     *  * @Description:键映射的值转为jsonobj
     *  * @param @param json
     *  * @param @param index
     *  * @param @return
     *  * JSONObject
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-18 下午4:19:10
     */
    public JSONObject getJsonObjByJsonArray(String json, int index) {
        JSONObject getJsonObject = null;
        try {
            JSONArray jsonArray = new JSONArray(json);
            getJsonObject = jsonArray.getJSONObject(index);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return getJsonObject;
    }

    /**
     *  * @Description:键映射的值转为jsonarray
     *  * @param @param json
     *  * @param @param jsonKey
     *  * @param @return
     *  * JSONArray
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-18 下午4:19:32
     */
    public JSONArray getJsonArrayByJsonObj(String json, String jsonKey) {
        JSONArray getJsonArray = null;
        try {
            JSONObject jsonObject = new JSONObject(json);
            getJsonArray = jsonObject.getJSONArray(jsonKey);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return getJsonArray;
    }

    /**
     *  * @Description:键映射的值转为jsonarray
     *  * @param @param json
     *  * @param @param index
     *  * @param @return
     *  * JSONArray
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-18 下午4:19:54
     */
    public JSONArray getJsonArrayByJsonArray(String json, int index) {
        JSONArray getJsonArray = null;
        try {
            JSONArray jsonArray = new JSONArray(json);
            getJsonArray = jsonArray.getJSONArray(index);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return getJsonArray;
    }

    /**
     *  * @Description:jsonobj转string
     *  * @param @param jsonObject
     *  * @param @return
     *  * String
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-18 下午4:30:25
     */
    public String getStringByJsonObj(JSONObject jsonObject) {
        String string = jsonObject.toString();
        return string;
    }

    /**
     *  * @Description:jsonarray转string
     *  * @param @param jsonArray
     *  * @param @return
     *  * String
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-18 下午4:33:26
     */
    public String getStringByJsonArray(JSONArray jsonArray) {
        String string = jsonArray.toString();
        return string;
    }

    /**
     *  * @Description:jsonobj转byte[]
     *  * @param @param jsonObject
     *  * @param @return
     *  * byte[]
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-18 下午4:36:57
     */
    public byte[] getByteByJsonObj(JSONObject jsonObject) {
        byte[] byteArray = null;
        String string = jsonObject.toString();
        byteArray = string.getBytes();
        return byteArray;
    }

    /**
     *  * @Description:jsonaray转byte[]
     *  * @param @param jsonArray
     *  * @param @return
     *  * byte[]
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-18 下午4:38:48
     */
    public byte[] getByteByJsonArray(JSONArray jsonArray) {
        byte[] byteArray = null;
        String string = jsonArray.toString();
        byteArray = string.getBytes();
        return byteArray;
    }
}
