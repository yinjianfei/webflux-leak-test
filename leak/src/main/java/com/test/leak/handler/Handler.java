package com.test.leak.handler;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

@Component
public class Handler {

    private WebClient webClient;

    public Handler(WebClient.Builder webClientBuilder) {
        String url = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("127.0.0.1")
                .build()
                .toUriString();
        webClient = webClientBuilder
                .baseUrl(url)
                .clientConnector(new ReactorClientHttpConnector(HttpClient.create()))
                .build();
    }
    public Mono<ServerResponse> handle(ServerRequest serverRequest) {
        return query(serverRequest.path()).flatMap(jsonNode -> ServerResponse.ok().syncBody(jsonNode));
    }
    private Mono<JsonNode> query(String path) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .port(8080)
                        .path(path)
                        .build())
                .retrieve()
                .bodyToMono(JsonNode.class)
                .flatMap(Mono::just);
    }
}
