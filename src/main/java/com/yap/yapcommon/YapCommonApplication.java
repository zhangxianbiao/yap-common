package com.yap.yapcommon;

import com.yap.yapcommon.client.ReactorWebClientTest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication

// 需要加上这个，因为测试的类和主运行类不在一个包下，测试时扫描不到，测试类会报No qualifying bean
@ComponentScan(basePackages = "com.yap")
public class YapCommonApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(YapCommonApplication.class, args);

        /*
        ReactorWebClientTest reactorWebClientTest = new ReactorWebClientTest();
        reactorWebClientTest.consume2();

         */
    }

}
