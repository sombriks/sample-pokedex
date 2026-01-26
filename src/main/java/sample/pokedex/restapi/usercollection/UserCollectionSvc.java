package sample.pokedex.restapi.usercollection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
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

    public Page<Pokemon> list(String subject, String q, int page, int pageSize) {
        LOG.debug("list");
        return Page.empty();
    }

    public Optional<Pokemon> find(String subject, int id) {
        LOG.debug("find");
        return Optional.empty();
    }

    public Pokemon insert(String subject, InsertPokemonDto data) {
        LOG.debug("insert");
        Optional<PokeUser> userMaybe = pokeUserRepo.findByUsername(subject);
        return null;
    }

    public Pokemon update(String subject, int id, UpdatePokemonDto data) {
        LOG.debug("update");
        Optional<PokeUser> userMaybe = pokeUserRepo.findByUsername(subject);
        return null;
    }

    public int delete(String subject, int id) {
        LOG.debug("delete");
        return 0;
    }

    public Page<Ability> listAbilities(String q, int page, int pageSize) {
        LOG.debug("listAbilities");
        return Page.empty();
    }

    public Page<Species> listSpecies(String q, int page, int pageSize) {
        LOG.debug("listSpecies");
        return Page.empty();
    }

    public Page<PokemonType> listTypes(String q, int page, int pageSize) {
        LOG.debug("listTypes");
        return Page.empty();
    }
}
