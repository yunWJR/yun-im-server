package com.yun.yunimserver.wsapi.DtoVo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: yun
 * @createdOn: 2019-07-15 15:12.
 */

@Data
@NoArgsConstructor
public class IgnoreUserPlatformDto {
    private String userId;

    private String platform;
}

