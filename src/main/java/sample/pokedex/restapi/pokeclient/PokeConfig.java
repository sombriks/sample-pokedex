package sample.pokedex.restapi.pokeclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class PokeConfig {

    @Bean
    public PokeApi pokeApi(@Value("${spring.pokeapi}") String pookeapi) {
        RestClient restClient = RestClient.builder()
                .baseUrl(pookeapi)
                .build();
        HttpServiceProxyFactory factory = HttpServiceProxyFactory
                .builderFor(RestClientAdapter.create(restClient))
                .build();
        return factory.createClient(PokeApi.class);
    }
}
