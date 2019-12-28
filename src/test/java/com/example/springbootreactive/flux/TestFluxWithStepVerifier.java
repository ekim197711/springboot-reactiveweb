package com.example.springbootreactive.flux;

import com.example.springbootreactive.space.SpaceShip;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.List;

public class TestFluxWithStepVerifier {

    private Flux<SpaceShip> flux(){
        return Flux.just(
                new SpaceShip("Hawk", 10),
                new SpaceShip("Eagle", 120),
                new SpaceShip("Swan", 20),
                new SpaceShip("Round", 30),
                new SpaceShip("Pyramid", 55),
                new SpaceShip("Black Bird", 5)
        )
                .delayElements(Duration.ofSeconds(1))
                .map(ship -> {
                    if (ship.getName().equals("Round"))
                        throw new RuntimeException(String.format("The ship is round!!! %s", ship));
                    return ship;
                })
                ;
    }

    private Flux<SpaceShip> fluxWithNoError(){
        return Flux.just(
                new SpaceShip("Hawk", 10),
                new SpaceShip("Eagle", 120),
                new SpaceShip("Swan", 20),
                new SpaceShip("Round", 30),
                new SpaceShip("Pyramid", 55),
                new SpaceShip("Black Bird", 5)
        )
                .delayElements(Duration.ofSeconds(1))
                ;
    }

    @Test
    void testTheFluxWithStepVerifier() throws InterruptedException {
        System.out.println("Test BEGIN");
        Flux<SpaceShip> flux = fluxWithNoError();
        flux.subscribe(
                ship -> System.out.println(String.format("Ship from flux: %s", ship)),
                error -> System.out.println(String.format("Wopsi some error occured %s", error.getMessage())),
                () -> System.out.println("YES We are done")
                );

        List<SpaceShip> lastShips = List.of(
                new SpaceShip("Round", 30),
                new SpaceShip("Pyramid", 55),
                new SpaceShip("Black Bird", 5)
        );

        Duration duration = StepVerifier.create(flux)
                .expectNext(new SpaceShip("Hawk", 10))
                .expectNext(new SpaceShip("Eagle", 120))
                .expectNextMatches(ship -> ship.getCrew() > 2)
                .expectNextSequence(lastShips)
                .expectComplete()
                .verify();
        System.out.println(String.format("Duration: %s", duration));

        System.out.println("Test END");

    }


}
