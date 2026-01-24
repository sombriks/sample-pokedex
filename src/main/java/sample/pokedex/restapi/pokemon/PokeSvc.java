package sample.pokedex.restapi.pokemon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import sample.pokedex.restapi.pokeclient.PokeApi;
import sample.pokedex.restapi.pokemon.dto.PokemonDto;

import java.util.Optional;

@Service
public class PokeSvc {

    private static final Logger LOG = LoggerFactory.getLogger(PokeSvc.class);

    private final PokeApi api;

    public PokeSvc(PokeApi api){
        LOG.info("init");
        this.api = api;
    }

    public Page<PokemonDto> list() {
        LOG.debug("list");
        return Page.empty();
    }

    public Optional<PokemonDto> detail(Integer id) {
        LOG.debug("detail id [{}]", id);
        try {
            return api.detail(id)
                    .map(PokemonDto::from);
        } catch (Exception ex) {
            LOG.warn("unexpected error ", ex);
            return Optional.empty();
        }
    }
}
