package com.yun.yunimserver.module.conversation.dtovo;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yun.base.IdGenerator.LongJson.LongJsonDeserializer;
import com.yun.base.IdGenerator.LongJson.LongJsonSerializer;
import com.yun.yunimserver.module.conversation.entity.group.ConversationGroup;
import com.yun.yunimserver.module.conversation.entity.group.ConversationGroupInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: yun
 * @createdOn: 2019-07-08 09:20.
 */

@Data
@NoArgsConstructor
public class GroupInfoVo {
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long id;

    @JsonUnwrapped
    private ConversationGroupInfo info;

    @ApiModelProperty("最多成员数")
    private Integer maxMeb = 100;

    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long adminUserId;

    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long createUserId;

    private List<GroupUserVo> userList;

    public GroupInfoVo(ConversationGroup group) {
        if (group != null) {
            this.id = group.getId();
            this.info = group.getInfo();
            this.adminUserId = group.getAdminUserId();
            this.createUserId = group.getCreateUserId();
            this.maxMeb = group.getMaxMeb();
        }
    }
}
