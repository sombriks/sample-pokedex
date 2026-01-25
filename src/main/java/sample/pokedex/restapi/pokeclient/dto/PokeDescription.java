package sample.pokedex.restapi.pokeclient.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

public record PokeDescription(
        @JsonAlias("flavor_text") String text,
        PokeResource language,
        PokeResource version) {
}
