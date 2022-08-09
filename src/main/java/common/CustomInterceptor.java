package common;

import api.controller.MovieApiController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomInterceptor implements HandlerInterceptor {
    private static Logger log = LoggerFactory.getLogger(CustomInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.debug("[CustomInterceptor] preHandle Start");
        log.debug("target Controller: {}", handler);

        request.setAttribute("startTime", System.currentTimeMillis());

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.debug("[CustomInterceptor] postHandle Start");

        request.setAttribute("endTime", System.currentTimeMillis());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) throws Exception {
        Long startTime = (Long)request.getAttribute("startTime");
        Long endTime = (Long)request.getAttribute("endTime");
        Long proceedingTime = endTime - startTime;

        log.debug("요청 및 응답 완료 시간: {}", proceedingTime);
    }
}
