package com.yun.yunimserver.util;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.yun.base.module.Bean.BaseRstCodeType;
import com.yun.base.module.Bean.RstBeanException;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: yun
 * @createdOn: 2019-07-12 13:36.
 */

public class PropUtil {

    public static <T, D> T copyProp(D org, T tag, String... ignoreProperties) {
        BeanUtil.copyProperties(org, tag,
                CopyOptions.create().setIgnoreNullValue(true).setIgnoreError(true).
                        setIgnoreProperties(ignoreProperties));

        return tag;
    }

    // 比JsonHelper.newObj快
    public static <T> T copyPropForNew(T org, Class<T> clazz, String... ignoreProperties) {
        T newItem = null;
        try {
            newItem = clazz.newInstance();

            BeanUtil.copyProperties(org, newItem,
                    CopyOptions.create().setIgnoreNullValue(true).setIgnoreError(true).
                            setIgnoreProperties(ignoreProperties));
        } catch (Exception e) {

        }

        return newItem;
    }

    public static boolean checkIsNotNegative(BigDecimal bd, boolean ep) {
        boolean isValid = false;

        if (bd == null) {
            if (ep) {
                throw new RstBeanException(BaseRstCodeType.ComErr.getCode(), "对象不存在");
            }

            return isValid;
        }

        isValid = bd.compareTo(BigDecimal.ZERO) >= 0;

        if (!isValid && ep) {
            throw new RstBeanException(BaseRstCodeType.ComErr.getCode(), "不能为负数");
        }

        return isValid;
    }

    public static boolean checkIsNotNegative(BigDecimal bd) {
        return checkIsNotNegative(bd, false);
    }

    public static Long startOrEndDate(Date date, boolean isStart) {
        SimpleDateFormat dfDay = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = dfDay.format(date);

        if (isStart) {
            dateStr += " 00:00:00";

        } else {
            dateStr += " 23:59:59";
        }

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Long sDate = null;
        try {
            sDate = df.parse(dateStr).getTime();

        } catch (ParseException e) {
            throw new RstBeanException(BaseRstCodeType.ComErr.getCode(), "time格式错误");
        }

        return sDate;
    }

    public static Long startOrEndDateByDayStr(String dayStr, boolean isStart) {
        if (isStart) {
            dayStr += " 00:00:00";

        } else {
            dayStr += " 23:59:59";
        }

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Long sDate = null;
        try {
            sDate = df.parse(dayStr).getTime();

        } catch (ParseException e) {
            throw new RstBeanException(BaseRstCodeType.ComErr.getCode(), "time格式错误");
        }

        return sDate;
    }

    public static String getEnableStr(Integer enable) {
        boolean isEb = false;

        if (enable != null && !enable.equals(0)) {
            isEb = true;
        }

        return isEb ? "启用" : "禁用";
    }
}
