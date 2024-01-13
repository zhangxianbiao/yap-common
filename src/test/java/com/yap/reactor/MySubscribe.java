package com.yap.reactor;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;

public class MySubscribe extends BaseSubscriber {


    @Override
    protected void hookOnSubscribe(Subscription subscription) {
        super.hookOnSubscribe(subscription);
        System.out.println("BaseSubscriber-hookOnSubscribe");
    }


    @Override
    protected void hookOnNext(Object value) {
        super.hookOnNext(value);
        System.out.println("BaseSubscriber-hookOnNext");
    }


}
