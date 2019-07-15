package com.yun.yunimserver.wsapi.DtoVo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: yun
 * @createdOn: 2019-07-15 10:50.
 */

@Data
@NoArgsConstructor
public class ClientUserVo {
    private String clientUserId;

    private Long createTime;

    private Long updateTime;

    private String remark;
}
