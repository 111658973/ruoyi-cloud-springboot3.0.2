package com.ruoyi.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

/**
 * @Author: Cbf
 * @Date: 2024/4/16
 * @Description: 反爬虫过滤器
 */
@Component
public class AntiCrawlerFilter extends AbstractGatewayFilterFactory<AntiCrawlerFilter.Config> {

    public AntiCrawlerFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            if (isCrawler(exchange)) {
                exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                return exchange.getResponse().setComplete();
            }
            return chain.filter(exchange);
        };
    }

    private boolean isCrawler(ServerWebExchange exchange) {
        // 自定义逻辑来检测爬虫
        // 例如，检查User-Agent或请求频率等
        String userAgent = exchange.getRequest().getHeaders().getFirst("User-Agent");
        return userAgent != null && userAgent.contains("bot");
    }

    public static class Config {
        // 在这里放置配置属性
    }
}