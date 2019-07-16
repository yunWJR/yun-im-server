package com.yun.yunimserver.wsapi.DtoVo;

import com.yun.yunimserver.module.imservice.entity.ConversationType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author: yun
 * @createdOn: 2019-07-15 13:27.
 */

@Data
@NoArgsConstructor
public class WsConversationDto {
    @NotBlank
    @Length(max = 200)
    private String extraCvsId;

    private List<String> extraUserId;

    private String remark;

    public static WsConversationDto newItem(ConversationType type, Long cvId, List<String> extraUserId) {
        WsConversationDto dto = new WsConversationDto();

        String extraId = String.format("%s_%s", type.getType(), cvId);
        dto.setExtraCvsId(extraId);

        dto.setExtraUserId(extraUserId);

        return dto;
    }
}
