package sample.pokedex.restapi.pokemon.dto;

import sample.pokedex.restapi.pokeclient.dto.PokeDetail;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public record PokemonDto(
        Integer id,
        String name,
        String imageUrl,
        List<TypeDto> types,
        Integer weight,
        List<AbilityDto> abilities
) {
    public static PokemonDto from(PokeDetail from) {
        return new PokemonDto(
                from.id(),
                from.name(),
                from.sprites().frontDefault(),
                Arrays.stream(from.types())
                        .map(TypeDto::from)
                        .collect(Collectors.toList()),
                from.weight(),
                Arrays.stream(from.abilities())
                        .map(AbilityDto::from)
                        .collect(Collectors.toList()));
    }
}
