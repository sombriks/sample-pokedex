package sample.pokedex.restapi.pokeclient;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import sample.pokedex.restapi.pokeclient.dto.PokeDetail;
import sample.pokedex.restapi.pokeclient.dto.PokePage;

import java.util.Optional;

@HttpExchange(url = "/pokemon")
public interface PokeApi {

    @GetExchange
    PokePage list(@RequestParam int offset, @RequestParam  int limit);

    @GetExchange("{id}")
    Optional<PokeDetail> detail(@PathVariable Integer id);
}
