package com.yap.yapcommon;

import com.yap.yapcommon.handler.MainHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.netty.http.server.HttpServer;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

/**
 *
 * 还有问题，报空指针，没有完成注入 todo
 */
public class Server {

    public RouterFunction<ServerResponse> routerFunction() {
        MainHandler handler = new MainHandler();

        return RouterFunctions.route(
                GET("/testing").and(accept(MediaType.APPLICATION_JSON)), handler::getBaiduResponse);
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

    public static void main(String[] args) throws Exception{
        Server server = new Server();
        server.createFluxReactorServer();
        System.out.println("Server stated, press any key to exit");
        System.in.read();
    }
}
