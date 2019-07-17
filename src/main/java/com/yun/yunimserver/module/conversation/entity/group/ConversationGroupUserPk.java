package com.yun.yunimserver.module.conversation.entity.group;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yun.base.IdGenerator.LongJson.LongJsonDeserializer;
import com.yun.base.IdGenerator.LongJson.LongJsonSerializer;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author: yun
 * @createdOn: 2018/7/27 13:53.
 */

@Data
@Embeddable
public class ConversationGroupUserPk implements Serializable {

    // region --Field

    @Column
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long groupId;

    @Column
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long userId;

    // endregion

    // region --Constructor

    public ConversationGroupUserPk() {
    }

    public ConversationGroupUserPk(Long groupId, Long userId) {
        this.groupId = groupId;
        this.userId = userId;
    }

    // endregion
}
