package org.security.demo.handler;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
*@Description 访问没有授权时的处理器
*@Param 
*@Return 
*/
@Component
public class RestfulAccessDeniedHandler  implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
       httpServletResponse.setCharacterEncoding("utf-8");
       httpServletResponse.setContentType("application/json");
       httpServletResponse.setStatus(200);
       httpServletResponse.getWriter().println("您当前访问未授权");//这里返回一个字符串，可以吧一个对象序列化之后再返回。
       httpServletResponse.getWriter().flush();
    }
}
