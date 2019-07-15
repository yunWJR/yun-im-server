package com.yun.base.Util;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Component;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * The itemType Object util.
 * @author: yun
 * @createdOn: 2018 /6/8 09:57.
 */
@Component
public class ObjectUtil {

    // region --Field

    // endregion

    // region --Constructor

    // endregion

    // region --static method

    /**
     * 将空值的属性从目标实体类中复制到源实体类中
     * @param src    : 要将属性中的空值覆盖的对象(源实体类)
     * @param target :从数据库根据id查询出来的目标对象
     */
    public static void copyNonNullProperties(Object src, Object target) {
        try {
            BeanUtils.copyProperties(src, target, getNullProperties(src));
        } catch (Exception e) {

            throw e;
        }
    }

    /**
     * Copy map to obj int.
     * @param srcMap the src map
     * @param target the target
     * @return the int
     */
    public static String copyMapToObj(Map<String, Object> srcMap, Object target) {
        for (String key : srcMap.keySet()) {
            if (StringUtil.hasCtn(key)) {
                try {
                    Field field = target.getClass().getDeclaredField(key);

                    field.setAccessible(true);
                    field.set(target, srcMap.get(key));
                } catch (Exception e) {
                    return e.getMessage();
                }
            }
        }

        return null;
    }

    /**
     * 将为空的properties给找出来,然后返回出来
     * @param src
     * @return
     */
    private static String[] getNullProperties(Object src) {
        BeanWrapper srcBean = new BeanWrapperImpl(src);
        PropertyDescriptor[] pds = srcBean.getPropertyDescriptors();
        Set<String> emptyName = new HashSet<>();
        for (PropertyDescriptor p : pds) {
            Object srcValue = srcBean.getPropertyValue(p.getName());

            if (srcValue == null) emptyName.add(p.getName());
        }
        String[] result = new String[emptyName.size()];
        return emptyName.toArray(result);
    }

    // endregion

    // region --Getter and Setter

    // endregion

    // region --Public method

    // endregion

    // region --private method

    // endregion

    // region --Other

    // endregion
}
