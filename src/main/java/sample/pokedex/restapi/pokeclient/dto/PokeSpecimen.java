package sample.pokedex.restapi.pokeclient.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

public record PokeSpecimen(
        Integer id,
        @JsonAlias("flavor_text_entries") PokeDescription[] descriptions,
        @JsonAlias("evolution_chain") PokeResource evolutionChain) {
    // XXX maybe a better logic in the future
    public String getDescription() {
        if (descriptions == null || descriptions.length == 0) return "";
        return descriptions[0].text();
    }
}
