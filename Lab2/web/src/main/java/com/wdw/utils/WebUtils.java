package com.wdw.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

public class WebUtils {
    public static <T> T copyParamToBean(T bean, Map value) {
        try {
            BeanUtils.populate(bean, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    public static Integer parseInt(String strInt) {
        try {
            return Integer.parseInt(strInt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
