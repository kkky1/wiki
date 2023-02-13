package com.example.wiki.config;

import com.example.wiki.Interceptor.PostHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import javax.annotation.Resource;

@Component
public class SpingMvcConfig implements WebMvcConfigurer {

    @Resource
    private PostHandler postHandler;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(postHandler).addPathPatterns("/**");
    }
}
