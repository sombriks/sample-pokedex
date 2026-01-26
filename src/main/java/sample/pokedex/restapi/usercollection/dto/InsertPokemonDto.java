package sample.pokedex.restapi.usercollection.dto;

import sample.pokedex.restapi.pokemon.dto.AbilityDto;
import sample.pokedex.restapi.pokemon.dto.TypeDto;
import sample.pokedex.restapi.usercollection.entity.Pokemon;
import sample.pokedex.restapi.usercollection.entity.Species;

import java.time.LocalDateTime;
import java.util.List;

public record InsertPokemonDto(
        Integer id,
        String name,
        Integer weight,
        String imageUrl,
        String speciesName,
        String description,
        List<TypeDto> types,
        List<AbilityDto> abilities) {
    public Pokemon patch(Pokemon pokemon) {
        pokemon.setName(name);
        pokemon.setWeight(weight);
        pokemon.setUpdated(LocalDateTime.now());
        return pokemon;
    }

    public Species patch(Species species) {
        species.setName(speciesName);
        species.setImageUrl(imageUrl);
        species.setDescription(description);
        species.setUpdated(LocalDateTime.now());
        return species;
    }

}
