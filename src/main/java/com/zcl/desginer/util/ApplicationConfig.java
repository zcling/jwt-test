package com.zcl.desginer.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;

/**
 * @title: ApplicationConfig
 * @Author chenglin.zou
 * @Date: 2022/5/21 15:00
 * @description
 * @Version 1.0
 */
@Getter
@Component
public class ApplicationConfig {
    @Value("${filter.enable.token}")
    private Boolean enableToken;
}
