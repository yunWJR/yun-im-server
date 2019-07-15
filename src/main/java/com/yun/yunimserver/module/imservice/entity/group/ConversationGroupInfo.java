package com.yun.yunimserver.module.imservice.entity.group;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author: yun
 * @createdOn: 2019-07-02 14:24.
 */

@Embeddable
@ApiModel("群聊内容")
@Data
@NoArgsConstructor
public class ConversationGroupInfo {

    @Column
    @ApiModelProperty("群名称")
    @Length(min = 0, max = 100)
    private String name;

    @Column
    @ApiModelProperty("群描述")
    @Length(min = 0, max = 100)
    private String descp;

    @Column
    @ApiModelProperty("群头像")
    @Length(min = 0, max = 100)
    private String avatar;
}