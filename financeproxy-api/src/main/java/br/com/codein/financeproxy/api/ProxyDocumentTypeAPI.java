package br.com.codein.financeproxy.api;

import io.gumga.core.GumgaValues;
import io.gumga.core.QueryObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Properties;

@RestController
@RequestMapping("/api/financeintegration/documenttype")
public class ProxyDocumentTypeAPI extends AbstractClient {


    private Properties properties;

    @Autowired
    public ProxyDocumentTypeAPI(GumgaValues gumgaValues) {
        super();
        this.properties = gumgaValues.getCustomFileProperties();
        this.url = this.properties.getProperty("finance.url");
    }

    @RequestMapping(method = RequestMethod.POST)
    public Map post(@RequestBody Map documenttype) {
        return (Map) this.post("/api/documenttype/", documenttype).getBody();
    }

    @Transactional
    @RequestMapping(method = RequestMethod.GET)
    public Map getDocumentytypes(QueryObject query) {
        return (Map) this.get("/api/documenttype",
                this.queryObjectToMap(query)).getBody();
    }



}

