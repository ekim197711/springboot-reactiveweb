package com.example.springbootreactive.space;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpaceShip {
    private Integer id;
    private String name;
    private Integer crew;

    public SpaceShip(String name, Integer crew) {
        this.name = name;
        this.crew = crew;
    }
}
