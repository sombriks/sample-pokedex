package sample.pokedex.restapi.usercollection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import sample.pokedex.restapi.usercollection.entity.Pokemon;
import sample.pokedex.restapi.usercollection.repository.PokemonRepo;

@Service
public class UserCollectionSvc {

    private static final Logger LOG = LoggerFactory.getLogger(UserCollectionSvc.class);

    private final PokemonRepo pokemonRepository;

    public UserCollectionSvc(PokemonRepo pokemonRepository) {
        LOG.info("init");
        this.pokemonRepository = pokemonRepository;
    }

    public Page<Pokemon> list() {
        LOG.debug("list");
        return Page.empty();
    }
}
