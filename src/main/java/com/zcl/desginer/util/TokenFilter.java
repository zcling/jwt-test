package com.zcl.desginer.util;

import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.redisson.api.RBucket;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.alibaba.fastjson.JSONObject;
import com.zcl.desginer.constant.ParamConstants;
import com.zcl.desginer.redis.RedissonService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * @title: TokentUtil
 * @Author chenglin.zou
 * @Date: 2022/5/21 13:27
 * @description token校验
 * @Version 1.0
 */
@AllArgsConstructor
@Component
@Slf4j
@Order
public class TokenFilter implements GlobalFilter, Ordered {
    private ApplicationConfig applicationConfig;

    private RedissonService redissonService;

    private static Pattern pattern = Pattern.compile("^/system/login$");

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("进入token过滤器！");
        String path = exchange.getRequest().getPath().value();
        if (pattern.matcher(path).find()) {
            return chain.filter(exchange);
        }
        Boolean enableToken = applicationConfig.getEnableToken();
        if (enableToken) {
            return chain.filter(exchange);
        }
        String token = getQueryParam(exchange.getRequest(), ParamConstants.TOKEN);
        String body = JwtUtil.decryptToken(token);
        JSONObject jsonObject = JSONObject.parseObject(body);
        String userId = jsonObject.getString("userId");
        String userCache = ParamConstants.USERID_CACEH + userId;
        RBucket<String> rBucket = redissonService.getRBucket(userCache);
        if (StringUtils.isEmpty(rBucket.get())) {
            throw new RuntimeException("TOKEN过期了");
        }
        return chain.filter(exchange);
    }

    public String getQueryParam(ServerHttpRequest request, String key) {
        String value = request.getQueryParams().getFirst(key);
        return value;
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
