package com.yun.yunimserver.module.imservice.dtovo;

import com.yun.yunimserver.util.JsonHelper;
import lombok.Data;

/**
 * @author: yun
 * @createdOn: 2018-12-06 15:33.
 */

@Data
public class PushDataDto implements java.io.Serializable {

    // region --Field

    private static final long serialVersionUID = 1L;

    /**
     * 类型
     */
    private Integer type;

    /**
     * 返回数据
     */
    private Object data;

    // endregion

    // region --Constructor

    public PushDataDto() {
    }

    public PushDataDto(Object data) {
        this.data = data;
    }

    public PushDataDto(Integer type, Object data) {
        this.type = type;
        this.data = data;
    }

    // endregion

    // region --Public method

    /**
     * 转换为 string
     * @return
     */
    public String toJsonStr() {
        return JsonHelper.toStr(this);
    }

    // endregion

    // region --private method

    // endregion

    // region --Other

    // endregion
}
