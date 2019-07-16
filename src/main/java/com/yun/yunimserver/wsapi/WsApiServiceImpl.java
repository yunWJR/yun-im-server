package com.yun.yunimserver.wsapi;

import com.fasterxml.jackson.core.type.TypeReference;
import com.yun.base.module.Bean.BaseRstBean;
import com.yun.base.module.Bean.BaseRstBeanT;
import com.yun.base.module.Bean.RstBeanException;
import com.yun.yunimserver.util.JsonHelper;
import com.yun.yunimserver.wsapi.DtoVo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author: yun
 * @createdOn: 2019-07-15 10:42.
 */

@Service
public class WsApiServiceImpl {
    @Autowired
    private WsProperties wsProperties;

    @Autowired
    private RestTemplate restTemplate;

    public ClientUserVo addClientUser(ClientUserDto dto) {
        HttpHeaders headers = httpHeaders();

        HttpEntity<ClientUserDto> request = new HttpEntity<>(dto, headers);

        ResponseEntity<String> response = restTemplate.exchange(httpUrl("v1/api/clientUser/add"),
                HttpMethod.POST, request, String.class);

        BaseRstBeanT<ClientUserVo> rst = JsonHelper.toObjType(response.getBody(),
                new TypeReference<BaseRstBeanT<ClientUserVo>>() {
                });

        handleRst(rst);

        return rst.getData();
    }

    public ClientUserLoginVo clientUserLogin(String extraUserId, String platform) {
        HttpHeaders headers = httpHeaders();

        HttpEntity<String> request = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(httpUrl(
                String.format("v1/api/clientUser/login/%s/%s", extraUserId, platform)),
                HttpMethod.POST, request, String.class);

        BaseRstBeanT<ClientUserLoginVo> rst = JsonHelper.toObjType(response.getBody(),
                new TypeReference<BaseRstBeanT<ClientUserLoginVo>>() {
                });

        handleRst(rst);

        return rst.getData();
    }

    public WsConversationVo createConversation(WsConversationDto dto) {
        HttpHeaders headers = httpHeaders();

        HttpEntity<WsConversationDto> request = new HttpEntity<>(dto, headers);

        ResponseEntity<String> response = restTemplate.exchange(httpUrl("v1/api/conversation/creat"),
                HttpMethod.POST, request, String.class);

        BaseRstBeanT<WsConversationVo> rst = JsonHelper.toObjType(response.getBody(),
                new TypeReference<BaseRstBeanT<WsConversationVo>>() {
                });

        handleRst(rst);

        return rst.getData();
    }

    public String pushMessage(MessageDto dto) {
        HttpHeaders headers = httpHeaders();

        HttpEntity<MessageDto> request = new HttpEntity<>(dto, headers);

        ResponseEntity<String> response = restTemplate.exchange(httpUrl("v1/api/message/push"),
                HttpMethod.POST, request, String.class);

        BaseRstBeanT<String> rst = JsonHelper.toObjType(response.getBody(),
                new TypeReference<BaseRstBeanT<String>>() {
                });

        handleRst(rst);

        return rst.getData();
    }

    public void handleRst(BaseRstBeanT rst) {
        if (rst.isError()) {
            BaseRstBean bRst = new BaseRstBean(rst.getCode(), rst.getErrorMsg());
            bRst.setData(rst.getData());

            throw new RstBeanException(bRst);
        }
    }

    private HttpHeaders httpHeaders() {

        HttpHeaders headers = new HttpHeaders();
        headers.add("ACCESS_AUTH_DTO", wsProperties.getAccessKey());
        headers.setContentType(MediaType.APPLICATION_JSON);

        return headers;
    }

    private String httpUrl(String path) {
        String url = String.format("%s/%s", wsProperties.getHost(), path);

        return url;
    }
}
