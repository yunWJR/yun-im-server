package com.yun.yunimserver.module.conversation.entity.group;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;

/**
 * @author: yun
 * @createdOn: 2018/7/26 13:56.
 */

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
public class ConversationGroupUserRl {
    @EmbeddedId
    @JsonUnwrapped
    private ConversationGroupUserPk pkId;

    @Column(nullable = false)
    @CreatedDate
    private Long createTime;

    @Column(nullable = false)
    @LastModifiedDate
    private Long updateTime;

    @Column
    private String remarkName;

    @Column(nullable = false)
    private Integer role = 0;

    public ConversationGroupUserRl() {
    }

    public ConversationGroupUserRl(Long chatGroup, Long userId) {
        pkId = new ConversationGroupUserPk(chatGroup, userId);
    }
}
