package com.example.car_message.utils;

/**
 * @author : 张鑫
 * @time 2017/6/19 11:55.
 */

import com.example.car_message.base.Result;
import com.example.car_message.base.ResultList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;





/**
 * Description：统一Gson解析
 */


public class GsonUtils {


    public static final String TAG = "GsonUtil";

    /**
     * 返回加过数据部位为Bean调用
     *
     * @param json  json String
     * @param clazz data字段类型 class
     * @param <T>   data字段类型 type
     * @return 结果 data字段为bean
     */
    public static <T> Result<T> JsonToResult(String json, Class clazz) {
        Gson gson = new Gson();
        Type objectType = type(Result.class, clazz);
        Result<T> result = null;
        try {
            result = gson.fromJson(json, objectType);
        } catch (Exception e) {
            printLogError(e);
            return result;
        }
        return result;
    }

    /**
     * 返回结果数据部位为List调用
     *
     * @param json  json String
     * @param clazz data字段List bean类型 class
     * @param <T>   data字段List bean类型 type
     * @return 结果 data字段为List
     */
    public static <T> ResultList<T> JsonToResultList(String json, Class clazz) {
        Gson gson = new Gson();
        Type objectType = type(ResultList.class, clazz);
        ResultList<T> result = null;
        try {
            result = gson.fromJson(json, objectType);
        } catch (Exception e) {
            printLogError(e);
            return result;
        }
        return result;
    }


    private static ParameterizedType type(final Class raw, final Type... args) {
        return new ParameterizedType() {
            public Type getRawType() {
                return raw;
            }

            public Type[] getActualTypeArguments() {
                return args;
            }

            public Type getOwnerType() {
                return null;
            }
        };
    }

    private static void printLogError(Exception error) {
        LogUtil.e(TAG, error + "");
    }


    /**
     * javabean转成Json串
     *
     * @param bean 要转换成json串的对象
     * @return json串
     */
    public static String ObjectToJson(Object bean) {
        Gson gson = new Gson();
        String json = "";
        try {
            json = gson.toJson(bean);
        } catch (Exception e) {
            printLogError(e);
            return json;
        }
        return json;
    }


    /**
     * Json串转成JavaBean
     *
     * @param json     json串
     * @param classOfT 泛型T的class
     * @param <T>      泛型T
     * @return T Bean
     */
    public static <T> T JsonToBean(String json, Class<T> classOfT) {
        if (classOfT.getSimpleName().equals(String.class.getSimpleName())) {
            return (T) json;
        }
        Gson gson = new Gson();
        T bean = null;
        try {
            bean = gson.fromJson(json, classOfT);

        } catch (Exception e) {
            printLogError(e);
            return bean;
        }
        return bean;
    }

    /**
     * json转换成List对象
     *
     * @param json     json串
     * @param classOfT 泛型T class
     * @param <T>      泛型T
     * @return List<T> 对象
     */
    public static <T> List<T> JsonToList(String json, Class<T> classOfT) {
        Gson gson = new Gson();
        List<T> list = null;
        try {
            list = gson.fromJson(json, new TypeToken<List<T>>() {
            }.getType());
        } catch (Exception e) {
            printLogError(e);
            return list;
        }
        return list;
    }


}
