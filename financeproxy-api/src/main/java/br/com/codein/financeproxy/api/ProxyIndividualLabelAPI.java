package br.com.codein.financeproxy.api;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

import br.com.codein.financeproxy.configuration.FinanceProxyProperties;
import io.gumga.core.QueryObject;

@RestController
@RequestMapping("/api/financeintegration/individuallabel")
public class ProxyIndividualLabelAPI extends AbstractClient {


    private Properties properties;

    @Autowired
    public ProxyIndividualLabelAPI(FinanceProxyProperties fProperties) {
        super();
        this.properties = fProperties.getProperties();
        this.url = this.properties.getProperty("mobiage.finance.host");
    }

    @Transactional
    @RequestMapping(method = RequestMethod.GET)
    public JsonNode search(QueryObject query) {
        return (JsonNode) this.get("/api/individuallabel",
                this.queryObjectToMap(query)).getBody();
    }


}

