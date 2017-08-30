package br.com.codein.financeproxy.api;

import com.fasterxml.jackson.databind.JsonNode;
import io.gumga.core.GumgaValues;
import io.gumga.core.QueryObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@RestController
@RequestMapping("/api/financeintegration/cashcheckin")
public class ProxyCheckinAPI extends AbstractClient {


    private Properties properties;

    @Autowired
    public ProxyCheckinAPI(GumgaValues gumgaValues) {
        super();
        this.properties = gumgaValues.getCustomFileProperties();
        this.url = this.properties.getProperty("finance.url");
    }

    @RequestMapping(method = RequestMethod.POST)
    public Map post(@RequestBody Map titulo) {
        return (Map) this.post("/api/cashcheckin/", titulo).getBody();
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public Map initialState() {
        return (Map) this.get("/api/cashcheckin/new").getBody();
    }

    @RequestMapping(value = "/opencheckin", method = RequestMethod.GET)
    public JsonNode openCheckin() {
        return (JsonNode) this.getArray("/api/cashcheckin/opencheckin").getBody();
    }

    @RequestMapping(value = "/getbycurrentcashcheckin", method = RequestMethod.GET)
    public JsonNode getbycurrentcashcheckin(@RequestParam  @DateTimeFormat(pattern = "YYYY-MM-dd'T'HH:mm:ssZ") Date date) {
        String param = new SimpleDateFormat("YYYY-MM-dd'T'HH:mm:ssZ").format(date);
        return (JsonNode) this.getArray(String.format("/api/cashcheckin/getbycurrentcashcheckin?date=%s", param)).getBody();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Map load(@PathVariable Long id) {
        return (Map) this.get(String.format("/api/cashcheckin/%d", id)).getBody();
    }

    @RequestMapping(method = RequestMethod.GET)
    public Map pesquisa(QueryObject query) throws IOException {
        return (Map) this.get("/api/cashcheckin",
                this.queryObjectToMap(query)).getBody();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Map update(@PathVariable("id") Long id,
                      @RequestBody Map model) {
        return (Map) this.put(String.format("/api/cashcheckin/%d", id), model).getBody();

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Map remove(@PathVariable("id") Long id,
                      @RequestBody Map model) {
        return (Map) this.delete(String.format("/api/cashcheckin/%d", id), model).getBody();

    }

}

