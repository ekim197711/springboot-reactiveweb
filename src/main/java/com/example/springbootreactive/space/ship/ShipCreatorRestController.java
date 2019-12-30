package com.example.springbootreactive.space.ship;

import com.example.springbootreactive.space.SpaceShip;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Random;

@RestController
@RequiredArgsConstructor
public class ShipCreatorRestController {
    private final ReactiveMongoTemplate template;


    @PostMapping(value = "/ship/create"
            , consumes = MediaType.APPLICATION_STREAM_JSON_VALUE
    )
    Mono<String> createShip(Mono<SpaceShip> ships) throws InterruptedException {
        System.out.println(String.format("Start create %s", ships));

        ships.log("atserver.");
        ships.subscribe(ship -> {

                    ship.setId(new Random().nextInt());
                    System.out.println(String.format("Ship: %s", ship));
                }, error -> System.out.println(String.format("error: %s", error.getClass())),
                () -> System.out.println("Donoe..."));
        System.out.println("End create");
        return Mono.just("Done");
    }

    @PostMapping(value = "/ships/create"
            , consumes = {MediaType.APPLICATION_STREAM_JSON_VALUE,
            MediaType.APPLICATION_JSON_VALUE}
    )
    String createShip(@RequestBody Flux<SpaceShip> ships) throws InterruptedException {
        System.out.println(String.format("Start create %s", ships));
        ships.log("atserver.");
        ships.subscribe(ship -> {
                    System.out.println("one element...");
                    ship.setId(new Random().nextInt());
                    System.out.println(String.format("SETTING ID Ship: %s", ship));
                }, error ->{
                    System.out.println(String.format("error: %s", error.getMessage()));
                    error.printStackTrace();
                } ,
                () -> System.out.println("Donoe..."));
//        ships.blockFirst();
        System.out.println("End create");
        return "Flux method done...";
//        return Mono.just("Test 1234");
    }
}
