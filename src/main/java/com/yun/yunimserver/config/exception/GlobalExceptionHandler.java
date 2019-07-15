package com.yun.yunimserver.config.exception;

import com.yun.base.module.Bean.BaseRstBean;
import com.yun.base.module.Bean.BaseRstCodeType;
import com.yun.base.module.Bean.RstBeanException;
import com.yun.base.token.AuthTokenException;
import com.yun.yunimserver.config.SpringEvnConfig;
import com.yun.yunimserver.util.JsonHelper;
import com.yun.yunimserver.util.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;

/**
 * The itemType Global exception handler.
 * @author: yun
 * @createdOn: 2018 /6/6 14:16.
 */
@ControllerAdvice
@Slf4j
@Component
public class GlobalExceptionHandler {
    @Autowired
    private SpringEvnConfig springEvnConfig;

    // region --Public method

    /**
     * 参数非法异常.
     * @param e the e
     * @return the wrapper
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public BaseRstBean illegalArgumentException(IllegalArgumentException e) {
        log.error(getLogExceptionMsg(e));

        if (springEvnConfig.isProEvn()) {
            return BaseRstBean.ComErrBean("参数异常");
        }

        return BaseRstBean.ComErrBean(e.getMessage());
    }

    /**
     * Hibernate 抛出的参数验证异常
     * @param e
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public BaseRstBean ConstraintViolationException(ConstraintViolationException e) {
        log.error(getLogExceptionMsg(e));

        if (springEvnConfig.isProEvn()) {
            return BaseRstBean.ComErrBean("参数异常");
        }

        String errMsg = null;

        Object[] objs = e.getConstraintViolations().toArray();
        if (objs != null && objs.length > 0) {
            // todo 其他类型
            ConstraintViolationImpl obj = (ConstraintViolationImpl) objs[0];

            if (obj != null) {
                errMsg = String.format("参数(%s) 错误：%s", obj.getPropertyPath(), obj.getMessage());
            }
        }

        if (errMsg == null) {
            errMsg = "参数异常";
        }

        log.error("参数非法异常={}", e.getMessage(), e);
        return BaseRstBean.ComErrBean(errMsg);
    }

    /**
     * 处理通用异常
     * @param e the e
     * @return the base rst bean
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    BaseRstBean handleException(Exception e) {
        String errMsg = this.getExceptionMsg(e);

        BaseRstBean rst = new BaseRstBean(BaseRstCodeType.InternalError, errMsg);
        return rst;
    }

    /**
     * 处理token 异常
     * @param e the e
     * @return the base rst bean
     */
    @ExceptionHandler(AuthTokenException.class)
    @ResponseBody
    BaseRstBean handleAuthTokenException(AuthTokenException e) {
        BaseRstBean rst = e.getRst();
        if (rst == null) {
            String errMsg = this.getExceptionMsg(e);
            rst = new BaseRstBean(BaseRstCodeType.TokenUnknown, errMsg);
        }

        return rst;
    }

    /**
     * Handle rst exception base rst bean.
     * @param e the e
     * @return the base rst bean
     */
    @ExceptionHandler(RstBeanException.class)
    @ResponseBody
    BaseRstBean handleRstException(RstBeanException e) {
        BaseRstBean rst = e.getRst();
        if (rst == null) {
            String errMsg = this.getExceptionMsg(e);
            rst = new BaseRstBean(BaseRstCodeType.TokenUnknown, errMsg);
        }

        return rst;
    }

    /**
     * 处理所有接口数据验证异常
     * @param e the e
     * @return the base rst bean
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    BaseRstBean handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String errMsg = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();

        BaseRstBean rst = new BaseRstBean(BaseRstCodeType.InValidData, errMsg);
        return rst;
    }

    // endregion

    // region --private method

    private String getExceptionMsg(Exception e) {
        String errMsg = "msg:" + e.getMessage() + "\n locMsg:" + e.getLocalizedMessage();

        log.error(errMsg);

        return errMsg;
    }

    private String getLogExceptionMsg(Exception e) {
        LogMsg logMsg = new LogMsg();

        if (e.getCause() != null) {
            logMsg.setMsg(JsonHelper.toStr(e.getCause()));
        } else {
            logMsg.setMsg(e.getMessage());
            logMsg.setMsg(e.getLocalizedMessage());
        }

        logMsg.setUserId(RequestUtil.getLoginUserId());

        logMsg.setPara(RequestUtil.getControllerPara());

        logMsg.updateStack(e);

        return JsonHelper.toStr(logMsg);
    }

    // endregion

    // region --Other

    // endregion
}
