package sample.pokedex.restapi.pokeclient;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import sample.pokedex.restapi.TestcontainersConfiguration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@Import({TestcontainersConfiguration.class})
public class PokeApiTest {

    @Autowired
    PokeApi api;

    @Test
    void shouldList() {
        var result = api.list(0,10);
        assertThat(result, notNullValue());
        assertThat(result.results(), notNullValue());
        assertThat(result.results().length, not(is(0)));
    }

    @Test
    void shouldDetail() {
        var result = api.detail(1);
        assertThat(result, notNullValue());
        assertThat(result.isPresent(), is(true));
    }
}
