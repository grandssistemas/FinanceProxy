package br.com.codein.financeproxy.api;

import java.io.IOException;
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
@RequestMapping("/api/financeintegration/thirdpartycheque")
public class ProxyThirdPartyChequeAPI extends AbstractClient{
    private Properties properties;

    @Autowired
    public ProxyThirdPartyChequeAPI(FinanceProxyProperties fProperties) {
        super();
        this.properties = fProperties.getProperties();
        this.url = this.properties.getProperty("mobiage.finance.host");
    }

    @RequestMapping(method = RequestMethod.POST)
    public JsonNode post(@RequestBody JsonNode titulo) {
        return (JsonNode) this.post("/api/thirdpartycheque/", titulo).getBody();
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public JsonNode initialState() {
        return (JsonNode) this.get("/api/thirdpartycheque/new").getBody();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public JsonNode load(@PathVariable Long id) {
        return (JsonNode) this.get(String.format("/api/thirdpartycheque/%d", id)).getBody();
    }

    @RequestMapping(method = RequestMethod.GET)
    public JsonNode pesquisa(QueryObject query) throws IOException {
        return (JsonNode) this.get("/api/thirdpartycheque",
                this.queryObjectToMap(query)).getBody();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public JsonNode update(@PathVariable("id") Long id,
                           @RequestBody JsonNode model) {
        return (JsonNode) this.put(String.format("/api/thirdpartycheque/%d", id), model).getBody();

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public JsonNode remove(@PathVariable("id") Long id,
                      @RequestBody JsonNode model) {
        return (JsonNode) this.delete(String.format("/api/thirdpartycheque/%d", id), model).getBody();
    }

}