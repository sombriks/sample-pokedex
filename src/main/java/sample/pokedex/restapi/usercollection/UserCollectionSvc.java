package sample.pokedex.restapi.usercollection;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import sample.pokedex.restapi.pokemon.dto.PokemonDto;
import sample.pokedex.restapi.security.PokeUserRepo;
import sample.pokedex.restapi.security.entity.PokeUser;
import sample.pokedex.restapi.usercollection.dto.InsertPokemonDto;
import sample.pokedex.restapi.usercollection.dto.UpdatePokemonDto;
import sample.pokedex.restapi.usercollection.entity.Ability;
import sample.pokedex.restapi.usercollection.entity.Pokemon;
import sample.pokedex.restapi.usercollection.entity.PokemonType;
import sample.pokedex.restapi.usercollection.entity.Species;
import sample.pokedex.restapi.usercollection.repository.AbilityRepo;
import sample.pokedex.restapi.usercollection.repository.PokemonRepo;
import sample.pokedex.restapi.usercollection.repository.PokemonTypeRepo;
import sample.pokedex.restapi.usercollection.repository.SpeciesRepo;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserCollectionSvc {

    private static final Logger LOG = LoggerFactory.getLogger(UserCollectionSvc.class);

    private final AbilityRepo abilityRepo;
    private final PokemonRepo pokemonRepo;
    private final PokemonTypeRepo pokemonTypeRepo;
    private final PokeUserRepo pokeUserRepo;
    private final SpeciesRepo speciesRepo;

    public UserCollectionSvc(
            AbilityRepo abilityRepo,
            PokemonRepo pokemonRepo,
            PokemonTypeRepo pokemonTypeRepo,
            PokeUserRepo pokeUserRepo,
            SpeciesRepo speciesRepo) {
        LOG.info("init");
        this.abilityRepo = abilityRepo;
        this.pokemonRepo = pokemonRepo;
        this.pokemonTypeRepo = pokemonTypeRepo;
        this.pokeUserRepo = pokeUserRepo;
        this.speciesRepo = speciesRepo;
    }

    public Page<PokemonDto> list(String subject, String query, int page, int pageSize) {
        LOG.debug("list");
        Pageable p = PageRequest.of(page, pageSize, Sort.by("name"));
        return pokemonRepo
                .search(subject, query, p)
                .map(PokemonDto::from);
    }

    public Optional<PokemonDto> find(String subject, int id) {
        LOG.debug("find");
        return pokemonRepo
                .findPokemon(subject, id)
                .map(PokemonDto::from);
    }

    @Transactional
    public Optional<Pokemon> insert(String subject, InsertPokemonDto data) {
        LOG.debug("insert");
        Optional<PokeUser> userMaybe = pokeUserRepo.findByUsername(subject);
        if (userMaybe.isEmpty()) return Optional.empty();
        Species species = speciesRepo
                .findByName(data.speciesName())
                .orElseGet(() -> data.patch(new Species()));
        Set<Ability> abilities = data.abilities() != null
                ? data.abilities()
                .stream()
                .map(a -> abilityRepo
                        .findByName(a.name())
                        .orElseGet(() -> a.patch(new Ability())))
                .collect(Collectors.toSet())
                : null;
        Set<PokemonType> types = data.types() != null
                ? data.types()
                .stream()
                .map(t -> pokemonTypeRepo
                        .findByName(t.name())
                        .orElseGet(() -> t.patch(new PokemonType())))
                .collect(Collectors.toSet())
                : null;
        Pokemon pokemon = data
                .patch(new Pokemon(userMaybe.get(), species, abilities, types));
        pokemonRepo.save(pokemon);
        return Optional.of(pokemon);
    }

    @Transactional
    public Optional<Pokemon> update(String subject, int id, UpdatePokemonDto data) {
        LOG.debug("update");
        Optional<Pokemon> pokemonMaybe = pokemonRepo.findPokemon(subject, id);
        if (pokemonMaybe.isEmpty()) return Optional.empty();
        Species species = speciesRepo
                .findByName(data.speciesName())
                .orElseGet(() -> data.patch(new Species()));
        Set<Ability> abilities = data.abilities() != null
                ? data.abilities()
                .stream()
                .map(a -> abilityRepo
                        .findByName(a.name())
                        .orElseGet(() -> a.patch(new Ability())))
                .collect(Collectors.toSet())
                : null;
        Set<PokemonType> types = data.types() != null
                ? data.types()
                .stream()
                .map(t -> pokemonTypeRepo
                        .findByName(t.name())
                        .orElseGet(() -> t.patch(new PokemonType())))
                .collect(Collectors.toSet())
                : null;
        Pokemon pokemon = data.patch(pokemonMaybe.get(), species, abilities, types);

        pokemonRepo.save(pokemon);
        return Optional.of(pokemon);
    }

    @Transactional
    public int delete(String subject, int id) {
        LOG.debug("delete");
        Optional<Pokemon> pokemonMaybe = pokemonRepo.findPokemon(subject, id);
        if (pokemonMaybe.isEmpty()) return 0;
        pokemonRepo.delete(pokemonMaybe.get());
        return 1;
    }

}
