package com.yun.yunimserver.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: yun
 * @createdOn: 2018/7/20 13:56.
 */

public class JsonHelper {

    // region --Field

    // jackson转换工具
    private static final ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true)
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

    private static int features = SerializerFeature.config(JSON.DEFAULT_GENERATE_FEATURE, SerializerFeature.WriteEnumUsingName, false);

    // endregion

    // region --Constructor

    // endregion

    // region --static method

    // endregion   

    // region --Getter and Setter

    // endregion

    // region --Public method

    public static String toStr(Object obj) {
        String str = null;

        try {
            str = objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return str;

        // return JSON.toJSONString(obj, features);
    }

    public static <T> T toObjType(String json, TypeReference valueTypeRef) {
        // T obj = default(T); // todo
        T obj;
        try {
            obj = objectMapper.readValue(json, valueTypeRef);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return obj;

        // return JSON.parseObject(json, clazz, features);
    }

    public static <T> T toObj(String json, Class<T> clazz) {
        // T obj = default(T); // todo
        T obj;
        try {
            obj = objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return obj;

        // return JSON.parseObject(json, clazz, features);
    }

    // enum 转换失败
    public static <T> T mapToObject(Map<String, Object> map, Class<T> beanClass) {
        if (map == null)
            return null;

        try {
            T obj = beanClass.newInstance();
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                Method setter = property.getWriteMethod();
                Object mspPro = map.get(property.getName());
                if (setter != null && mspPro != null) {
                    setter.invoke(obj, mspPro);
                }
            }
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Map<String, Object> objectToMap(Object obj) throws InvocationTargetException, IllegalAccessException {
        if (obj == null)
            return null;

        Map<String, Object> map = new HashMap<String, Object>();

        BeanInfo beanInfo = null;
        try {
            beanInfo = Introspector.getBeanInfo(obj.getClass());
        } catch (IntrospectionException e) {
            e.printStackTrace();
            return null;
        }
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            String key = property.getName();
            if (key.compareToIgnoreCase("class") == 0) {
                continue;
            }
            Method getter = property.getReadMethod();
            Object value = getter != null ? getter.invoke(obj) : null;
            map.put(key, value);
        }

        return map;
    }

    // endregion

    // region --private method

    // endregion

    // region --Other

    // endregion
}
