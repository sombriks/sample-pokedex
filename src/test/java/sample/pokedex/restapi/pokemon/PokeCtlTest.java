package sample.pokedex.restapi.pokemon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import sample.pokedex.restapi.TestcontainersConfiguration;
import sample.pokedex.restapi.pokemon.dto.PokemonDto;
import sample.pokedex.restapi.security.dto.UserPass;

import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@AutoConfigureTestRestTemplate
@Import({TestcontainersConfiguration.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PokeCtlTest {

    @Autowired
    TestRestTemplate restTemplate;

    HttpEntity<Void> headerEntity;

    @BeforeEach
    void setup() {
        var u = new UserPass("user", "pass");
        var token = restTemplate.postForObject("/v1/auth", u, String.class);
        var headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + token);
        headerEntity = new HttpEntity<Void>(headers);
    }

    @Test
    void shouldList() {
        var result = restTemplate.exchange("/v1/pokemons", HttpMethod.GET, headerEntity, HashMap.class);
        assertThat(result.getStatusCode().is2xxSuccessful(), is(true));
        assertThat(result.hasBody(), is(true));
        assertThat(result.getBody().get("content"), notNullValue());
    }

    @Test
    void shouldFind() {
        var result = restTemplate.exchange("/v1/pokemons/4", HttpMethod.GET, headerEntity, PokemonDto.class);
        assertThat(result.getStatusCode().is2xxSuccessful(), is(true));
        assertThat(result.hasBody(), is(true));
        assertThat(result.getBody().name(), is("charmander"));
    }
}
