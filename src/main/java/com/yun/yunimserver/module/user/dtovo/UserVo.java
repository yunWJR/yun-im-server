package com.yun.yunimserver.module.user.dtovo;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.yun.yunimserver.module.user.entity.User;
import com.yun.yunimserver.wsapi.DtoVo.ClientUserLoginVo;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: yun
 * @createdOn: 2019-07-15 13:45.
 */

@Data
@NoArgsConstructor
public class UserVo {
    @JsonUnwrapped
    private User user;

    private ClientUserLoginVo wsPara;

    public UserVo(User us, ClientUserLoginVo vo) {
        this.user = us;
        this.wsPara = vo;
    }
}
