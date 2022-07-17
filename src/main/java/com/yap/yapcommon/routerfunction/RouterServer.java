package com.yap.yapcommon.routerfunction;

import com.yap.yapcommon.routerfunction.MainHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.netty.http.server.HttpServer;

import javax.annotation.PostConstruct;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

/**
 *
 * 无法注入getBaiduResponse todo zxb，后续再研究，看下spring boot 的加载过程
 */
public class RouterServer {

    @Autowired
    private MainHandler mainHandler;

    @PostConstruct
    public void init() {
        System.out.println("Init RouterServer...");
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

        // MainHandler 不能new, 否则getBaiduResponse 里面的 BaiduRequest 就无法注入了
        // 必须都得使用Autowired
        //MainHandler mainHandler = new MainHandler();
        return RouterFunctions
                .route(
                    GET("/router/baidu").and(accept(MediaType.TEXT_PLAIN)),
                    mainHandler::getBaiduResponse)
                .andRoute(
                      GET("/router/person").and(accept(MediaType.TEXT_PLAIN)),
                      mainHandler::getLocalResponse);
    }

}
