package sample.pokedex.restapi.security.dto;

import sample.pokedex.restapi.security.entity.PokeUser;

public record UserPass(String username, String password) {
    public PokeUser patch(PokeUser pokeUser) {
        pokeUser.setUsername(username);
        pokeUser.setPassword(password);
        return pokeUser;
    }
}
