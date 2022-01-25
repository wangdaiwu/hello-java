package com.wdw.util;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

public class BeanUtil {
    public static <T> T mapToBean(T bean, Map value) {
        try {
            BeanUtils.populate(bean, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }
}
