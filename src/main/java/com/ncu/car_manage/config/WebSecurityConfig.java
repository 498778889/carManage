package com.ncu.car_manage.config;

import com.ncu.car_manage.interceptor.RedisInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebSecurityConfig implements WebMvcConfigurer {

    @Bean
    public RedisInterceptor getSessionInterceptor() {
        return new RedisInterceptor();
    }
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry){
//        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/css/");
//        registry.addResourceHandler("/images/**").addResourceLocations("classpath:/images/");
//        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/js/");
//        registry.addResourceHandler("/layui/**").addResourceLocations("classpath:/layui/");
//    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //所有已api开头的访问都要进入RedisSessionInterceptor拦截器进行登录验证，并排除login接口(全路径)。必须写成链式，分别设置的话会创建多个拦截器。
        // 必须写成getSessionInterceptor()，否则SessionInterceptor中的@Autowired会无效
        registry.addInterceptor(getSessionInterceptor())
                .addPathPatterns("/carManage/**")
                .excludePathPatterns("/carManage/login")
                .excludePathPatterns("/carManage/logout")
                .excludePathPatterns("/User/*")
                .excludePathPatterns("/carManage/register")
                .excludePathPatterns("/js/**")
                .excludePathPatterns("/css/**")
                .excludePathPatterns("/images/**")
                .excludePathPatterns("/layui/**");
        WebMvcConfigurer.super.addInterceptors(registry);

    }
}
