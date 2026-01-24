package sample.pokedex.restapi.pokeclient;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import sample.pokedex.restapi.pokeclient.dto.PokeDetail;
import sample.pokedex.restapi.pokeclient.dto.PokePage;

@HttpExchange(url = "/pokemon")
public interface PokeApi {

    @GetExchange
    PokePage list();

    @GetExchange("{id}")
    PokeDetail detail(@PathVariable Integer id);
}
