package br.com.codein.financeproxy.api;

import com.fasterxml.jackson.databind.JsonNode;
import io.gumga.core.GumgaValues;
import io.gumga.core.QueryObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import java.util.Properties;

@RestController
@RequestMapping("/api/financeintegration/titlelabel")
public class ProxyTitleLabelAPI extends AbstractClient {


    private Properties properties;

    @Autowired
    public ProxyTitleLabelAPI(GumgaValues gumgaValues) {
        super();
        this.properties = gumgaValues.getCustomFileProperties();
        this.url = this.properties.getProperty("finance.url");
    }

    @RequestMapping(method = RequestMethod.GET)
    public JsonNode pesquisa(QueryObject query) throws IOException {
        return (JsonNode) this.get("/api/titlelabel",
                this.queryObjectToMap(query)).getBody();
    }
}

