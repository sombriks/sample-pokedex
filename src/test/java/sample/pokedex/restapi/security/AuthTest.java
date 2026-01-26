package sample.pokedex.restapi.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;
import sample.pokedex.restapi.TestcontainersConfiguration;
import sample.pokedex.restapi.security.dto.UserPass;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@AutoConfigureTestRestTemplate
@Import({TestcontainersConfiguration.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    PokeUserRepo repository;

    @Autowired
    PasswordEncoder encoder;

    @Test
    void testUserIsPresent() {
        var userMaybe = repository.findByUsername("user");
        assertThat(userMaybe.isPresent(), is(true));
    }

    @Test
    void ecodePass() {
        var result = encoder.encode("pass");
        assertThat(result, notNullValue());
    }

    @Test
    void shouldLogin() {
        var u = new UserPass("user", "pass");
        var token = restTemplate.postForObject("/v1/auth", u, String.class);
        assertThat(token, notNullValue());
    }

    @Test
    void shouldSignUp() {
        var u = new UserPass("newuser", "newpass");
        var token = restTemplate.postForObject("/v1/sign-up", u, String.class);
        assertThat(token, notNullValue());
        var userMaybe = repository.findByUsername(u.username());
        assertThat(userMaybe.isPresent(), is(true));
        var user = userMaybe.get();
        assertThat(encoder
                .matches(u.password(), user.getPassword()), is(true));
    }
}
