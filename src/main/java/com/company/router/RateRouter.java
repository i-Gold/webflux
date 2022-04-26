package com.company.router;

import com.company.handler.RateHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration(proxyBeanMethods = false)
public class RateRouter {

    @Bean
    public RouterFunction<ServerResponse> route(RateHandler rateHandler) {

        return RouterFunctions
                .route(GET("/api/exchange-rate").and(accept(MediaType.TEXT_PLAIN)), rateHandler::getRate);
    }

}
