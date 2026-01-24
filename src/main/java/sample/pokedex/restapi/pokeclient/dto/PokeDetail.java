package sample.pokedex.restapi.pokeclient.dto;

public record PokeDetail(
        Integer id,
        String name,
        PokeSprites sprites,
        PokeType[] types,
        Integer weight,
        PokeAbility[] abilities) {
}
