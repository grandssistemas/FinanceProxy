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
@RequestMapping("/api/financeintegration/titleparcel")
public class ProxyTitleParcelAPI extends AbstractClient {


    private Properties properties;

    @Autowired
    public ProxyTitleParcelAPI(FinanceProxyProperties fProperties) {
        super();
        this.properties = fProperties.getProperties();
        this.url = this.properties.getProperty("mobiage.finance.host");
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

    @RequestMapping(value = "/getpaymentsbyparcel/{id}", method = RequestMethod.GET)
    public JsonNode getPaymentsByParcel(@PathVariable("id") Long id) {
        return (JsonNode) this.get(String.format("/api/titleparcel/getpaymentsbyparcel/%d", id)).getBody();
    }

    @RequestMapping(path = "/gquery", method = RequestMethod.POST)
    public JsonNode qquery(@RequestBody JsonNode query) {
        return (JsonNode) this.post("/api/titleparcel/gquery", query).getBody();
    }
}

