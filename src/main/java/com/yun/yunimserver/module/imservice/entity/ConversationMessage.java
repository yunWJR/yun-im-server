package com.yun.yunimserver.module.imservice.entity;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yun.base.IdGenerator.LongJson.LongJsonDeserializer;
import com.yun.base.IdGenerator.LongJson.LongJsonSerializer;
import com.yun.base.jpa.BaseEntity.BaseEntityWithGlIdDate;
import com.yun.yunimserver.module.imservice.dtovo.SendMessageDto;
import com.yun.yunimserver.util.JsonHelper;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import java.util.Map;

/**
 * @author: yun
 * @createdOn: 2019-07-02 14:07.
 */

@Entity
@Data
@NoArgsConstructor
public class ConversationMessage extends BaseEntityWithGlIdDate {

    @Embedded
    @JsonUnwrapped
    private ConversationMessageInfo info;

    @Column(nullable = false)
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long sendUserId;

    @Column(nullable = false)
    private Integer platformType;

    public ConversationMessage(Long sendUserId, Integer platformType, SendMessageDto dto) {
        this.sendUserId = sendUserId;

        this.platformType = platformType;

        this.info = dto.getInfo();
    }

    @JsonIgnore
    public Map gMapValue() {
        // todo 为了保持格式统一，转换两次，效率低
        String js = JsonHelper.toStr(this);

        Map maps = (Map) JSON.parse(js);
        return maps;

        // BeanMap bMap = new BeanMap(this);
        //
        // Map map = new HashMap();
        // for (Object key : bMap.keySet()) {
        //     if ("class".equals(key)) {
        //         continue;
        //     }
        //
        //     map.put(key, bMap.get(key));
        // }
        //
        // return map;
    }

    @JsonIgnore
    public String gJsonValue() {
        String js = JsonHelper.toStr(this);
        return js;
        // Map maps = (Map) JSON.parse(js);
        // return maps;
    }
}
