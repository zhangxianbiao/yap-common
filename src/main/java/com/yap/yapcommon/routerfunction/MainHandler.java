package com.yap.yapcommon.routerfunction;

import com.yap.yapcommon.request.BaiduRequest;
import com.yap.yapcommon.request.LocalRouterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class MainHandler {

    @Autowired
    private BaiduRequest baiduRequest;

    @Autowired
    private LocalRouterRequest localRouterRequest;

    public Mono<ServerResponse> getBaiduResponse(ServerRequest serverRequest) {
        return baiduRequest.request()
                .map(x -> x.get())
                .flatMap(x -> {
                    return ServerResponse
                            .ok()
                            //.contentType(MediaType.TEXT_PLAIN)
                            .body(Mono.justOrEmpty(x), Object.class);
                        });
    }

    public Mono<ServerResponse> getLocalResponse(ServerRequest serverRequest) {
        return localRouterRequest.request()
                .map(x -> x.get())
                .flatMap(x -> {
                    return ServerResponse
                            .ok()
                            .body(Mono.justOrEmpty(x), Object.class);
                        });
    }

}
