package sample.pokedex.restapi.pokemon.dto;

import sample.pokedex.restapi.pokeclient.dto.PokeType;
import sample.pokedex.restapi.usercollection.entity.PokemonType;

import java.time.LocalDateTime;

public record TypeDto(String name) {
    public static TypeDto from(PokeType from) {
        return new TypeDto(from.type().name());
    }

    public static TypeDto from(PokemonType from) {
        return new TypeDto(from.getName());
    }

    public PokemonType patch(PokemonType type) {
        type.setName(name);
        type.setUpdated(LocalDateTime.now());
        return type;
    }
}
