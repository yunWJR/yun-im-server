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
    private Long id;

    private String extraUserId;

    private Long createTime;

    private Long updateTime;

    private String remark;
}
