package br.com.codein.financeproxy.api;

import com.fasterxml.jackson.databind.JsonNode;
import io.gumga.core.GumgaValues;
import io.gumga.core.QueryObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


import java.util.Properties;

@RestController
@RequestMapping("/api/financeintegration/planleaf")
public class ProxyPlanLeafAPI extends AbstractClient {


    private Properties properties;

    @Autowired
    public ProxyPlanLeafAPI(GumgaValues gumgaValues) {
        super();
        this.properties = gumgaValues.getCustomFileProperties();
        this.url = this.properties.getProperty("finance.url");
    }

    @RequestMapping(method = RequestMethod.GET)
    public JsonNode planLeaf(QueryObject query) {
        return (JsonNode) this.get("/api/planleaf",
                this.queryObjectToMap(query)).getBody();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/types")
    public JsonNode getTypes() {
        return (JsonNode) this.get("/api/planleaf/types").getBody();
    }

    @RequestMapping(method = RequestMethod.POST)
    public JsonNode post(@RequestBody JsonNode titulo) {
        return (JsonNode) this.post("/api/planleaf", titulo).getBody();
    }



    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public JsonNode update(@PathVariable("id") Long id,
                      @RequestBody JsonNode model) {
        return (JsonNode) this.put(String.format("/api/planleaf/%d", id), model).getBody();

    }

}

