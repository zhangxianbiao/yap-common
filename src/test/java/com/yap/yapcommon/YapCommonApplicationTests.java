package com.yap.yapcommon;

import com.yap.yapcommon.pojo.Person;
import com.yap.yapcommon.request.BaiduRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.scheduler.Schedulers;

import java.util.Scanner;

@SpringBootTest
class YapCommonApplicationTests {

    @Autowired
    Person person;

    @Autowired
    BaiduRequest baiduRequest;

    @Test
    void contextLoads() {
        baiduRequest.request()
                .subscribeOn(Schedulers.single())
                .map(x -> x.get())
                .doOnError(x -> x.printStackTrace() )
                .doOnSuccess(x -> System.out.println(x))
                .doFinally(x -> System.out.println("finally"))
                .subscribe(System.out::println);

        (new Scanner(System.in)).nextLine();
    }
}
