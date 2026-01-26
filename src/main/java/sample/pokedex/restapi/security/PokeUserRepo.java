package sample.pokedex.restapi.security;

import org.springframework.data.jpa.repository.JpaRepository;
import sample.pokedex.restapi.security.entity.PokeUser;

import java.util.Optional;

public interface PokeUserRepo extends JpaRepository<PokeUser, Integer> {
    Optional<PokeUser> findByUsername(String username);
}
