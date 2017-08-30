package br.com.codein.financeproxy.api;

import com.fasterxml.jackson.databind.JsonNode;
import io.gumga.core.GumgaValues;
import io.gumga.core.QueryObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import java.util.Properties;

@RestController
@RequestMapping("/api/financeintegration/individuallabel")
public class ProxyIndividualLabelAPI extends AbstractClient {


    private Properties properties;

    @Autowired
    public ProxyIndividualLabelAPI(GumgaValues gumgaValues) {
        super();
        this.properties = gumgaValues.getCustomFileProperties();
        this.url = this.properties.getProperty("finance.url");
    }

    @Transactional
    @RequestMapping(method = RequestMethod.GET)
    public JsonNode search(QueryObject query) {
        return (JsonNode) this.get("/api/individuallabel",
                this.queryObjectToMap(query)).getBody();
    }


}

