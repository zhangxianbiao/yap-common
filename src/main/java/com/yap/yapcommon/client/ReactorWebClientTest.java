package com.yap.yapcommon.client;

import com.yap.yapcommon.pojo.Person;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.web.reactive.function.client.WebClient;

/**
 *
 * 必须使用 WebClient 这一reactor的webClient请求才可以实现背压
 * 一般用户的web请求并不能实现背压操作，此时使用webflux作为服务器仅仅是性能的提升,非阻塞
 * 但是内部不同系统间的调用可以使用WebClient等作为下游客户端，或者自己写，可以实现对上游的背压
 * 服务端产生数据必须使用 produces = MediaType.APPLICATION_NDJSON_VALUE
 *
 * 响应式数据库引擎 R2DBC
 */
public class ReactorWebClientTest {

    WebClient webClient = WebClient.create("http://127.0.0.1:8080");

    public void consume1() {
        webClient.get()
                .uri("/person/get/{id}", "1")
                .retrieve()
                .bodyToMono(Person.class)
                .subscribe(person -> System.out.println("Client subscribe:" + person));
    }

    public void consume2() {

        webClient.get()
                .uri("/person/getall")
                .retrieve()
                .bodyToFlux(Person.class)
                .subscribe(new Subscriber<Person>() {

                    private Subscription subscription;
                    private Integer count = 0;
                    @Override
                    public void onSubscribe(Subscription subscription) {
                        this.subscription = subscription;
                        subscription.request(2);
                        System.out.println("Client request 2...");
                    }

                    @Override
                    public void onNext(Person person) {
                        count ++;
                        if(count >= 2) {
                            count = 0;
                            subscription.request(2);
                            System.out.println("Client request 2");
                        }
                        System.out.println("Client subscribes: " + person);
                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }



    public static void main(String[] args) throws Exception{
        ReactorWebClientTest reactorWebClientTest = new ReactorWebClientTest();
        reactorWebClientTest.consume2();
        Thread.sleep(10000);
    }

}
