package com.yun.yunimserver.module.friend.dtovo;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
 * @createdOn: 2019-07-17 15:52.
 */

@Data
@NoArgsConstructor
public class NewFriendCheckDto {
    @NotNull
    @ApiModelProperty("id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long newFriendId;

    @NotNull
    @ApiModelProperty("pass 1-通过 0-不通过")
    private Integer pass;

    @Length(max = 100)
    @ApiModelProperty("审核理由")
    private String checkRemark;

    @JsonIgnore
    public boolean passed() {
        if (pass != null && pass.equals(1)) {
            return true;
        }

        return false;
    }
}
