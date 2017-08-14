package br.com.codein.financeproxy.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.gumga.core.GumgaValues;
import io.gumga.core.QueryObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Properties;

@RestController
@RequestMapping("/api/financeintegration/ratioplan")
public class ProxyRatioPlanAPI extends AbstractClient {


    private Properties properties;

    @Autowired
    public ProxyRatioPlanAPI(GumgaValues gumgaValues) {
        super();
        this.properties = gumgaValues.getCustomFileProperties();
        this.url = this.properties.getProperty("finance.url");
    }

    @RequestMapping(method = RequestMethod.GET)
    public Map ratioPlan() {
        return (Map) this.get("/api/ratioplan").getBody();
    }

    @Transactional
    @RequestMapping(value = "/automaticratio/{label}/{value}", method = RequestMethod.GET)
    public JsonNode automaticRatio(@PathVariable String label, @PathVariable Long value ) {
        return  (JsonNode) this.getArray(String.format("/api/ratioplan/automaticratio/%s/%d", label, value)).getBody();
    }
}

