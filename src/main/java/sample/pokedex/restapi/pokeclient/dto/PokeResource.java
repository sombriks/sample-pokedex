package sample.pokedex.restapi.pokeclient.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public record PokeResource(String name, String url) {
    private static final Logger LOG = LoggerFactory.getLogger(PokeResource.class);
    public Integer extractId() {
        try {
            String id = url.replaceFirst(".*/(\\d+)/", "$1");
            return Integer.parseInt(id);
        } catch (Exception ex) {
            LOG.warn("malformed PokeResource",ex);
            return -1;
        }
    }
}
