package com.yun.yunimserver.module.common.qiniu;

import com.alibaba.fastjson.JSONObject;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: yun
 * @createdOn: 2018/12/17 11:07.
 */

@Service
public class QiNiuServiceImpl implements QiNiuService {

    // region --Field

    @Value("${qiNiu.bucketName}")
    private String bucketName;

    @Value("${qiNiu.qnCdnUrl}")
    private String qnCdnUrl;

    @Value("${qiNiu.accessKey}")
    private String accessKey;

    @Value("${qiNiu.secretKey}")
    private String secretKey;

    private Auth auth;

    // endregion

    // region --Public method

    @Override
    public String getUpToken() {
        Map<String, Object> map = new HashMap<>();
        map.put("state", "SUCCESS");
        map.put("url", qnCdnUrl + "/$(etag)");
        map.put("size", "$(fsize)");
        map.put("title", "$(fname)");
        map.put("original", "$(fname)");

        StringMap stringMap = new StringMap();
        stringMap.put("returnBody", JSONObject.toJSONString(map));

        return qiNiuAuth().uploadToken(bucketName, null, 100, stringMap);
    }

    // endregion

    // region --private method

    private Auth qiNiuAuth() {
        if (auth == null) {
            auth = Auth.create(accessKey, secretKey);
        }

        return auth;
    }

    // endregion

}
