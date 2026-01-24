package sample.pokedex.restapi.pokemon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import sample.pokedex.restapi.pokeclient.PokeApi;
import sample.pokedex.restapi.pokemon.dto.PokemonDto;

import java.util.Arrays;
import java.util.Optional;

@Service
public class PokeSvc {

    private static final Logger LOG = LoggerFactory.getLogger(PokeSvc.class);

    private final PokeApi api;

    public PokeSvc(PokeApi api) {
        LOG.info("init");
        this.api = api;
    }

    public Page<PokemonDto> list(int page, int pageSize) {
        LOG.debug("list poage [{}], pageSize [{}]", page, pageSize);
        int offset = (page -1) * pageSize;
        var pokemons = api.list(offset, pageSize);
        return new PageImpl<PokemonDto>(
                Arrays.stream(pokemons.results())
                        .map(r ->
                                detail(r.extractId()))
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .toList(),
                PageRequest.of(page, pageSize, Sort.by("id")),
                pokemons.count());
    }

    public Optional<PokemonDto> detail(int id) {
        LOG.debug("detail id [{}]", id);
        if (id <= 0) return Optional.empty();
        try {
            return api.detail(id)
                    .map(PokemonDto::from);
        } catch (Exception ex) {
            LOG.warn("unexpected error ", ex);
            return Optional.empty();
        }
    }
}
