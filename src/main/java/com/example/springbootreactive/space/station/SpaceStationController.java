package com.example.springbootreactive.space.station;

import com.example.springbootreactive.space.SpaceShip;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
public class SpaceStationController {
    @Qualifier("navigation-web-client")
    private final WebClient webClient;

    @GetMapping(value = "/spacestation/nondockedships",
            produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<SpaceShip> nondockedships(){


        Flux<SpaceShip> spaceShipFlux = webClient
                .get()
                .uri("/ships")
                .retrieve()
                .bodyToFlux(SpaceShip.class);

        spaceShipFlux.subscribe(ship -> {
            System.out.println(String.format("A ship has arrived. Watch out. %s", ship));
        });

        return spaceShipFlux;
    }

}
