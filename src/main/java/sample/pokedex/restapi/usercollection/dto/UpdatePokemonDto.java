package sample.pokedex.restapi.usercollection.dto;

import sample.pokedex.restapi.usercollection.entity.Pokemon;

public record UpdatePokemonDto() {
    public Pokemon patch(Pokemon pokemon) {
        return pokemon;
    }
}
