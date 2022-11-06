package com.hml.gateway;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author hml
 * @version 1.0
 * @description: 自定义全局过滤器 AuthorizeFilter 实现 GlobalFilter接口
 * @date 2022/11/6 10:33
 */
@Component
//@Order(-1)  //过滤器执行顺序  值越小优先级越高 可以通过注解@Order或者实现Ordered接口
public class AuthorizeFilter implements GlobalFilter, Ordered {
    /**
     * Process the Web request and (optionally) delegate to the next {@code WebFilter}
     * through the given {@link GatewayFilterChain}.
     *
     * @param exchange the current server exchange
     * @param chain    provides a way to delegate to the next filter
     * @return {@code Mono<Void>} to indicate when request processing is complete
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //http://localhost:10010/user/1?authorization=admin  可以访问

        //1.获取请求参数
        ServerHttpRequest request = exchange.getRequest();
        MultiValueMap<String, String> queryParams = request.getQueryParams();
        //2.获取参数中的 authorization 参数
        String auth = queryParams.getFirst("authorization");
        //3.判断参数是否等于admin
        if ("admin".equals(auth)){
            //4.是 放行
            return  chain.filter(exchange);

        }
        //5.否 拦截
        //5.1 设置状态栏
        //setStatusCode()需要传入一个枚举类型的状态码， HttpStatus.UNAUTHORIZED 401 未登录
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        //5.2 拦截请求
        return exchange.getResponse().setComplete();
    }

    /**
     * Get the order value of this object.
     * <p>Higher values are interpreted as lower priority. As a consequence,
     * the object with the lowest value has the highest priority (somewhat
     * analogous to Servlet {@code load-on-startup} values).
     * <p>Same order values will result in arbitrary sort positions for the
     * affected objects.
     *
     * @return the order value
     * @see #HIGHEST_PRECEDENCE
     * @see #LOWEST_PRECEDENCE
     */
    @Override
    public int getOrder() {
        return -1;
    }
}
