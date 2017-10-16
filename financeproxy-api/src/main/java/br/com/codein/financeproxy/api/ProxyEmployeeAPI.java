package br.com.codein.financeproxy.api;

import com.fasterxml.jackson.databind.JsonNode;
import io.gumga.core.GumgaValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;

@RestController
@RequestMapping("/api/financeintegration/employee")
public class ProxyEmployeeAPI extends AbstractClient{

    private Properties properties;

    @Autowired
    public ProxyEmployeeAPI(GumgaValues gumgaValues) {
        super();
        this.properties = gumgaValues.getCustomFileProperties();
        this.url = this.properties.getProperty("finance.url");
    }
}
