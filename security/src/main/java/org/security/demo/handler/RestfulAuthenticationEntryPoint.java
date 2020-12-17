package org.security.demo.handler;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class RestfulAuthenticationEntryPoint implements AuthenticationEntryPoint {


    /**
    *@Description 认证信息失效（未认证或认证信息过期）处理器
    *@Param [request, response, e]
    *@Return void
    */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setCharacterEncoding("uef-8");
        response.setContentType("application/json");
        response.setStatus(200);
        response.getWriter().println("您当前的认证信息无效");
        response.getWriter().flush();
    }
}
