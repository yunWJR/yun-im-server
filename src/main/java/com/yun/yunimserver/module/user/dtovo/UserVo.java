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

    private String name;

    private String wsPath;

    public UserVo(User us, ClientUserLoginVo vo) {
        this(us);
        this.wsPath = vo.getWsPath();
    }

    public UserVo(User us) {
        this.user = us;
        this.name = us.getUserAcct().getName();
    }
}
