package com.yap.yapcommon.controller;

import com.yap.yapcommon.pojo.Person;
import com.yap.yapcommon.service.PersonService;
import lombok.val;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;


@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonService personService;


    @GetMapping("/getbytype")
    public Mono<Map<String, Object>> test(ServerHttpRequest request ) {
        /**
         * 使用 HttpServletRequest ServerRequest 均会报错
         * No primary or single unique constructor found for interface javax.servlet.http.HttpServletRequest/ServerRequest
         * 把 javax.servlet.http.HttpServletRequest 换成 org.springframework.http.server.reactive.ServerHttpRequest 即可。
         * 因为Spring Cloud Gateway 不支持 HttpServletRequest ? 。
         */
        final String type = request.getQueryParams().get("type").get(0);

        if ("1".equals(type)) {
            return Mono.just(new HashMap<>(){{
                put("code", 1);
                put("status", "success");
                put("b", "c");
            }});
        }else if("2".equals(type)) {
            Person person = new Person("1", "zhangsan", "18");
            return Mono.just(new HashMap<>(){{
                put("code", 1);
                put("status", "success");
                put("person", person);
            }});
        }else {
            return Mono.just(new HashMap<>(){{
                put("code", 1);
                put("status", "param error");
            }});
        }
    }

    @GetMapping("/getbyid/{id}")
    public Mono<Person> getPersonById(@PathVariable String id) {
        return personService.getPersonById(id);
    }

    /**
     *
     * @return
     */
    //@GetMapping(value = "/getall",produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    @GetMapping(value = "/getall",produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<Person> getAllPerson() {
        return personService.getAllPerson()
                .delayElements(Duration.ofMillis(500))
                .doOnNext(person -> System.out.println("Server Produce:" + person));
    }

    @PostMapping("/update")
    public Mono<Person> updatePerson(@RequestBody Person person) {
        return personService.updatePerson(person);

    }
}
