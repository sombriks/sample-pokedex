package sample.pokedex.restapi.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import sample.pokedex.restapi.security.dto.UserPass;

import java.util.Optional;

@Service
public class AuthSvc {

    private static final Logger LOG = LoggerFactory.getLogger(AuthSvc.class);

    public AuthSvc(){}

    Optional<String> auth(UserPass credentials) {
        LOG.debug("auth");
        return Optional.empty();
    }
}
