package com.yun.base.Util;

import com.yun.base.module.Bean.RstBeanException;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author: yun
 * @createdOn: 2019-07-02 10:39.
 */

public class JrpUtil {

    public static <T1, T2> T1 findById(JpaRepository<T1, T2> jrp, T2 itemId) {
        return findById(jrp, itemId, null);
    }

    public static <T1, T2> T1 findById(JpaRepository<T1, T2> jrp, T2 itemId, String err) {
        if (itemId == null) {
            if (err != null) {
                throw RstBeanException.RstComErrBeanWithStr(err);
            }

            return null;
        }

        Optional<T1> optItem = jrp.findById(itemId);
        if (optItem.isPresent()) {
            return optItem.get();
        }

        if (err != null) {
            throw RstBeanException.RstComErrBeanWithStr(err);
        }

        return null;
    }

    public static Sort createTimeDescSort() {
        Sort sort = new Sort(Sort.Direction.DESC, "messageCreateTime");

        return sort;
    }

    // public static <T1> T1 findById(JpaRepository<T1, Long> jrp, Long itemId) {
    //     return findById(jrp, itemId);
    // }
}
