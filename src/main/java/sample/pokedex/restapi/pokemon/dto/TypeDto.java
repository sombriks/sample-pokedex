package sample.pokedex.restapi.pokemon.dto;

import sample.pokedex.restapi.pokeclient.dto.PokeType;
import sample.pokedex.restapi.usercollection.entity.PokemonType;

public record TypeDto(String name) {
    public static TypeDto from(PokeType from) {
        return new TypeDto(from.type().name());
    }

    public static TypeDto from(PokemonType from) {
        return new TypeDto(from.getName());
    }
}
