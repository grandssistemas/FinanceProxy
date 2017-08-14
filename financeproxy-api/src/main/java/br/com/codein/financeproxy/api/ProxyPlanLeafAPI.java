package br.com.codein.financeproxy.api;

import io.gumga.core.GumgaValues;
import io.gumga.core.QueryObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Properties;

@RestController
@RequestMapping("/api/financeintegration/planleaf")
public class ProxyPlanLeafAPI extends AbstractClient {


    private Properties properties;

    @Autowired
    public ProxyPlanLeafAPI(GumgaValues gumgaValues) {
        super();
        this.properties = gumgaValues.getCustomFileProperties();
        this.url = this.properties.getProperty("finance.url");
    }

    @RequestMapping(method = RequestMethod.GET)
    public Map planLeaf(QueryObject query) {
        return (Map) this.get("/api/planleaf",
                this.queryObjectToMap(query)).getBody();
    }

}

