package sample.pokedex.restapi.pokemon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sample.pokedex.restapi.pokemon.dto.PokemonDto;

@RestController
@RequestMapping("v1/pokemons")
public class PokeCtl {

    private static final Logger LOG = LoggerFactory.getLogger(PokeCtl.class);

    private final PokeSvc service;

    public PokeCtl(PokeSvc service) {
        LOG.info("init");
        this.service = service;
    }

    @GetMapping
    public Page<PokemonDto> list() {
        LOG.debug("list");
        return service.list();
    }

    @GetMapping("{id}")
    public ResponseEntity<PokemonDto> detail(@PathVariable Integer id) {
        LOG.debug("detail id [{}]", id);
        return service.detail(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
