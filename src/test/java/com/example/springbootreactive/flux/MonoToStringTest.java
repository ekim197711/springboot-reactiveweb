package com.example.springbootreactive.flux;


import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class MonoToStringTest {

    private void useTheString(String importantstring){
        System.out.printf("Using the important string.... %s %n", importantstring);
    }

    @Test
    public void tryMonoToString() throws InterruptedException {
        Mono<String> thirdpartyMono = Mono.just("Hi test 1 2 3. This is a string. Tralalala")
                .delayElement(Duration.ofSeconds(1));
//        thirdpartyMono.subscribe(s -> useTheString(s));
        String block = thirdpartyMono.block();
        useTheString(block);
    }
}
