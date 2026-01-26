package sample.pokedex.restapi.usercollection.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sample.pokedex.restapi.usercollection.entity.Species;

import java.util.Optional;

public interface SpeciesRepo extends JpaRepository<Species, Integer> {
    Optional<Species> findByName(String name);
}
