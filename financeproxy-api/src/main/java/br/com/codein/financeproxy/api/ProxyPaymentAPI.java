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
@RequestMapping("/api/financeintegration/payment")
public class ProxyPaymentAPI extends AbstractClient {


    private Properties properties;

    @Autowired
    public ProxyPaymentAPI(FinanceProxyProperties fProperties) {
        super();
        this.properties = fProperties.getProperties();
        this.url = this.properties.getProperty("mobiage.finance.host");
    }

    @RequestMapping(method = RequestMethod.POST)
    public JsonNode post(@RequestBody JsonNode titulo) {
        return (JsonNode) this.post("/api/payment/", titulo).getBody();
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public JsonNode initialState() {
        return (JsonNode) this.get("/api/payment/new").getBody();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public JsonNode load(@PathVariable Long id) {
        return (JsonNode) this.get(String.format("/api/payment/%d", id)).getBody();
    }

    @RequestMapping(method = RequestMethod.GET)
    public JsonNode pesquisa(QueryObject query) throws IOException {
        return (JsonNode) this.get("/api/payment",
                this.queryObjectToMap(query)).getBody();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public JsonNode update(@PathVariable("id") Long id,
                           @RequestBody JsonNode model) {
        return (JsonNode) this.put(String.format("/api/payment/%d", id), model).getBody();

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public JsonNode remove(@PathVariable("id") Long id,
                           @RequestBody JsonNode model) {
        return (JsonNode) this.delete(String.format("/api/payment/%d", id), model).getBody();
    }

    @RequestMapping(value = "/pay",method = RequestMethod.POST)
    public JsonNode pay(@RequestBody JsonNode titulo) {
        return (JsonNode) this.post("/api/payment/pay", titulo).getBody();
    }

    @RequestMapping(value ="/receive",method = RequestMethod.POST)
    public JsonNode receive(@RequestBody JsonNode titulo) {
        return (JsonNode) this.post("/api/payment/receive", titulo).getBody();
    }

    @RequestMapping(value ="/gquery",method = RequestMethod.POST)
    public JsonNode gquery(@RequestBody JsonNode gquery) {
        return (JsonNode) this.post("/api/payment/gquery", gquery).getBody();
    }

}

