package sample.pokedex.restapi.pokeclient;

import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange(url = "/pokemon")
public interface PokeApi {

    @GetExchange
    String list();
}
