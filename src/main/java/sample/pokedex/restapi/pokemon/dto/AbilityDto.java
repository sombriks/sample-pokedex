package sample.pokedex.restapi.pokemon.dto;

import sample.pokedex.restapi.pokeclient.dto.PokeAbility;
import sample.pokedex.restapi.usercollection.entity.Ability;

public record AbilityDto(String name) {
    public static AbilityDto from(PokeAbility from) {
        return new AbilityDto(from.ability().name());
    }

    public static AbilityDto from(Ability from) {
        return new AbilityDto(from.getName());
    }
}
