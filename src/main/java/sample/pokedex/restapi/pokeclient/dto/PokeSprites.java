package sample.pokedex.restapi.pokeclient.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

public record PokeSprites(@JsonAlias("front_default") String frontDefault) {
}
