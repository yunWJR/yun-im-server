package com.yun.yunimserver.config;

// import com.yun.base.token.AuthTokenInterceptor;

/**
 * @author: yun
 * @createdOn: 2018-12-04 11:10.
 */

// @Configuration
// public class UrlInterceptConfig extends WebMvcConfigurerAdapter {
//
//     @Autowired
//     private AuthTokenInterceptor authTokenInterceptor;
//
//     @Override
//     public void addInterceptors(InterceptorRegistry registry) {
//         System.out.println("进入拦截器配置器");
//
//         //注册拦截器
//         InterceptorRegistration iRegistration = registry.addInterceptor(authTokenInterceptor);
//         iRegistration.addPathPatterns("/**")
//                 .excludePathPatterns("/swagger-resources/**", "*.js", "/**/*.js", "*.css", "/**/*.css", "*.html", "/**/*.html", "/v2/api-docs/**", "/configuration/**");
//         //super.addInterceptors(registry);
//     }
// }
