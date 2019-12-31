package com.example.springbootreactive.space.storage;

import com.example.springbootreactive.space.SpaceShip;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class SpaceShipInitData {
    private final SpaceShipRepository spaceShipRepository;
    private final ReactiveMongoTemplate template;


    void initSpaceShipsWithTemplate() {
        List<SpaceShip> ships = new ArrayList<>();
        for (int i = 0; i < 1000; i++){
            ships.add(new SpaceShip("reactivetemplate" + i, Math.abs(new Random().nextInt()%1000)));
        }
        Flux<SpaceShip> spaceShipFlux = template.insertAll(ships);
        spaceShipFlux.subscribe();
    }

    @PostConstruct
    void initSpaceShips(){
        log.info("initSpaceShips BEGIN");
        Mono<Void> deleteMono = spaceShipRepository.deleteAll();
        deleteMono.subscribe();
        List<SpaceShip> ships = new ArrayList<>();
        for (int i = 0; i < 1000; i++){
            ships.add(new SpaceShip("generated" + i, Math.abs(new Random().nextInt()%1000)));
        }

        Flux<SpaceShip> spaceShipFlux = spaceShipRepository.saveAll(ships);
        spaceShipFlux.subscribe();
        initSpaceShipsWithTemplate();
        log.info("initSpaceShips END");
    }
}










