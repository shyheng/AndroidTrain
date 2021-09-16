package com.hhh.dingdingtask.tools;

import com.google.gson.Gson;

public class JsonUtil {
    /**
     * 将对象转换成json字符串
     * （此方法会引导 某些手机方法不再向下进行了。）
     *
     * @param obj 要转换json的对象
     * @return 对应的json字符串，失败时为null
     */
    public static String toJson(Object obj) {
        try {
            return new Gson().toJson(obj);
        } catch (Exception e) {
//            JJBLog.e(JsonManager.class.getName(), "e-->" + e.getMessage());
            return null;
        }
    }

    /**
     * 将json字符串转换成对象
     *
     * @param jsonStr 需要解析对象的json字符串
     * @param cls     要解析对象类型的.class
     * @param <T>     请求与返回是相关的对象类型
     * @return 对应的对象，失败时返回null
     */
    public static <T> T fromJson(String jsonStr, Class<T> cls) {
        try {
            return new Gson().fromJson(jsonStr, cls);
        } catch (Exception e) {
            return null;
        }
    }
}
