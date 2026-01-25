package sample.pokedex.restapi.pokemon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import sample.pokedex.restapi.TestcontainersConfiguration;

@AutoConfigureTestRestTemplate
@Import({TestcontainersConfiguration.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PokeCtlTest {

    @Autowired
    TestRestTemplate restTemplate;

}
