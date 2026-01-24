package sample.pokedex.restapi.pokeclient.dto;

public record PokePage(
        Integer count,
        String next,
        String previous,
        PokeResource[] results
) {
}
