package com.example.cms9cc.admin.login;

import com.example.cms9cc.tools.JWTUtils;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    /**
     * 目标方法执行前
     * 该方法在控制器处理请求方法前执行，其返回值表示是否中断后续操作
     * 返回 true 表示继续向下执行，返回 false 表示中断后续操作
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("token");
        System.out.println(token);


        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            JwtIgnore jwtIgnore = handlerMethod.getMethodAnnotation(JwtIgnore.class);
            if (jwtIgnore != null) {
                return true;
            }
        }
        if (HttpMethod.OPTIONS.name().equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }

        if(JWTUtils.verify(token)==null){
           throw new RuntimeException();
        }else{
            return true;
        }
    }
    /**
     * 目标方法执行后
     * 该方法在控制器处理请求方法调用之后、解析视图之前执行
     * 可以通过此方法对请求域中的模型和视图做进一步修改
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println(modelAndView);
    }
    /**
     * 页面渲染后
     * 该方法在视图渲染结束后执行
     * 可以通过此方法实现资源清理、记录日志信息等工作
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println(ex);
    }
}