package com.yun.base.Util;

import java.util.*;

/**
 * The itemType List util.
 * @author: yun
 * @createdOn: 2018 /8/3 16:44.
 */
public class ListUtil {
    /**
     * 删除ArrayList中重复元素
     * @param list the list
     * @return the list
     */
    public static List removeDuplicate(List list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = list.size() - 1; j > i; j--) {
                if (list.get(j).equals(list.get(i))) {
                    list.remove(j);
                }
            }
        }

        return list;
    }

    /**
     * 删除ArrayList中重复元素,add进去顺序就变了不考虑顺序的话可以使用
     * 通过HashSet剔除
     * @param list the list
     * @return the list
     */
    public static List removeDuplicateHash(List list) {
        HashSet h = new HashSet(list);
        list.clear();
        list.addAll(h);

        return list;
    }

    /**
     * 删除ArrayList中重复元素，保持顺序
     * 删除ArrayList中重复元素，保持顺序
     * @param list the list
     * @return the list
     */
    public static List removeDuplicateWithOrder(List list) {
        Set set = new HashSet();
        List newList = new ArrayList();
        for (Iterator iter = list.iterator(); iter.hasNext(); ) {
            Object element = iter.next();
            if (set.add(element))
                newList.add(element);
        }
        list.clear();
        list.addAll(newList);

        return list;
    }
}