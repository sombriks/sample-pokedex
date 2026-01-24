package sample.pokedex.restapi.pokeclient.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

public record PokeAbility(PokeResource ability, @JsonAlias("is_hidden") Boolean isHidden, Integer slot) {
}
