package com.yun.yunimserver.module.friend.dtovo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yun.base.IdGenerator.LongJson.LongJsonDeserializer;
import com.yun.base.IdGenerator.LongJson.LongJsonSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @author: yun
 * @createdOn: 2019-07-17 15:24.
 */

@Data
@NoArgsConstructor
public class NewFriendDto {
    @NotNull
    @ApiModelProperty("朋友id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long friendId;

    @Length(max = 100)
    @ApiModelProperty("申请理由")
    private String addRemark;
}
