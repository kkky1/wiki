package com.example.wiki.Interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class PostHandler implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
        log.info("当前的请求地址为{}", req.getRequestURI());
        log.info("远程地址为{}", req.getRemoteAddr());
        log.info("当前的请求方式为{}", req.getMethod());
        long start = System.currentTimeMillis();
        req.setAttribute("PostStartTime",start);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        long startTime = (long) request.getAttribute("PostStartTime");
        log.info("-----当前请求耗时:{}ms-----", System.currentTimeMillis() - startTime);
    }
}
