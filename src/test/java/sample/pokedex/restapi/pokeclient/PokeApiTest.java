package sample.pokedex.restapi.pokeclient;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import sample.pokedex.restapi.TestcontainersConfiguration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest
@Import({PokeConfig.class, TestcontainersConfiguration.class})
public class PokeApiTest {

    @Autowired
    PokeApi api;

    @Test
    void shouldList() {
        String result = api.list();
        assertThat(result, notNullValue());
    }
}
