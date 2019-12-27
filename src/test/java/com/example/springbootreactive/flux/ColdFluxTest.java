package com.example.springbootreactive.flux;

import com.example.springbootreactive.space.SpaceShip;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;

public class ColdFluxTest {

    private Flux<SpaceShip> flux(){

//        List<SpaceShip> spaceShips = List.of(new SpaceShip("Hawk", 10),
//                new SpaceShip("Eagle", 120),
//                new SpaceShip("Swan", 20),
//                new SpaceShip("Round", 30),
//                new SpaceShip("Pyramid", 55),
//                new SpaceShip("Black Bird", 5));

        return Flux.just(
                new SpaceShip("Hawk", 10),
                new SpaceShip("Eagle", 120),
                new SpaceShip("Swan", 20),
                new SpaceShip("Round", 30),
                new SpaceShip("Pyramid", 55),
                new SpaceShip("Black Bird", 5)
        )
                .delayElements(Duration.ofSeconds(3))
                .map(ship -> {
                    if (ship.getName().equals("Round"))
                        throw new RuntimeException(String.format("The ship is round!!! %s", ship));
                    ship.setCrew(ship.getCrew()*2);
                    return ship;
                })
                ;
    }

    @Test
    void testTheFlux() throws InterruptedException {
        System.out.println("Test BEGIN");
        Flux<SpaceShip> flux = flux();
        flux.subscribe(new SpaceShipSubscriber<>());
        flux.subscribe(
                ship -> System.out.println(String.format("Ship from flux: %s", ship)),
                error -> System.out.println(String.format("Wopsi some error occured %s", error.getMessage())),
                () -> System.out.println("YES We are done")
                );


        Thread.sleep(20000);
        System.out.println("Test END");

    }


}
