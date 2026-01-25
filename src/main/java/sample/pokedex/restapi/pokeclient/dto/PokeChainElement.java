package sample.pokedex.restapi.pokeclient.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

public record PokeChainElement(
        PokeResource species,
        @JsonAlias("evolves_to") PokeChainElement[] evolvesTo) {
}
