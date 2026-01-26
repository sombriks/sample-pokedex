package sample.pokedex.restapi.usercollection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import sample.pokedex.restapi.security.PokeUserRepo;
import sample.pokedex.restapi.security.entity.PokeUser;
import sample.pokedex.restapi.usercollection.entity.Pokemon;
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
        Optional<PokeUser> userMaybe = pokeUserRepo.findByUsername(subject);
        return Page.empty();
    }
}
