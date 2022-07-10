package com.yap.yapcommon.controller;

import com.yap.yapcommon.request.BaiduRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Mono;

import java.util.Optional;


// @RestController = @Controller+@ResponseBody两个注解的结合，
// 使用@RestController这个注解，就不能返回jsp.html页面了，返回的是字符串数据
@Controller
public class MainController {

    @Autowired
    BaiduRequest baiduRequest;

    @ResponseBody
    @GetMapping("/")
    public String root() {
        return "This is yap!";
    }

    @ResponseBody
    @GetMapping("/testing")
    public Mono<String> getTest() {
        Mono<String> response = baiduRequest.request()
                .map(x -> x.get());
        return response;
    }
}
