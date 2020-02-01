package com.example.springbootreactive.flux;

import com.example.springbootreactive.space.SpaceShip;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class FlatMapAndMapsTest {
    private Flux<SpaceShip> flux() {
        return Flux.just(
                new SpaceShip("Hawk", 10),
                new SpaceShip("Eagle", 120),
                new SpaceShip("Swan", 20),
                new SpaceShip("Round", 30),
                new SpaceShip("Pyramid", 55),
                new SpaceShip("Black Bird", 5)
        )
                .delayElements(Duration.ofSeconds(1));
    }

    @Test
    void tryFlatmaps() throws InterruptedException {
        System.out.println("Start of test");
        Flux<SpaceShip> flux = flux();
        flux.map(ship -> {
            ship.setId("-1");
            return ship;
        }).flatMap(ship -> {
            return Flux.just(ship, new SpaceShip(-100, ship.getName() + "_LARGE", ship.getCrew() * 2));
        })
          .filter(ship -> ship.getCrew() > 30)
          .log("mike.flux.")
          .subscribe(ship -> {
                    System.out.println(String.format("Ship: %s", ship));
                });

        System.out.println("End of test");
        Thread.sleep(10000);
    }

}
