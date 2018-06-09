package br.com.codein.financeproxy.api;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

import br.com.codein.financeproxy.configuration.FinanceProxyProperties;

/**
 * Created by marcio on 30/08/17.
 */

@RestController
@RequestMapping("/api/financeintegration/boleto")
public class ProxyBoletoAPI extends AbstractClient {


    private Properties properties;

    @Autowired
    public ProxyBoletoAPI(FinanceProxyProperties fProperties) {
        super();
        this.properties = fProperties.getProperties();
        this.url = this.properties.getProperty("mobiage.finance.host");
    }

    @RequestMapping(value = "/{line}", method = RequestMethod.GET)
    public JsonNode parse(@PathVariable String line) {
        return (JsonNode) this.get(String.format("/api/boleto/%s",line)).getBody();
    }

}


