package br.com.codein.financeproxy.api;

import io.gumga.core.GumgaValues;
import io.gumga.core.QueryObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

@RestController
@RequestMapping("/api/financeintegration/thirdpartycheque")
public class ProxyThirdPartyChequeAPI extends AbstractClient{
    private Properties properties;

    @Autowired
    public ProxyThirdPartyChequeAPI(GumgaValues gumgaValues) {
        super();
        this.properties = gumgaValues.getCustomFileProperties();
        this.url = this.properties.getProperty("finance.url");
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity post(@RequestBody Map titulo) {
        return this.post("/api/thirdpartycheque/", titulo);
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public Map initialState() {
        return (Map) this.get("/api/thirdpartycheque/new").getBody();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Map load(@PathVariable Long id) {
        return (Map) this.get(String.format("/api/thirdpartycheque/%d", id)).getBody();
    }

    @RequestMapping(method = RequestMethod.GET)
    public Map pesquisa(QueryObject query) throws IOException {
        return (Map) this.get("/api/thirdpartycheque",
                this.queryObjectToMap(query)).getBody();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Map update(@PathVariable("id") Long id,
                      @RequestBody Map model) {
        return (Map) this.put(String.format("/api/thirdpartycheque/%d", id), model).getBody();

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Map remove(@PathVariable("id") Long id,
                      @RequestBody Map model) {
        return (Map) this.delete(String.format("/api/thirdpartycheque/%d", id), model).getBody();
    }

}
