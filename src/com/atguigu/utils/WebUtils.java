package com.atguigu.utils;

import org.apache.commons.beanutils.BeanUtils;
import java.util.Map;


public class WebUtils {
    /**
     * 把Map中的值注入到对应的JavaBean属性中去
     * @param value
     * @param bean
     */
    public static <T> T copyParamToBean(Map value, T bean) {
        try {
            // 把所请求的参数注入到User对象中去
            BeanUtils.populate(bean, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    public static int parseInt(String strInt, int defaultValue) {
        try {
            return Integer.parseInt(strInt);
        } catch (Exception e) {
            // e.printStackTrace();
        }
        return defaultValue;
    }
}
