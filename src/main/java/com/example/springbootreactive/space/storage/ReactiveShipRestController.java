package com.example.springbootreactive.space.storage;

import com.example.springbootreactive.space.SpaceShip;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ReactiveShipRestController {
    private final SpaceShipRepository spaceShipRepository;

    @GetMapping(value = "/api/storage/ships", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<SpaceShip> ships(){
        log.info("Ships BEGIN");
        spaceShipRepository.count().subscribe(result -> log.info("Records: {}", result));
        Flux<SpaceShip> all = spaceShipRepository.findAll();
        log.info("Ships END");
        return all.delayElements(Duration.ofMillis(500));
    }
}
