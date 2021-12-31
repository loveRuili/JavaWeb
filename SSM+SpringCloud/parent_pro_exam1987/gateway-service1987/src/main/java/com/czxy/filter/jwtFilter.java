package com.czxy.filter;



import com.czxy.JWTUtil;
import io.jsonwebtoken.Claims;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@Order(-1)
public class jwtFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //1.获取路径
        String path = exchange.getRequest().getURI().getPath();
        System.out.println(path);
        //2.过滤出除了登录以外的请求
        if (!path.contains("auth")&&!path.contains("user")){
            //3.判断是否登录
            List<String> authorization = exchange.getRequest().getHeaders().get("authorization");
            if (authorization!=null&&authorization.size()>0){
                System.out.println(authorization.get(0));
                //4.判断token是否有效
                Claims claims = JWTUtil.parseToken(authorization.get(0), "user");
                System.out.println(claims);
                if (claims!=null){
                    System.out.println(claims.get("userId"));
                    //有效放行
                    return chain.filter(exchange);
                }
            }
            //无效，则拦截
            exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
            return exchange.getResponse().setComplete();
        }
        //放行
        return chain.filter(exchange);
    }
}
