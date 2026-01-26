package sample.pokedex.restapi.usercollection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import sample.pokedex.restapi.usercollection.dto.InsertPokemonDto;
import sample.pokedex.restapi.usercollection.dto.UpdatePokemonDto;
import sample.pokedex.restapi.usercollection.entity.Pokemon;

import java.net.URI;

@RestController
@RequestMapping("v1/user-collection")
public class UserCollectionCtl {

    private static final Logger LOG = LoggerFactory.getLogger(UserCollectionCtl.class);

    private final UserCollectionSvc service;

    public UserCollectionCtl(UserCollectionSvc service) {
        LOG.info("init");
        this.service = service;
    }

    @GetMapping
    public Page<Pokemon> list(
            @AuthenticationPrincipal Jwt principal,
            @RequestParam(defaultValue = "") String q,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        LOG.debug("list q [{}], page [{}], pageSize [{}]", q, page, pageSize);
        return service.list(principal.getSubject(), q, page, pageSize);
    }

    @GetMapping("{id}")
    public ResponseEntity<Pokemon> find(
            @AuthenticationPrincipal Jwt principal,
            @PathVariable Integer id) {
        LOG.debug("find id [{}]", id);
        return service.find(principal.getSubject(), id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Pokemon> insert(
            @AuthenticationPrincipal Jwt principal,
            @RequestBody InsertPokemonDto data) {
        LOG.debug("insert");
        return service.insert(principal.getSubject(), data)
                .map(p -> ResponseEntity
                        .created(URI.create("/v1/user-collection/" + p.getId())).body(p))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("{id}")
    public ResponseEntity<Pokemon> update(
            @AuthenticationPrincipal Jwt principal,
            @PathVariable Integer id,
            @RequestBody UpdatePokemonDto data) {
        LOG.debug("update");
        return service.update(principal.getSubject(), id, data)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(
            @AuthenticationPrincipal Jwt principal,
            @PathVariable Integer id) {
        LOG.debug("delete");
        return ResponseEntity
                .ok(service.delete(principal.getSubject(), id));
    }

    // list abilities
    // list species
    // list types
}
