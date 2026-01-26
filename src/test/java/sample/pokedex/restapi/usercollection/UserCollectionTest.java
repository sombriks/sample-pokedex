package sample.pokedex.restapi.usercollection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import sample.pokedex.restapi.TestcontainersConfiguration;
import sample.pokedex.restapi.security.dto.UserPass;

@AutoConfigureTestRestTemplate
@Import({TestcontainersConfiguration.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserCollectionTest {

    @Autowired
    TestRestTemplate restTemplate;

    HttpHeaders headers;

    @BeforeEach
    void setup() {
        var u = new UserPass("user", "pass");
        var token = restTemplate.postForObject("/v1/auth", u, String.class);
        headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + token);
    }

    @Test
    void shouldList() {
    }

    @Test
    void shouldFind() {
    }

    @Test
    void shouldInsert() {
    }

    @Test
    void shouldUpdate() {
    }

    @Test
    void shouldDelete() {
    }

}
