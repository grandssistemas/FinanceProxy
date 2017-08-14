package br.com.codein.financeproxy.api;

import io.gumga.core.GumgaValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Properties;

@RestController
@RequestMapping("/api/financeintegration/plan")
public class ProxyPlanAPI extends AbstractClient {


    private Properties properties;

    @Autowired
    public ProxyPlanAPI(GumgaValues gumgaValues) {
        super();
        this.properties = gumgaValues.getCustomFileProperties();
        this.url = this.properties.getProperty("finance.url");
    }

    @RequestMapping(value = "/plantree", method = RequestMethod.GET)
    public Map planTree() {
        return (Map) this.get("/api/plan/plantree").getBody();
    }

}

