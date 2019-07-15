package com.yun.yunimserver.wsapi.DtoVo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author: yun
 * @createdOn: 2019-07-15 13:30.
 */

@Data
@NoArgsConstructor
public class MessageDto {
    @NotBlank
    @Length(max = 200)
    private String clientGroupId;

    private String contentJson;

    private List<IgnoreUserPlatformDto> ignoreList;
}
