package sample.pokedex.restapi.pokeclient.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

public record PokeChain(
        Integer id,
        PokeResource species,
        @JsonAlias("evolves_to") PokeChain[] evolvesTo) {
}
