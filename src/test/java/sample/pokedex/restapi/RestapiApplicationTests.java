package sample.pokedex.restapi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(TestcontainersConfiguration.class)
class RestapiApplicationTests {

	@Test
	void contextLoads() {
	}

}
