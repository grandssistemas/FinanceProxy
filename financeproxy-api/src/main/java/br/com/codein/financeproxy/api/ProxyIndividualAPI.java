package br.com.codein.financeproxy.api;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

import br.com.codein.financeproxy.configuration.FinanceProxyProperties;
import io.gumga.core.QueryObject;

@RestController
@RequestMapping("/api/financeintegration/individual")
public class ProxyIndividualAPI extends AbstractClient {


    private Properties properties;

    @Autowired
    public ProxyIndividualAPI(FinanceProxyProperties fProperties) {
        super();
        this.properties = fProperties.getProperties();
        this.url = this.properties.getProperty("mobiage.finance.host");
    }

    @Transactional
    @RequestMapping(method = RequestMethod.GET)
    public JsonNode QueryParticipations(QueryObject query) {
        return (JsonNode) this.get("/api/individual",
                this.queryObjectToMap(query)).getBody();
    }

    @RequestMapping(value = "/getlogged", method = RequestMethod.GET)
    public JsonNode getlogged() {
        return (JsonNode) this.getArray("/api/individual/getlogged").getBody();
    }

    @RequestMapping(value = "/getcompany", method = RequestMethod.GET)
    public JsonNode getcompany() {
        return (JsonNode) this.getArray("/api/individual/getcompany").getBody();
    }

    @RequestMapping(value ="/gquery", method = RequestMethod.POST)
    public JsonNode gquery(@RequestBody JsonNode gquery) {
        return (JsonNode) this.post("/api/individual/gquery", gquery).getBody();
    }

}

