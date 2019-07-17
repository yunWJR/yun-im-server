package com.yun.yunimserver.config.authtoken;

import com.yun.base.Util.StringUtil;
import com.yun.base.module.Bean.RstBeanException;
import com.yun.yunimserver.config.NoNeedAccessAuthentication;
import com.yun.yunimserver.config.SetSystem;
import com.yun.yunimserver.config.SpringContextUtil;
import com.yun.yunimserver.config.SystemServiceImp;
import com.yun.yunimserver.module.user.entity.User;
import com.yun.yunimserver.module.user.service.UserService;
import com.yun.yunimserver.util.GlobalConstant;
import com.yun.yunimserver.util.RequestUtil;
import com.yun.yunimserver.util.ThreadLocalMap;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author: yun
 * @createdOn: 2019-07-12 17:06.
 */

@Slf4j
@Component
public class AuthHandlerInterceptor implements HandlerInterceptor {

    /**
     * 需要手动注入
     */
    private UserService _loginServiceImp;

    /**
     * 该类不会自动注入，所以 bean 需要手动注入
     * @return
     */
    private UserService loginServiceImp() {
        if (_loginServiceImp == null) {
            _loginServiceImp = SpringContextUtil.getBean(UserService.class);
        }

        return _loginServiceImp;
    }

    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 跨域试探请求不处理 todo
        if (!(handler instanceof HandlerMethod)) {
            getDeviceType(request);
            return true;
        }

        if (!isSetSystem(handler) && !SystemServiceImp.isServerOn) {
            throw RstBeanException.RstComErrBeanWithStr("服务器已停止，请稍候重试");
        }

        // 根据注解确定是否检查
        if (noCheckToken(handler)) {
            getDeviceType(request);
            return true;
        }

        boolean hasAuth = false;

        String tokenStr = request.getHeader(GlobalConstant.Sys.TOKEN_AUTH_DTO);
        // 无 token
        if (!StringUtils.isBlank(tokenStr)) {
            User appUser = loginServiceImp().checkTokenUser(tokenStr, request);

            // token 无效
            if (appUser != null) {
                hasAuth = true;
                RequestUtil.saveLoginUser(appUser);
            }
        }

        if (!hasAuth) {
            setRspNoToken(response);

            return false;
        }

        // 获取设备类型
        getDeviceType(request);

        return true;
    }

    private void setRspNoToken(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Credentials", "false");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Expose-Headers", "Set-Cookie");
        response.setHeader("contentJson-type", "application/json");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setStatus(401);
    }

    private void getDeviceType(HttpServletRequest request) {
        String tokenStr = request.getHeader(GlobalConstant.Sys.DEVICE_TYPE_DTO);
        if (!StringUtil.isNullOrEmpty(tokenStr)) {
            Integer dv = Integer.valueOf(tokenStr);

            if (dv != null) {
                RequestUtil.saveDeviceType(dv);
            }
        }
    }

    /**
     * 请求处理之后进行调用，但是在视图被渲染之前(Controller方法调用之后)
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 在整个请求结束之后被调用，也就是在DispatcherServlet渲染了对应的视图之后执行
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ThreadLocalMap.remove();

        if (ex != null) {
            this.handleException(response);
        }
    }

    private void handleException(HttpServletResponse res) throws IOException {
        res.resetBuffer();
        res.setHeader("Access-Control-Allow-Origin", "*");
        res.setHeader("Access-Control-Allow-Credentials", "true");
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        res.getWriter().write("{\"code\":100009 ,\"message\" :\"解析token失败\"}");
        res.flushBuffer();
    }

    private boolean noCheckToken(Object handler) {
        HandlerMethod handlerMethod = (HandlerMethod) handler;

        Method method = handlerMethod.getMethod();

        NoNeedAccessAuthentication rps = AnnotationUtils.findAnnotation(method, NoNeedAccessAuthentication.class);

        return rps != null;
    }

    private boolean isSetSystem(Object handler) {
        HandlerMethod handlerMethod = (HandlerMethod) handler;

        Method method = handlerMethod.getMethod();

        SetSystem rps = AnnotationUtils.findAnnotation(method, SetSystem.class);

        return rps != null;
    }
}

