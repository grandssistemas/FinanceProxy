package br.com.codein.financeproxy.api;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

import br.com.codein.financeproxy.configuration.FinanceProxyProperties;

@RestController
@RequestMapping("/api/financeintegration/plan")
public class ProxyPlanAPI extends AbstractClient {


    private Properties properties;

    @Autowired
    public ProxyPlanAPI(FinanceProxyProperties fProperties) {
        super();
        this.properties = fProperties.getProperties();
        this.url = this.properties.getProperty("mobiage.finance.host");
    }

    @RequestMapping(value = "/plantree", method = RequestMethod.GET)
    public JsonNode planTree() {
        return (JsonNode) this.get("/api/plan/plantree").getBody();
    }

}

