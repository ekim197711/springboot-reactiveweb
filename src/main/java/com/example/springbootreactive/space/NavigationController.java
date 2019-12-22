package com.example.springbootreactive.space;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
public class NavigationController {

    @GetMapping(value = "/destinations", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<String> destinations(){
        return Flux.just(
                "Mars",
                "Earth",
                "Venus",
                "Mercury",
                "Jupiter",
                "Saturn",
                "Pluto"
        ).delayElements(Duration.ofSeconds(3));
    }
    @GetMapping(value = "/ships", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<SpaceShip> ships(){
        return Flux.just(
                new SpaceShip("Hawk", 10),
                new SpaceShip("Eagle", 120),
                new SpaceShip("Swan", 20),
                new SpaceShip("Round", 30),
                new SpaceShip("Pyramid", 55),
                new SpaceShip("Black Bird", 5)
        ).delayElements(Duration.ofSeconds(3));
    }



}
