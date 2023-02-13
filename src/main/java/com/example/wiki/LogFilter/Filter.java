package com.example.wiki.LogFilter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j      //加载日志
@Component//当作组件 被spring容器扫描
public class Filter implements javax.servlet.Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        javax.servlet.Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        log.info("当前的请求地址为{}", req.getRequestURI());
        log.info("当前的请求方式为{}", req.getMethod());
        long start = System.currentTimeMillis();
        filterChain.doFilter(req, servletResponse);//放行
        log.info("当前请求耗时{}ms", System.currentTimeMillis() - start);
    }

    @Override
    public void destroy() {
        javax.servlet.Filter.super.destroy();
    }
}
