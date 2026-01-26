package sample.pokedex.restapi.usercollection.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sample.pokedex.restapi.usercollection.entity.Ability;

import java.util.Optional;

public interface AbilityRepo extends JpaRepository<Ability, Integer> {
    Optional<Ability> findByName(String name);
}
