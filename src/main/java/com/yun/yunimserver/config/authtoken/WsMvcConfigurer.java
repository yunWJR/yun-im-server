package com.yun.yunimserver.config.authtoken;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.*;

/**
 * @author: yun
 * @createdOn: 2019-07-12 17:26.
 */

@Configuration
public class WsMvcConfigurer implements WebMvcConfigurer {
    // 拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthHandlerInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");
    }

    // // 跨域访问
    // @Override
    // public void addCorsMappings(CorsRegistry registry) {
    //     registry.addMapping("/**")
    //             .allowedOrigins("*")
    //             .exposedHeaders("Set-Cookie")
    //             .allowedHeaders("*")
    //             .allowCredentials(false)
    //             .allowedMethods("*")
    //             .maxAge(3600);
    // }

    // 格视化
    @Override
    public void addFormatters(FormatterRegistry registry) {
        // registry.addFormatter(new DateFormatter("yyyy-MM-dd HH:mm:ss"));
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseSuffixPatternMatch(false).
                setUseTrailingSlashMatch(true);
    }

    /**
     * 添加静态资源--过滤swagger-api (开源的在线API文档)
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //过滤swagger
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

        registry.addResourceHandler("/swagger-resources/**")
                .addResourceLocations("classpath:/META-INF/resources/swagger-resources/");

        registry.addResourceHandler("/swagger/**")
                .addResourceLocations("classpath:/META-INF/resources/swagger*");

        registry.addResourceHandler("/v1/api/**")
                .addResourceLocations("classpath:/META-INF/resources/v1/api/");

    }

    // URL到视图的映射
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // registry.addViewController("/index.html").setViewName("/index.btl");
        // registry.addRedirectViewController("/**/*.do", "/index.html");
    }
}
