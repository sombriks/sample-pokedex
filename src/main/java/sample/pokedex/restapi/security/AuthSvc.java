package sample.pokedex.restapi.security;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import sample.pokedex.restapi.security.dto.UserPass;
import sample.pokedex.restapi.security.entity.PokeUser;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
public class AuthSvc {

    private static final Logger LOG = LoggerFactory.getLogger(AuthSvc.class);

    private final AuthRepo repository;
    private final JwtEncoder jwtEncoder;
    private final PasswordEncoder passwordEncoder;

    public AuthSvc(
            AuthRepo repository,
            JwtEncoder jwtEncoder,
            PasswordEncoder passwordEncoder) {
        LOG.info("init");
        this.repository = repository;
        this.jwtEncoder = jwtEncoder;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<String> auth(UserPass credentials) {
        LOG.debug("auth");
        Optional<PokeUser> userMaybe = repository
                .findByUsername(credentials.username());
        if (userMaybe.isEmpty()) return Optional.empty();
        PokeUser user = userMaybe.get();
        if (!passwordEncoder.matches(
                credentials.password(),
                user.getPassword())) return Optional.empty();
        return makeToken(user);
    }

    @Transactional
    public Optional<String> signUp(UserPass credentials) {
        try {
            PokeUser user = credentials.patch(new PokeUser());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setCreated(LocalDateTime.now());
            repository.save(user);
            return makeToken(user);
        } catch (Exception ex) {
            LOG.warn("unexpected error", ex);
            return Optional.empty();
        }
    }

    private Optional<String> makeToken(PokeUser user) {
        // now prepare to build the token
        Instant now = Instant.now();
        Instant exp = now.plus(1, ChronoUnit.DAYS);
        // XXX expand in the future, if we want roles or something
        String scope = "user";
        // JWT claims
        JwtClaimsSet claims = JwtClaimsSet
                .builder()
                .issuedAt(now)
                .expiresAt(exp)
                .issuer("sample pokedex - example issuer")
                .subject(user.getUsername())
                .claim("scope", scope)
                .build();
        // finally return the token
        return Optional.of(jwtEncoder
                .encode(JwtEncoderParameters.from(claims))
                .getTokenValue());
    }
}
