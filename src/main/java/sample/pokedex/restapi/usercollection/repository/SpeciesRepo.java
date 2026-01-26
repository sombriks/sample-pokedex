package sample.pokedex.restapi.usercollection.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sample.pokedex.restapi.usercollection.entity.Species;

public interface SpeciesRepo extends JpaRepository<Species, Integer> {
}
