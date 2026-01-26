package sample.pokedex.restapi.usercollection.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sample.pokedex.restapi.usercollection.entity.PokemonType;

import java.util.Optional;

public interface PokemonTypeRepo extends JpaRepository <PokemonType,Integer> {
    Optional<PokemonType> findByName(String name);
}
