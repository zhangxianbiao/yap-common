package com.yap.yapcommon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication

// 需要加上这个，因为测试的类和主运行类不在一个包下，测试时扫描不到，测试类会报No qualifying bean
@ComponentScan(basePackages = "com.yap")
public class YapCommonApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(YapCommonApplication.class, args);
    }

    // webflux 支持两种编程模型，两种模型不能同时使用
    // 1. 基于注解的编程模型
    //  使用springMVC的注解路由，使用Controller、GetMapping等制度路由
    // 2. 基于函数式编程模型
    //  使用RouterFunction (路由功能，请求转发给对应的handler) 和 HandlerFunction (处理请求生成相应的函数)
    //
    //
    //
    //


}
