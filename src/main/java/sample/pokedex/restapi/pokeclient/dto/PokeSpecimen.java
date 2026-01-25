package sample.pokedex.restapi.pokeclient.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

public record PokeSpecimen(
        Integer id,
        @JsonAlias("flavor_text_entries") PokeDescription[] descriptions,
        @JsonAlias("evolution_chain") PokeResource evolutionChain) {
}
