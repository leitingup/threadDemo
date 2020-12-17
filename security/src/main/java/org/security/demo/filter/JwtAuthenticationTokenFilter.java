package org.security.demo.filter;

import cn.hutool.core.util.StrUtil;
import org.security.demo.entity.UserEntity;
import org.security.demo.service.UserService;
import org.security.demo.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import sun.plugin.liveconnect.SecurityContextHelper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //当前上下文认证中不存在认证信息
        //尝试获取token （token不一定存放在header中，比如也可以当作请求参数进行传递）
        //尝试从token中解析对象（token中可以存放任何信息）
        //尝试从根据存放在token的信息去找对应的用户信息
        //用户找到用户信息 就在当前的认证上下文中进行设置，确保后续的filter能够检测到认证通过
        if(SecurityContextHolder.getContext().getAuthentication()==null){
            String token = request.getHeader("token");
            if(StrUtil.isNotBlank(token)){
                String tokenObj = JwtUtils.getJwtTokenClaimValue(token);
                if(StrUtil.isNotBlank(tokenObj)){
                    UserEntity user = userService.getByUsername(tokenObj);
                    if(user!=null){
                        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
                        if(user.getAuthorities()!=null && user.getAuthorities().size()>0){
                            authorities = user.getAuthorities().stream().map
                                    (a -> new SimpleGrantedAuthority(a)).collect(Collectors.toList());
                        }
                    }
                }
            }
        }
        //调用下一个过滤器
        filterChain.doFilter(request,response);
    }
}
