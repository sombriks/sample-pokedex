package sample.pokedex.restapi.pokemon.dto;

import sample.pokedex.restapi.pokeclient.dto.PokeChain;
import sample.pokedex.restapi.pokeclient.dto.PokeChainElement;
import sample.pokedex.restapi.pokeclient.dto.PokeDetail;
import sample.pokedex.restapi.pokeclient.dto.PokeSpecimen;

import java.util.Arrays;
import java.util.List;

public record PokemonDto(
        Integer id,
        String name,
        String imageUrl,
        List<TypeDto> types,
        Integer weight,
        List<AbilityDto> abilities,
        String description,
        List<PokemonDto> evolutions) {
    public static PokemonDto from(PokeDetail detail) {
        return from(detail, null, null);
    }

    public static PokemonDto from(
            PokeDetail detail,
            PokeSpecimen specimen,
            PokeChain evolutions) {
        return new PokemonDto(
                detail.id(),
                detail.name(),
                detail.sprites().frontDefault(),
                Arrays.stream(detail.types())
                        .map(TypeDto::from)
                        .toList(),
                detail.weight(),
                Arrays.stream(detail.abilities())
                        .map(AbilityDto::from)
                        .toList(),
                specimen == null ? null : specimen.getDescription(),
                evolutions != null
                        ? evolutions.chain() != null
                        ? Arrays
                        .stream(evolutions.chain().evolvesTo())
                        .map(PokemonDto::from)
                        .toList() : null : null);
    }

    public static PokemonDto from(PokeChainElement chain) {
        return new PokemonDto(
                chain.species().extractId(),
                chain.species().name(),
                null,
                null,
                null,
                null,
                null,
                Arrays.stream(chain.evolvesTo())
                        .map(PokemonDto::from)
                        .toList());
    }
}
