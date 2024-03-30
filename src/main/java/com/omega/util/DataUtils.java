package com.omega.util;

import java.math.BigDecimal;

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
}
