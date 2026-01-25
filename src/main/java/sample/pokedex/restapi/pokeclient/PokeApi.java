package sample.pokedex.restapi.pokeclient;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import sample.pokedex.restapi.pokeclient.dto.PokeChain;
import sample.pokedex.restapi.pokeclient.dto.PokeDetail;
import sample.pokedex.restapi.pokeclient.dto.PokePage;
import sample.pokedex.restapi.pokeclient.dto.PokeSpecimen;

import java.util.Optional;

@HttpExchange
public interface PokeApi {

    @GetExchange("/pokemon")
    PokePage list(@RequestParam int offset, @RequestParam  int limit);

    @GetExchange("/pokemon/{id}")
    Optional<PokeDetail> detail(@PathVariable Integer id);

    @GetExchange("/pokemon-species/{id}")
    Optional<PokeSpecimen> specimen(@PathVariable Integer id);

    @GetExchange("/evolution-chain/{id}")
    Optional<PokeChain> evolutionChain(@PathVariable Integer id);
}
