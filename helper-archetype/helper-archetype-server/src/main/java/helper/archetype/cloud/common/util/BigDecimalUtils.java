/*
 * 文件名称：BigDecimalUtils.java
 * 系统名称：[系统名称]
 * 模块名称：BigDecimal工具类
 * 软件版权：杭州闪宝科技有限公司.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20180530 12:10
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20180530-01         Rushing0711     M201805301210 新建文件
 ********************************************************************************/
package helper.archetype.cloud.common.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * BigDecimal工具类.
 *
 * <p>创建时间: <font style="color:#00FFFF">20180530 12:10</font><br>
 * [请在此输入功能详述]
 *
 * @author Rushing0711
 * @version 1.0.0
 * @since 1.0.0
 */
public class BigDecimalUtils {

    // 默认除法运算精度
    private static final int DEFAULT_DIV_SCALE = 10;

    // 零的精度
    private static final BigDecimal ZERO_SCALE = new BigDecimal("0.0001");

    // 默认的格式化字符样式 “#.00”  还可以是像“#.0000”
    public static final String DEFAULT_FORMAT_PATTERN = "#,##0.00";
    public static final String SMPLE_FORMAT_PATTERN = "#.00";

    public static BigDecimal add(double val, double... vals) {
        BigDecimal bdVal = BigDecimal.valueOf(val);
        for (double dbl : vals) {
            bdVal = add(bdVal, BigDecimal.valueOf(dbl));
        }
        return bdVal;
    }

    public static BigDecimal add(BigDecimal val, BigDecimal... vals) {
        if (null == val) {
            val = BigDecimal.ZERO;
        }
        if (null != vals) {
            for (BigDecimal bigDecimal : vals) {
                val = val.add(null == bigDecimal ? BigDecimal.ZERO : bigDecimal);
            }
        }
        return val;
    }

    public static BigDecimal sub(double val, double... vals) {
        BigDecimal bdVal = BigDecimal.valueOf(val);
        for (double dbl : vals) {
            bdVal = sub(bdVal, BigDecimal.valueOf(dbl));
        }
        return bdVal;
    }

    public static BigDecimal sub(BigDecimal val, BigDecimal... vals) {
        if (null == val) {
            val = BigDecimal.ZERO;
        }
        if (null != vals) {
            for (BigDecimal bigDecimal : vals) {
                val = val.subtract(null == bigDecimal ? BigDecimal.ZERO : bigDecimal);
            }
        }
        return val;
    }

    public static BigDecimal mul(double val, double... vals) {
        BigDecimal bdVal = BigDecimal.valueOf(val);
        for (double dbl : vals) {
            bdVal = mul(bdVal, BigDecimal.valueOf(dbl));
        }
        return bdVal;
    }

    public static BigDecimal mul(BigDecimal val, BigDecimal... vals) {
        if (null == val) {
            val = BigDecimal.ZERO;
            return val;
        }
        if (null != vals) {
            for (BigDecimal bigDecimal : vals) {
                if (null == bigDecimal) {
                    val = BigDecimal.ZERO;
                    break;
                }
                val = val.multiply(bigDecimal);
            }
        }
        return val;
    }

    public static BigDecimal div(double val1, double val2) {
        BigDecimal bdVal1 = BigDecimal.valueOf(val1);
        BigDecimal bdVal2 = BigDecimal.valueOf(val2);
        return div(bdVal1, bdVal2, DEFAULT_DIV_SCALE);
    }

    public static BigDecimal div(BigDecimal val1, BigDecimal val2) {
        return div(val1, val2, DEFAULT_DIV_SCALE);
    }

    public static BigDecimal div(double val1, double val2, int scale) {
        BigDecimal bdVal1 = BigDecimal.valueOf(val1);
        BigDecimal bdVal2 = BigDecimal.valueOf(val2);
        return div(bdVal1, bdVal2, scale);
    }

    public static BigDecimal div(BigDecimal val1, BigDecimal val2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        if (null == val2 || val2.compareTo(BigDecimal.ZERO) == 0) {
            throw new IllegalArgumentException("the divisor must not be zero");
        }
        if (null == val1) {
            val1 = BigDecimal.ZERO;
            return val1;
        }
        val1 = val1.divide(val2, scale, BigDecimal.ROUND_HALF_UP);
        return val1;
    }

    public static BigDecimal max(double val, double... vals) {
        BigDecimal bdVal = BigDecimal.valueOf(val);
        for (double dbl : vals) {
            bdVal = max(bdVal, BigDecimal.valueOf(dbl));
        }
        return bdVal;
    }

    public static BigDecimal max(BigDecimal val, BigDecimal... vals) {
        if (null == val) {
            val = BigDecimal.ZERO;
        }
        if (null != vals) {
            for (BigDecimal bigDecimal : vals) {
                val = val.max(null == bigDecimal ? BigDecimal.ZERO : bigDecimal);
            }
        }
        return val;
    }

    public static BigDecimal min(double val, double... vals) {
        BigDecimal bdVal = BigDecimal.valueOf(val);
        for (double dbl : vals) {
            bdVal = min(bdVal, BigDecimal.valueOf(dbl));
        }
        return bdVal;
    }

    public static BigDecimal min(BigDecimal val, BigDecimal... vals) {
        if (null == val) {
            val = BigDecimal.ZERO;
        }
        if (null != vals) {
            for (BigDecimal bigDecimal : vals) {
                val = val.min(null == bigDecimal ? BigDecimal.ZERO : bigDecimal);
            }
        }
        return val;
    }

    public static int compateTo(double val1, double val2) {
        BigDecimal bdVal1 = BigDecimal.valueOf(val1);
        BigDecimal bdVal2 = BigDecimal.valueOf(val2);
        int compareVal = bdVal1.compareTo(bdVal2);
        if (BigDecimal.valueOf(Math.abs(sub(val1, val2).doubleValue())).compareTo(ZERO_SCALE) < 0) {
            compareVal = 0;
        }
        return compareVal;
    }

    public static String format(double val) {
        BigDecimal bdVal = BigDecimal.valueOf(val);
        return format(bdVal);
    }

    /**
     * 格式化金额数据.
     *
     * <p>创建时间: <font style="color:#00FFFF">20180531 16:00</font><br>
     *
     * <ul>
     *   <li><code>format(123456.7, "#.00")->123456.70</code>
     *   <li><code>format(123456.7, "#.0000")->123456.7000</code>
     *   <li><code>format(123456.7, "#,##0.00")->123,456.70</code>
     * </ul>
     *
     * @param val - 金额数据
     * @param pattern - 模式
     * @return java.lang.String
     * @author Rushing0711
     * @since 1.0.0
     */
    public static String format(double val, String pattern) {
        BigDecimal bdVal = BigDecimal.valueOf(val);
        return format(bdVal, pattern);
    }

    public static String format(BigDecimal val) {
        return format(val, DEFAULT_FORMAT_PATTERN);
    }

    public static String format(BigDecimal val, String pattern) {
        return new DecimalFormat(pattern).format(val);
    }

    public static BigDecimal round(double val, int scale) {
        return div(val, 1, scale);
    }
}
