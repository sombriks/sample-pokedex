package sample.pokedex.restapi.usercollection.dto;

import sample.pokedex.restapi.pokemon.dto.AbilityDto;
import sample.pokedex.restapi.pokemon.dto.TypeDto;
import sample.pokedex.restapi.usercollection.entity.Ability;
import sample.pokedex.restapi.usercollection.entity.Pokemon;
import sample.pokedex.restapi.usercollection.entity.PokemonType;
import sample.pokedex.restapi.usercollection.entity.Species;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public record UpdatePokemonDto(
        Integer id,
        String name,
        Integer weight,
        String imageUrl,
        String speciesName,
        String description,
        List<TypeDto> types,
        List<AbilityDto> abilities

) {
    public Pokemon patch(Pokemon pokemon, Species species, Set<Ability> abilities, Set<PokemonType> types) {
        pokemon.setName(name);
        pokemon.setWeight(weight);
        pokemon.setSpecies(species);
        pokemon.setAbilities(abilities);
        pokemon.setTypes(types);
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
