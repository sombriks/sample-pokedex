package sample.pokedex.restapi.usercollection.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.type.SqlTypes;
import sample.pokedex.restapi.security.entity.PokeUser;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "pokemons")
public class Pokemon {
    @ManyToMany
    @JoinTable(name = "pokemons_types", joinColumns = {@JoinColumn(name = "pokemons_id")}, inverseJoinColumns = {@JoinColumn(name = "types_id")})
    private Set<PokemonType> types = new LinkedHashSet<>();
    @ManyToMany
    @JoinTable(name = "pokemons_abilities", joinColumns = {@JoinColumn(name = "pokemons_id")}, inverseJoinColumns = {@JoinColumn(name = "abilities_id")})
    private Set<Ability> abilities = new LinkedHashSet<>();
    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "updated", nullable = false)
    private LocalDateTime updated;
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "details")
    private Map<String, Object> details;
    @Column(name = "weight")
    private Integer weight;
    @Size(max = 255)
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "poke_users_id", nullable = false)
    private PokeUser user;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "species_id", nullable = false)
    private Species species;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    public Pokemon(PokeUser user, Species species) {
        this.user = user;
        this.species = species;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<PokemonType> getTypes() {
        return types;
    }

    public void setTypes(Set<PokemonType> types) {
        this.types = types;
    }

    public Set<Ability> getAbilities() {
        return abilities;
    }

    public void setAbilities(Set<Ability> abilities) {
        this.abilities = abilities;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public Map<String, Object> getDetails() {
        return details;
    }

    public void setDetails(Map<String, Object> details) {
        this.details = details;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PokeUser getUser() {
        return user;
    }

    public void setUser(PokeUser user) {
        this.user = user;
    }

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }

    public Integer getId() {
        return id;
    }

    public String getImageUrl() {
        return species != null
                ? species.getImageUrl()
                : null;
    }

    public String getDescription() {
        return species != null
                ? species.getDescription()
                : null;
    }
}
