package br.com.codein.financeproxy.api;

import com.fasterxml.jackson.databind.JsonNode;
import io.gumga.core.GumgaValues;
import io.gumga.core.QueryObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import java.util.Properties;

@RestController
@RequestMapping("/api/financeintegration/titleparcel")
public class ProxyTitleParcelAPI extends AbstractClient {


    private Properties properties;

    @Autowired
    public ProxyTitleParcelAPI(GumgaValues gumgaValues) {
        super();
        this.properties = gumgaValues.getCustomFileProperties();
        this.url = this.properties.getProperty("finance.url");
    }

    @RequestMapping(method = RequestMethod.POST)
    public JsonNode post(@RequestBody JsonNode titulo) {
        return (JsonNode) this.post("/api/titleparcel/", titulo).getBody();
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public JsonNode load(@PathVariable Long id) {
        return (JsonNode) this.get(String.format("/api/titleparcel/%d", id)).getBody();
    }

    @RequestMapping(method = RequestMethod.GET)
    public JsonNode pesquisa(QueryObject query) throws IOException {
        return (JsonNode) this.get("/api/titleparcel",
                this.queryObjectToMap(query)).getBody();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public JsonNode update(@PathVariable("id") Long id,
                      @RequestBody JsonNode model) {
        return (JsonNode) this.put(String.format("/api/titleparcel/%d", id), model).getBody();

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public JsonNode remove(@PathVariable("id") Long id,
                      @RequestBody JsonNode model) {
        return (JsonNode) this.delete(String.format("/api/titleparcel/%d", id), model).getBody();

    }


    @RequestMapping(value = "/grouped/{type}", method = RequestMethod.GET)
    public JsonNode loadGrouped(@PathVariable("type") String type) {
        return (JsonNode) this.get(String.format("/api/titleparcel/grouped/%s", type)).getBody();
    }

    @RequestMapping(value = "/grouped/{type}/{ind}", method = RequestMethod.GET)
    public JsonNode loadIndividualGrouped(@PathVariable("type") String type, @PathVariable("ind") Long ind) {
        return (JsonNode) this.get(String.format("/api/titleparcel/grouped/%s/%d", type, ind)).getBody();
    }
}

