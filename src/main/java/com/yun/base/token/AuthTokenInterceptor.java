package com.yun.base.token;

/**
 * @author: yun
 * @createdOn: 2018-11-29 15:49.
 */

public class AuthTokenInterceptor {
}
//
// @Configuration
// @Component
// public class AuthTokenInterceptor implements HandlerInterceptor {
//
//     // region --Field
//
//     @Autowired
//     private AuthTokenParam tokenParam;
//
//     @Autowired
//     private AuthTokenUtil tokenUtil;
//
//     // endregion
//
//     // region --Constructor
//
//     // endregion
//
//     // region --static method
//
//     // endregion
//
//     // region --Getter and Setter
//
//     // endregion
//
//     // region --Public method
//
//     @Override
//     public boolean preHandle(HttpServletRequest rqt, HttpServletResponse rsp, Object handle) throws Exception {
//         if (!tokenParam.shouldCheckToken()) {
//             return true;
//         }
//
//         // 跨域试探请求不处理
//         if (!(handle instanceof HandlerMethod)) {
//             return true;
//         }
//
//         // 根据注解确定是否检查
//         if (!shouldCheckToken(handle)) {
//             return true;
//         }
//
//         // 根据token获取登录信息
//         String tkVal = tokenParam.getToken(rqt);
//
//         AuthTokenPayload jwt = tokenUtil.getValidToken(tkVal);
//
//         if (tokenParam.isThreadLocalMode()) {
//             AuthTokenThreadLocal.put(jwt);
//         } else {
//             // TODO 待添加
//         }
//
//         // todo 自定义解析
//         String plVal = rqt.getHeader(HeaderThreadLocal.platformName);
//         HeaderThreadLocal.put(HeaderThreadLocal.platformName, plVal);
//
//         // AuthTokenThreadLocal.put(jwt);
//
//         return true;
//     }
//
//     @Override
//     public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
//
//     }
//
//     @Override
//     public void afterCompletion(HttpServletRequest var1, HttpServletResponse var2, Object var3, Exception var4) throws Exception {
//         if (tokenParam.isThreadLocalMode()) {
//             AuthTokenThreadLocal.remove();
//         } else {
//             // TODO 待添加
//         }
//
//         HeaderThreadLocal.remove();
//     }
//
//     // endregion
//
//     // region --private method
//
//     private boolean shouldCheckToken(Object handler) {
//         HandlerMethod handlerMethod = (HandlerMethod) handler;
//
//         Method method = handlerMethod.getMethod();
//
//         // 全部默认需要校验 token
//         if (tokenParam.isAllNeedTokenOn()) {
//             // 排除NoAuthToken注解
//             NoAuthToken rsp = AnnotationUtils.findAnnotation(method, NoAuthToken.class);
//             return rsp == null;
//         }
//
//         // authtoken 注解需要检查
//         AuthToken rsp = AnnotationUtils.findAnnotation(method, AuthToken.class);
//
//         return rsp != null;
//     }
//
//     // endregion
//
//     // region --Other
//
//     // endregion
// }
