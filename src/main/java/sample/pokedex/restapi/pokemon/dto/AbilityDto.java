package sample.pokedex.restapi.pokemon.dto;

import sample.pokedex.restapi.pokeclient.dto.PokeAbility;

public record AbilityDto(String name) {
    public static AbilityDto from(PokeAbility from) {
        return new AbilityDto(from.ability().name());
    }
}
