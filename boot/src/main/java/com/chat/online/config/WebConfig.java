package com.chat.online.config;

import com.chat.online.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 拦截器的配置类
 **/
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{
    //拦截的路径
    String[] addPathPatterns={"/*","/view/*","/"};
    //允许通过的路径
    String[] excludePathPatterns={"/user/*","/webSocket/*"};
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加一个拦截器并且指定拦截路径(addPathPatterns)，和放行路径(excludePathPatterns)
      registry.addInterceptor(new LoginInterceptor()).addPathPatterns(addPathPatterns).excludePathPatterns(excludePathPatterns);
    }
}
