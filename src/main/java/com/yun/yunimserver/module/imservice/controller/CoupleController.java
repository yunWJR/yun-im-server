package com.yun.yunimserver.module.imservice.controller;

import com.yun.base.module.Bean.BaseRstBeanT;
import com.yun.yunimserver.module.imservice.dtovo.GroupUserVo;
import com.yun.yunimserver.module.imservice.service.CoupleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * @author: yun
 * @createdOn: 2018-12-03 15:48.
 */

@RestController
@RequestMapping(value = "api/v1/conversation/couple")
@Api(tags = "02-01-会话管理-单聊")
public class CoupleController {

    // region --Field

    private CoupleService coupleService;

    // endregion

    // region --Constructor

    @Autowired
    public CoupleController(CoupleService coupleService) {
        this.coupleService = coupleService;
    }

    // endregion

    // region --Public method

    @ApiOperation(value = "获取单聊用户", notes = "")
    @RequestMapping(value = {"/getChatCoupleUser/{chatCoupleId}"}, method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "chatCoupleId", value = "chatCoupleId", required = true, dataType = "string"),
    })
    public BaseRstBeanT<GroupUserVo> getChatCoupleUser(@PathVariable @NotNull Long chatCoupleId) {
        return new BaseRstBeanT<>(coupleService.getChatCoupleUser(chatCoupleId));
    }

    // endregion
}
