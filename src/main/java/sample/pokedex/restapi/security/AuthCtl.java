package sample.pokedex.restapi.security;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sample.pokedex.restapi.security.dto.UserPass;

import java.net.URI;

@RestController
public class AuthCtl {

    private static final Logger LOG = LoggerFactory.getLogger(AuthCtl.class);

    private final AuthSvc service;

    public AuthCtl(AuthSvc service) {
        LOG.info("init");
        this.service = service;
    }

    @PostMapping("v1/auth")
    public ResponseEntity<String> auth(@RequestBody @Valid UserPass credentials) {
        LOG.debug("auth [{}]",credentials.username());
        return service.auth(credentials)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity
                        .status(HttpStatus.UNAUTHORIZED)
                        .build());
    }

    @PostMapping("v1/sign-up")
    public ResponseEntity<String> signUp(@RequestBody @Valid UserPass credentials) {
        LOG.debug("signUp [{}]",credentials.username());
        return service.signUp(credentials)
                .map(token -> ResponseEntity
                        .created(URI.create("/v1/auth"))
                        .body(token))
                .orElseGet(() -> ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .build());
    }
}
