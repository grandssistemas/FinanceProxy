package br.com.codein.financeproxy.api;

import com.fasterxml.jackson.databind.JsonNode;
import io.gumga.core.GumgaValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;

/**
 * Created by marcio on 30/08/17.
 */

@RestController
@RequestMapping("/api/financeintegration/boleto")
public class ProxyBoletoAPI extends AbstractClient {


    private Properties properties;

    @Autowired
    public ProxyBoletoAPI(GumgaValues gumgaValues) {
        super();
        this.properties = gumgaValues.getCustomFileProperties();
        this.url = this.properties.getProperty("finance.url");
    }

    @RequestMapping(value = "/{line}", method = RequestMethod.GET)
    public JsonNode parse(@PathVariable String line) {
        return (JsonNode) this.get(String.format("/api/boleto/%s",line)).getBody();
    }

}


