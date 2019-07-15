package com.yun.base.token;

import com.yun.base.Util.StringUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * AuthToken切面，用于对有AuthToken注解的方法进行验证
 * @author: yun
 * @createdOn: 2018 /6/4 14:03.
 */
@Configuration
@Aspect
@Component
public class AuthTokenAspect {
    private final AuthTokenParam tokenParam;

    private final AuthTokenUtil tokenUtil;

    /**
     * Instantiates a new Auth token aspect.
     * @param tokenParam the token param
     * @param tokenUtil  the token util
     */
    @Autowired
    public AuthTokenAspect(AuthTokenParam tokenParam, AuthTokenUtil tokenUtil) {
        this.tokenParam = tokenParam;
        this.tokenUtil = tokenUtil;
    }

    /**
     * Auth point.
     */
    @Pointcut(value = "@annotation(com.yun.base.token.AuthToken)")
    public void authTokenPoint() {
    }

    /**
     * Be before.
     * @param joinPoint the join point
     * @throws Throwable the throwable
     */
    @Before("authTokenPoint()")
    public void beBefore(JoinPoint joinPoint) throws Throwable {
    }

    /**
     * Do around object.
     * @param pjp       the pjp
     * @param authToken the auth token
     * @return the object
     * @throws Throwable the throwable
     */
    @Around("@annotation(authToken)")
    public Object doAround(ProceedingJoinPoint pjp, AuthToken authToken) throws Throwable {
        if (tokenParam.isIgnoreToken()) {
            Object[] args = pjp.getArgs();
            Object proRst = pjp.proceed(args);

            return proRst;
        }

        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest rqt = sra.getRequest();

        String tkVal = tokenParam.getToken(rqt);

        AuthTokenPayload jwt = tokenUtil.getValidToken(tkVal);

        Object[] args = pjp.getArgs();

        boolean findTokenAg = false;

        // 1.根据注解名称设定参数值
        if (authToken.paraName().length() > 0) {
            Signature signature = pjp.getSignature();
            MethodSignature methodSignature = (MethodSignature) signature;
            String[] parNas = methodSignature.getParameterNames();
            for (int i = 0; i < parNas.length; i++) {
                String na = parNas[i];
                if (StringUtil.isNullOrEmpty(na)) {
                    continue;
                }

                if (na.equals(authToken.paraName())) {
                    findTokenAg = true;
                    args[i] = jwt;
                    break;
                }
            }
        }

        if (!findTokenAg) { // 没有注解名称，则设置类型为AuthTokenPayload的参数
            for (int i = 0; i < args.length; i++) {
                Object obj = args[i];
                if (obj == null) {
                    continue;
                }

                if (obj.getClass().getName().equals("com.yun.base.token.AuthTokenPayload")) {
                    findTokenAg = true;
                    args[i] = jwt;
                    break;
                }
            }
        }

        //
        if (!findTokenAg) {
            // 没有地方设置 token
        }

        //  继续执行方法
        Object proRst = pjp.proceed(args);

        return proRst;
    }
}
