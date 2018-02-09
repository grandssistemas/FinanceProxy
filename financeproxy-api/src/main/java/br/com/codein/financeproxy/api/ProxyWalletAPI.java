package br.com.codein.financeproxy.api;

import com.fasterxml.jackson.databind.JsonNode;
import io.gumga.core.GumgaValues;
import io.gumga.core.QueryObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Properties;

@RestController
@RequestMapping("/api/financeintegration/wallet")
public class ProxyWalletAPI extends AbstractClient {

    private Properties properties;

    @Autowired
    public ProxyWalletAPI(GumgaValues gumgaValues) {
        super();
        this.properties = gumgaValues.getCustomFileProperties();
        this.url = this.properties.getProperty("finance.url");
    }

    @RequestMapping(method = RequestMethod.POST)
    public JsonNode post(@RequestBody JsonNode titulo) {
        return (JsonNode) this.post("/api/wallet/", titulo).getBody();
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public JsonNode initialState() {
        return (JsonNode) this.get("/api/wallet/new").getBody();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public JsonNode load(@PathVariable Long id) {
        return (JsonNode) this.get(String.format("/api/wallet/%d", id)).getBody();
    }

    @RequestMapping(method = RequestMethod.GET)
    public JsonNode pesquisa(QueryObject query) throws IOException {
        return (JsonNode) this.get("/api/wallet",
                this.queryObjectToMap(query)).getBody();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public JsonNode update(@PathVariable("id") Long id,
                           @RequestBody JsonNode model) {
        return (JsonNode) this.put(String.format("/api/wallet/%d", id), model).getBody();

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public JsonNode remove(@PathVariable("id") Long id,
                           @RequestBody JsonNode model) {
        return (JsonNode) this.delete(String.format("/api/wallet/%d", id), model).getBody();

    }

    @RequestMapping(value = "/gquery", method = RequestMethod.POST)
    public JsonNode gQuery(@RequestBody JsonNode gquery) {
        return (JsonNode) this.post("/api/wallet/gquery", gquery).getBody();
    }


}