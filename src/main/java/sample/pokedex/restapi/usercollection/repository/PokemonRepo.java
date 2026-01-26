package sample.pokedex.restapi.usercollection.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sample.pokedex.restapi.usercollection.entity.Pokemon;

public interface PokemonRepo extends JpaRepository<Pokemon, Integer> {
}
