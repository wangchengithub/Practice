package com.practice.gateway;

import org.springframework.cloud.gateway.handler.predicate.BeforeRoutePredicateFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfiguration {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder, BeforeRoutePredicateFactory beforeRoutePredicateFactory) {
        return builder.routes()
                .route("path_route", r -> r.path("/get").uri("http://httpbin.org"))
                .route("host_route", r -> r.host("*.myhost.org").uri("http://httpbin.org"))
                .route("rewrite_route", r -> r.host("*.rewrite.org").filters(f -> f.rewritePath("/foo/(?<segment>.*)", "/${segment}")).uri("http://httpbin.org"))
                //.route("hystrix_route", r -> r.host("*.hystrix.org").filters(f -> f.hystrix(c -> c.setName("slowcmd"))).uri("http://httpbin.org"))
                //.route("hystrix_fallback_route", r -> r.host("*.hystrixfallback.org").filters(f -> f.hystrix(c -> c.setName("slowcmd").setFallbackUri("forward:/hystrixfallback"))).uri("http://httpbin.org"))
                //.route("limit_route", r -> r.host("*.limited.org").and().path("/anything/**")/*.filters(f -> f.requestRateLimiter(c -> c.setRateLimiter(redisRateLimiter())))*/.uri("http://httpbin.org").predicate(beforeRoutePredicateFactory.apply(new BeforeRoutePredicateFactory.Config())))
                .build();
    }
}
