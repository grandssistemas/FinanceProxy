package br.com.codein.financeproxy.api;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

import br.com.codein.financeproxy.configuration.FinanceProxyProperties;
import io.gumga.core.QueryObject;

@RestController
@RequestMapping("/api/financeintegration/ratioplan")
public class ProxyRatioPlanAPI extends AbstractClient {


    private Properties properties;

    @Autowired
    public ProxyRatioPlanAPI(FinanceProxyProperties fProperties) {
        super();
        this.properties = fProperties.getProperties();
        this.url = this.properties.getProperty("mobiage.finance.host");
    }

    @RequestMapping(method = RequestMethod.GET)
    public JsonNode get(QueryObject query) {
        return (JsonNode) this.get("/api/ratioplan",
                this.queryObjectToMap(query)).getBody();
    }
    @Transactional
    @RequestMapping(value = "/automaticratio/{label}/{value}", method = RequestMethod.GET)
    public JsonNode automaticRatio(@PathVariable String label, @PathVariable Long value ) {
        return  (JsonNode) this.getArray(String.format("/api/ratioplan/automaticratio/%s/%d", label, value)).getBody();
    }
}

