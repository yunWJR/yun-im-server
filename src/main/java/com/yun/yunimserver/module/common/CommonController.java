package com.yun.yunimserver.module.common;

import com.yun.base.module.Bean.BaseRstBeanT;
import com.yun.yunimserver.config.NoNeedAccessAuthentication;
import com.yun.yunimserver.module.common.qiniu.QiNiuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: yun
 * @createdOn: 2018/12/17 11:20.
 */

@Slf4j
@RestController
@RequestMapping(value = "api/v1/common")
@Api(tags = "00-00-通用", description = "")
public class CommonController {

    // region --Field

    @Autowired
    private QiNiuService qiNiuService;

    // endregion

    // region --Constructor

    // endregion

    @ApiOperation(value = "获取七牛上传 token", notes = "")
    @RequestMapping(value = {"/getQiNiuToken"}, method = RequestMethod.GET)
    public BaseRstBeanT<String> getQiNiuToken() {
        return new BaseRstBeanT<>(qiNiuService.getUpToken());
    }

    @NoNeedAccessAuthentication  // todo
    @ApiOperation(value = "获取七牛上传 token", notes = "")
    @RequestMapping(value = {"/getQiNiuTokenTest"}, method = RequestMethod.GET)
    public BaseRstBeanT<String> getQiNiuTokenTest() {
        log.info("getQiNiuTokenTest");
        return new BaseRstBeanT<>(qiNiuService.getUpToken());
    }
}
