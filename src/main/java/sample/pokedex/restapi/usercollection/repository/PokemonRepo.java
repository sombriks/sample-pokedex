package sample.pokedex.restapi.usercollection.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sample.pokedex.restapi.usercollection.entity.Pokemon;

import java.util.Optional;

public interface PokemonRepo extends JpaRepository<Pokemon, Integer> {

    @Query("""
            select p from Pokemon p
             where p.user.username = :username
               and p.id = :id""")
    Optional<Pokemon> findPokemon(String username, Integer id);

    @Query("""
            select p from Pokemon p
             where p.user.username = :username
               and ' '||p.name||' ' ilike  '%'||:q||'%'""")
    Page<Pokemon> search(String username, String q, Pageable name);
}
