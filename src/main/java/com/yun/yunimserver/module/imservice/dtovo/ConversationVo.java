package com.yun.yunimserver.module.imservice.dtovo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yun.base.IdGenerator.LongJson.LongJsonDeserializer;
import com.yun.base.IdGenerator.LongJson.LongJsonSerializer;
import com.yun.yunimserver.module.imservice.entity.ConversationType;
import com.yun.yunimserver.module.imservice.entity.couple.ConversationCouple;
import com.yun.yunimserver.module.imservice.entity.group.ConversationGroup;
import com.yun.yunimserver.module.userservice.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author: yun
 * @createdOn: 2018/7/26 14:50.
 */

@Data
@ApiModel("对话数据")
public class ConversationVo {

    // region --Field

    @ApiModelProperty(value = "对话id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long id;

    @ApiModelProperty(value = "对话类型：ConversationType")
    private ConversationType type;

    @ApiModelProperty(value = "对话名称")
    private String name;

    @ApiModelProperty(value = "对话头像，null 则由客服端生成")
    private String avatar;

    @ApiModelProperty(value = "消息列表")
    private List<MessageVo> messageList;

    private Long lastMsgTime = 0L;

    // endregion

    // region --Constructor

    public ConversationVo() {
    }

    public ConversationVo(ConversationGroup dg) {
        this.type = ConversationType.Group;

        if (dg != null) {
            this.id = dg.getId();

            if (dg.getInfo() != null) {
                this.name = dg.getInfo().getName();
                this.avatar = dg.getInfo().getAvatar();
            }
        }
    }

    public ConversationVo(ConversationCouple cp, User tgUser) {
        this.type = ConversationType.Couple;

        if (cp != null) {
            this.id = cp.getId();

            if (tgUser != null) {
                this.name = tgUser.getDisplayName();
                this.avatar = tgUser.getUserInfo() != null ? tgUser.getUserInfo().getHeadUrl() : null;
            }
        }
    }

    // endregion

    public void setMessageList(List<MessageVo> messageList) {
        this.messageList = messageList;

        if (messageList != null && messageList.size() > 0) {
            lastMsgTime = messageList.get(0).getCreateTime();
        }
    }
}
