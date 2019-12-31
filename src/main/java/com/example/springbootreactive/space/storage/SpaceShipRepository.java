package com.example.springbootreactive.space.storage;

import com.example.springbootreactive.space.SpaceShip;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface SpaceShipRepository extends ReactiveCrudRepository<SpaceShip, String> {
}
