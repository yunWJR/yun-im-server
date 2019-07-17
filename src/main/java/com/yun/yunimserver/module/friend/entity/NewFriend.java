package com.yun.yunimserver.module.friend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yun.base.IdGenerator.LongJson.LongJsonDeserializer;
import com.yun.base.IdGenerator.LongJson.LongJsonSerializer;
import com.yun.yunimserver.module.friend.dtovo.FriendStatusType;
import com.yun.yunimserver.module.friend.dtovo.NewFriendCheckDto;
import com.yun.yunimserver.module.friend.dtovo.NewFriendDto;
import com.yun.yunimserver.module.friend.dtovo.UserStatusType;
import com.yun.yunimserver.module.user.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * @author: yun
 * @createdOn: 2019-07-17 13:48.
 */

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@ApiModel("新的朋友")
public class NewFriend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long id;

    @Column(nullable = false)
    @CreatedDate
    private Long createTime;

    @Column(nullable = false)
    @LastModifiedDate
    private Long updateTime;

    @Column(nullable = false)
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long userId;

    @Column(nullable = false)
    @ApiModelProperty(UserStatusType.des)
    private Integer userStatus;

    @Column(nullable = false)
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long friendId;

    @Column(nullable = false)
    @ApiModelProperty(FriendStatusType.des)
    private Integer friendStatus;

    @Column(nullable = false)
    private String userFriendId;

    @Column
    @Length(max = 100)
    @ApiModelProperty("申请理由")
    private String addRemark;

    @Column
    @Length(max = 100)
    @ApiModelProperty("审核理由")
    private String checkRemark;

    @Column()
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long conversationId;

    public NewFriend(User lgUser, NewFriendDto dto) {
        userId = lgUser.getId();
        userStatus = UserStatusType.WaitCheck.getType();

        friendId = dto.getFriendId();
        friendStatus = FriendStatusType.WaitCheck.getType();

        userFriendId = userId + "_" + friendId;

        addRemark = dto.getAddRemark();

        createUserFriendId();
    }

    @JsonIgnore
    public void updateByCheck(NewFriendCheckDto dto) {
        if (dto.passed()) {
            userStatus = UserStatusType.AddSuc.getType();
            friendStatus = FriendStatusType.AddSuc.getType();
        } else {
            userStatus = UserStatusType.Reject.getType();
            friendStatus = FriendStatusType.Reject.getType();
        }

        checkRemark = dto.getCheckRemark();
    }

    @JsonIgnore
    private void createUserFriendId() {
        if (userId < friendId) {
            userFriendId = userId + "_" + friendId;
        } else {
            userFriendId = friendId + "_" + userId;
        }
    }

}
