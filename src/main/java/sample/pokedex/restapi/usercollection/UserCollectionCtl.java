package sample.pokedex.restapi.usercollection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sample.pokedex.restapi.usercollection.entity.Pokemon;

@RestController
@RequestMapping("v1/user-collection")
public class UserCollectionCtl {

    private static final Logger LOG = LoggerFactory.getLogger(UserCollectionCtl.class);

    private final UserCollectionSvc service;

    public UserCollectionCtl(UserCollectionSvc service) {
        LOG.info("init");
        this.service = service;
    }

    public Page<Pokemon> list() {
        return service.list();
    }

    // detail one pokemon
    // add pokemon to the collection
    // update a pokemon
    // remove one from the collection
}
