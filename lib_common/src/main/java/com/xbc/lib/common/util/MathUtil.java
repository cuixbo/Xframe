package com.xbc.lib.common.util;

/**
 * @author xiaobo.cui
 */
public class MathUtil {

    public static double getMax2Decimal(double value) {
        String ret = String.format("%.2f", value);
        return Double.valueOf(ret);
    }

}
