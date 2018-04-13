package br.com.codein.financeproxy.api;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

import br.com.codein.financeproxy.configuration.FinanceProxyProperties;
import io.gumga.core.QueryObject;

@RestController
@RequestMapping("/api/financeintegration/planleaf")
public class ProxyPlanLeafAPI extends AbstractClient {


    private Properties properties;

    @Autowired
    public ProxyPlanLeafAPI(FinanceProxyProperties fProperties) {
        super();
        this.properties = fProperties.getProperties();
        this.url = this.properties.getProperty("mobiage.finance.host");
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

