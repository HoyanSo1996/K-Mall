package com.omega.util;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Map;

/**
 * Class DataUtils
 *
 * @author KennySo
 * @date 2024/3/30
 */
public class DataUtils {

    public static boolean transformStringToInteger(String... parameters) {
        try {
            for (String parameter : parameters) {
                Integer.valueOf(parameter);
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean transformStringToBigDecimal(String... parameters) {
        try {
            for (String parameter : parameters) {
                new BigDecimal(parameter);
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    /**
     * 封装 BeanUtils 的 populate 方法
     */
    public static <T> T copyParamToBean(Map<String, String[]> values, T bean) {
        try {
            BeanUtils.populate(bean, values);
            return bean;

        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * copy Bean A to Bean B
     */
    public static <T, V> T copyProperties(T dest, V orig) {
        try {
            BeanUtils.copyProperties(dest, orig);
            return dest;

        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
