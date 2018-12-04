package br.com.codein.financeproxy.api;

import java.io.IOException;
import java.util.List;
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
@RequestMapping("/api/financeintegration/title")
public class ProxyTitleAPI extends AbstractClient {


    private Properties properties;

    @Autowired
    public ProxyTitleAPI(FinanceProxyProperties fProperties) {
        super();
        this.properties = fProperties.getProperties();
        this.url = this.properties.getProperty("mobiage.finance.host");
    }

    @RequestMapping(method = RequestMethod.POST)
    public JsonNode post(@RequestBody JsonNode titulo) {
        return (JsonNode) this.post("/api/title/", titulo).getBody();
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public JsonNode initialState() {
        return (JsonNode) this.get("/api/title/new").getBody();
    }

    @RequestMapping(value = "/newfromentry", method = RequestMethod.POST)
    public JsonNode generateNew(@RequestBody JsonNode titleTO) {
        return (JsonNode) this.post("/api/title/new", titleTO).getBody();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public JsonNode load(@PathVariable Long id) {
        return (JsonNode) this.get(String.format("/api/title/%d", id)).getBody();
    }

    @RequestMapping(method = RequestMethod.GET)
    public JsonNode pesquisa(QueryObject query) throws IOException {
        return (JsonNode) this.get("/api/title",
                this.queryObjectToMap(query)).getBody();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public JsonNode update(@PathVariable("id") Long id,
                      @RequestBody JsonNode model) {
        return (JsonNode) this.put(String.format("/api/title/%d", id), model).getBody();

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public JsonNode remove(@PathVariable("id") Long id,
                      @RequestBody JsonNode model) {
        return (JsonNode) this.delete(String.format("/api/title/%d", id), model).getBody();

    }
    @RequestMapping(value = "/joinparticipations", method = RequestMethod.GET)
    public JsonNode QueryParticipations(QueryObject query) {
        return (JsonNode) this.get("/api/title/joinparticipations",
                this.queryObjectToMap(query)).getBody();
    }

    @RequestMapping(value = "/parcelsdto", method = RequestMethod.POST)
    public JsonNode titleParcelForQuery(@RequestBody List<Long> idParcels) {
        return (JsonNode) this.post("/api/title/parcelsdto",idParcels).getBody();
    }

    @RequestMapping(value = "/replacement", method = RequestMethod.POST)
    public JsonNode saveReplacement(@RequestBody JsonNode title) {
        return (JsonNode) this.post("/api/title/replacement",title).getBody();
    }
    @RequestMapping(value = "/renegotiation", method = RequestMethod.POST)
    public JsonNode saveRenegotiation(@RequestBody JsonNode title) {
        return (JsonNode) this.post("/api/title/renegotiation",title).getBody();
    }

    @RequestMapping(value = "/findbylabel/{label}/{type}", method = RequestMethod.GET)
    public JsonNode titleByLabel(@PathVariable String label, @PathVariable String type) {
        return (JsonNode) this.get(String.format("/api/title/findbylabel/%s/%s", label, type)).getBody();
    }

    @RequestMapping(value ="/gquery", method = RequestMethod.POST)
    public JsonNode gquery(@RequestBody JsonNode gquery) {
        return (JsonNode) this.post("/api/title/gquery", gquery).getBody();
    }

    @RequestMapping(value = "/typecategorys", method = RequestMethod.POST)
    public JsonNode listTitleTypeCategory(@RequestBody JsonNode gquery) {
        return (JsonNode) this.post("/api/title/typecategory", gquery).getBody();
    }

}

