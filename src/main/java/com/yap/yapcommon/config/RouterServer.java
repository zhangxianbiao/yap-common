package com.yap.yapcommon.config;

import com.yap.yapcommon.handler.MainHandler;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.netty.http.server.HttpServer;

import javax.annotation.PostConstruct;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

/**
 *
 * 无法注入getBaiduResponse todo zxb，后续再研究，看下spring boot 的加载过程
 */
public class RouterServer {

    @PostConstruct
    public void init() {
        createFluxReactorServer();
    }

    public void createFluxReactorServer() {
        // 路由和handler的适配
        RouterFunction<ServerResponse> router = routerFunction();
        HttpHandler handler = RouterFunctions.toHttpHandler(router);
        ReactorHttpHandlerAdapter adapter = new ReactorHttpHandlerAdapter(handler);

        // 创建服务器
        HttpServer httpServer = HttpServer.create();
        httpServer.handle(adapter).port(8081).bindNow();
    }

    public RouterFunction<ServerResponse> routerFunction() {
        MainHandler mainHandler = new MainHandler();
        return RouterFunctions.route(
                GET("/testing").and(accept(MediaType.APPLICATION_JSON)),
                mainHandler::getBaiduResponse);
    }

}
