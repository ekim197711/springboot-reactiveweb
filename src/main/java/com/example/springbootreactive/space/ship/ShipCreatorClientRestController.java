package com.example.springbootreactive.space.ship;

import com.example.springbootreactive.space.SpaceShip;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Random;

@RestController
public class ShipCreatorClientRestController {

    private Flux<SpaceShip> flux(){
        return Flux.just(
                new SpaceShip(-1, "test 1", 10),
                new SpaceShip(-1, "test 2", 20),
                new SpaceShip(-1, "test 3", 30),
                new SpaceShip(-1, "test 4", 40))
                .log("server.request.");
    }

    @GetMapping(value = "/ships/create/try", produces = MediaType.TEXT_HTML_VALUE)
    String tryCreates(){
        System.out.println("First flux");
        flux().log("firstflux.");
        System.out.println("Start try ship");
        WebClient wc = WebClient.create("http://localhost:8080");
        wc.post()
                .uri("/ships/create")
//                 .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_STREAM_JSON)
                .body(flux(),SpaceShip.class)
//                flux(),SpaceShip.class)
                .retrieve()
                .bodyToFlux(String.class)
                .subscribe(str ->
                        System.out.println(String.format("Response: %s",str ))
                );
//        spaceShipFlux.subscribe(ship -> String.format("Received %s", ship));
        System.out.println("End try ship ");
        return "Done";
    }

    @GetMapping(value = "/ship/create/try", produces = MediaType.TEXT_HTML_VALUE)
    String tryCreate(){
        System.out.println("First flux");
        flux().log("firstflux.");
        System.out.println("Start try ship");
        WebClient wc = WebClient.create("http://localhost:8080");
         wc.post()
                .uri("/ship/create")
//                 .accept(MediaType.APPLICATION_JSON)
                 .contentType(MediaType.APPLICATION_STREAM_JSON)
                 .body(Mono.just(new SpaceShip(-1, "test 2", 20)),SpaceShip.class)
//                flux(),SpaceShip.class)
                .retrieve()
                 .bodyToMono(String.class)
                 .subscribe(str ->
                System.out.println(String.format("Response: %s",str ))
        );
//        spaceShipFlux.subscribe(ship -> String.format("Received %s", ship));
        System.out.println("End try ship ");
        return "Done";
    }
}
