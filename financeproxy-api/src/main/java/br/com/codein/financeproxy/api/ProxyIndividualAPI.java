package br.com.codein.financeproxy.api;

import io.gumga.core.GumgaValues;
import io.gumga.core.QueryObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

@RestController
@RequestMapping("/api/financeintegration/individual")
public class ProxyIndividualAPI extends AbstractClient {


    private Properties properties;

    @Autowired
    public ProxyIndividualAPI(GumgaValues gumgaValues) {
        super();
        this.properties = gumgaValues.getCustomFileProperties();
        this.url = this.properties.getProperty("finance.url");
    }

    @Transactional
    @RequestMapping(method = RequestMethod.GET)
    public Map QueryParticipations(QueryObject query) {
        return (Map) this.get("/api/individual",
                this.queryObjectToMap(query)).getBody();
    }


}

