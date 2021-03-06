package com.test.leak.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Slf4j
@Configuration
public class RouterConfig {

    @Bean
    @Order(1)
    public RouterFunction<ServerResponse> route(Handler handler) {
        return RouterFunctions.route()
                .GET("/leak/test", handler::handle)
                .build();
    }

}
