package com.zcl.desginer.util;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.zcl.desginer.model.ParamConstants;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * @title: TokentUtil
 * @Author chenglin.zou
 * @Date: 2022/5/21 13:27
 * @description
 * @Version 1.0
 */
@AllArgsConstructor
@Component
@Slf4j
@Order
public class TokenFilter implements GlobalFilter, Ordered {
    private ApplicationConfig applicationConfig;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("进入token过滤器！");
        Boolean enableToken = applicationConfig.getEnableToken();
        if (enableToken) {
            return chain.filter(exchange);
        }
        String token = getQueryParam(exchange.getRequest(), ParamConstants.TOKEN);

        return null;
    }

    public String getQueryParam(ServerHttpRequest request, String key) {
        String value = request.getQueryParams().getFirst(key);

        return value;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
