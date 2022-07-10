package com.yap.yapcommon.handler;

import com.yap.yapcommon.request.BaiduRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public class MainHandler {

    @Autowired
    private BaiduRequest baiduRequest;

    //
    public Mono<ServerResponse> getBaiduResponse(ServerRequest serverRequest) {
        Mono<ServerResponse> serverResponseMono;
        return baiduRequest.request()
                .map(x -> x.get())
                .flatMap(x -> {
                    return ServerResponse
                            .ok()
                            .contentType(MediaType.TEXT_PLAIN)
                            .body(Mono.justOrEmpty(x), Object.class);
                        });
    }


}
