package com.yap.yapcommon.config;


import com.yap.yapcommon.routerfunction.RouterServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 先注释
 */
@Configuration
public class YapAutoConfig {

    @Bean
    public RouterServer getRouterServer() {
        return new RouterServer();
    }
}
