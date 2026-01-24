package sample.pokedex.restapi.pokemon.dto;

import sample.pokedex.restapi.pokeclient.dto.PokeType;

public record TypeDto(String name) {
    public static TypeDto from(PokeType from) {
        return new TypeDto(from.type().name());
    }
}
