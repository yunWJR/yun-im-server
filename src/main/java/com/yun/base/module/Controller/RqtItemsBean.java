package com.yun.base.module.Controller;

import java.util.List;

/**
 * @Description:
 * @Author: yun
 * @CreatedOn: 2018/5/28 18:33.
 */
public class RqtItemsBean<T> {
    private List<T> items;

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
