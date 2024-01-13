package com.yap.reactor;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class TestReactor {

    public static void main(String[] args) {


        Mono.just("hello")
                .doOnNext(s -> System.out.println("==doOnNext: "+s))
                //.doOnError(s -> System.out.println("==doOnError: "+s))
                //.doOnEach(s -> System.out.println("==doOnEach: "+s))
                .doFinally(s -> System.out.println("==doFinally: "+s))
                //.subscribe(new MySubscribe());

                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onSubscribe(Subscription subscription) {
                        System.out.println("subscribe-onSubscribe");
                        subscription.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println("subscribe-onNext:" + s);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        System.out.println("subscribe-onError:" + throwable);

                    }

                    @Override
                    public void onComplete() {
                        System.out.println("subscribe-onComplete");

                    }
                });

        System.out.println();
        System.out.println("==============");
        System.out.println();

        Flux.just("hello", "world")
                //.doOnNext(s -> System.out.println("==doOnNext: "+s))
                //.doOnError(s -> System.out.println("==doOnError: "+s))
                .doOnEach(s -> System.out.println("==doOnEach: "+s))
                .doFinally(s -> System.out.println("==doFinally: "+s))
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onSubscribe(Subscription subscription) {
                        System.out.println("subscribe-onSubscribe");
                        subscription.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println("subscribe-onNext:" + s);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        System.out.println("subscribe-onError:" + throwable);

                    }

                    @Override
                    public void onComplete() {
                        System.out.println("subscribe-onComplete");

                    }
                });

    }


}