package sample.pokedex.restapi.pokemon;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import sample.pokedex.restapi.TestcontainersConfiguration;

@SpringBootTest
@Import({TestcontainersConfiguration.class})
public class PokeCtlTest {


}
