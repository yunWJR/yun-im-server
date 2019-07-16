package com.yun.yunimserver.wsapi.DtoVo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: yun
 * @createdOn: 2019-07-15 10:50.
 */

@Data
@NoArgsConstructor
public class ClientUserLoginVo {
    private Long id;

    private String extraUserId;

    private String platform;

    private String para;

    private String sessionId;

    private String wsPath;
}
