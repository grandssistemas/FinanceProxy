package br.com.codein.financeproxy.api;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

import br.com.codein.financeproxy.configuration.FinanceProxyProperties;
import io.gumga.core.QueryObject;

@RestController
@RequestMapping("/api/financeintegration/cashcheckin")
public class ProxyCheckinAPI extends AbstractClient {


    private Properties properties;

    @Autowired
    public ProxyCheckinAPI(FinanceProxyProperties fProperties) {
        super();
        this.properties = fProperties.getProperties();
        this.url = this.properties.getProperty("mobiage.finance.host");
    }

    @RequestMapping(method = RequestMethod.POST)
    public JsonNode post(@RequestBody JsonNode titulo) {
        return (JsonNode) this.post("/api/cashcheckin/", titulo).getBody();
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public JsonNode initialState() {
        return (JsonNode) this.get("/api/cashcheckin/new").getBody();
    }

    @RequestMapping(value = "/opencheckin", method = RequestMethod.GET)
    public JsonNode openCheckin() {
        return (JsonNode) this.getArray("/api/cashcheckin/opencheckin").getBody();
    }

    @RequestMapping(value = "/getbycurrentcashcheckin", method = RequestMethod.GET)
    public JsonNode getbycurrentcashcheckin(@RequestParam  @DateTimeFormat(pattern = "YYYY-MM-dd'T'HH:mm:ssZ") Date date) {
        String param = new SimpleDateFormat("YYYY-MM-dd'T'HH:mm:ssZ").format(date);
        return (JsonNode) this.getArray(String.format("/api/cashcheckin/getbycurrentcashcheckin?date=%s", param)).getBody();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public JsonNode load(@PathVariable Long id) {
        return (JsonNode) this.get(String.format("/api/cashcheckin/%d", id)).getBody();
    }

    @RequestMapping(method = RequestMethod.GET)
    public JsonNode pesquisa(QueryObject query) throws IOException {
        return (JsonNode) this.get("/api/cashcheckin",
                this.queryObjectToMap(query)).getBody();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public JsonNode update(@PathVariable("id") Long id,
                      @RequestBody JsonNode model) {
        return (JsonNode) this.put(String.format("/api/cashcheckin/%d", id), model).getBody();

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public JsonNode remove(@PathVariable("id") Long id,
                      @RequestBody JsonNode model) {
        return (JsonNode) this.delete(String.format("/api/cashcheckin/%d", id), model).getBody();

    }

    @RequestMapping(method = RequestMethod.POST, path = "/do-cashcheckin")
    public JsonNode openCashCheckin(@RequestBody JsonNode openCashCheckin) {
        return (JsonNode) this.post("/api/cashcheckin/do-cashcheckin", openCashCheckin).getBody();
    }
}

