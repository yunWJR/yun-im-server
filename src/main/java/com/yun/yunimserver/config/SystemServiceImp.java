package com.yun.yunimserver.config;

import org.springframework.stereotype.Service;

/**
 * @author: yun
 * @createdOn: 2019-07-12 17:07.
 */

@Service
public class SystemServiceImp {

    public static boolean isServerOn = true;

    // @Autowired
    // private SysParaJrp sysParaJrp;

    /**
     * 检查 code 是否有效
     * @param code
     */
    public void checkSysCode(String code) {
        // if (StringUtil.isNullOrEmpty(code)) {
        //     throw new BusinessException(ErrorCodeEnum.G10003, "无操作权限");
        // }
        //
        // SysPara para = sysParaJrp.findFirstByType(SysParaType.CacheAuthCode.getType());
        //
        // if (para == null) {
        //     throw new BusinessException(ErrorCodeEnum.G10003, "无操作权限");
        // }
        //
        // if (!para.getValue().equals(code)) {
        //     throw new BusinessException(ErrorCodeEnum.G10003, "无操作权限");
        // }
    }

    /**
     * 更新系统在线状态
     * @param isOn
     */
    public void updateServerOn(boolean isOn) {
        isServerOn = isOn;
    }
}

