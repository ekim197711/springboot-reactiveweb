package com.example.springbootreactive.flux;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;

public class SpaceShipSubscriber<SpaceShip> extends BaseSubscriber<SpaceShip> {
    @Override
    protected void hookOnSubscribe(Subscription subscription) {
        System.out.println(String.format("Subscribed: %s", subscription));
        request(1);
    }

    @Override
    protected void hookOnNext(SpaceShip value) {
        System.out.println(String.format("next element: %s", value));
        request(1);
    }

    @Override
    protected void hookOnComplete() {
        System.out.println("Completed!!!: ");
    }

    @Override
    protected void hookOnError(Throwable throwable) {
        System.out.println(String.format("Error occured!!!: %s", throwable.getMessage()));
    }

    @Override
    protected void hookOnCancel() {
        System.out.println("canceled!");
    }
}
