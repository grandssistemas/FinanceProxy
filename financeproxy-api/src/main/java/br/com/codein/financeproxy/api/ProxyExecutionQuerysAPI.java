package br.com.codein.financeproxy.api;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

import br.com.codein.financeproxy.configuration.FinanceProxyProperties;

@RestController
@RequestMapping("/api/financeintegration/executionquerys")
public class ProxyExecutionQuerysAPI extends AbstractClient{

    private Properties properties;

    @Autowired
    public ProxyExecutionQuerysAPI(FinanceProxyProperties fProperties) {
        super();
        this.properties = fProperties.getProperties();
        this.url = this.properties.getProperty("mobiage.finance.host");
    }

    @RequestMapping(value = "/entriesfromlastbalance/{id}", method = RequestMethod.GET)
    public JsonNode entriesfromlastbalance(@PathVariable("id") Long id) {
        return (JsonNode) this.getArray(String.format("/api/executionquerys/entriesfromlastbalance/%d", id)).getBody();
    }

    @RequestMapping(value = "/entriesfromlastbalance/{id}/{data}", method = RequestMethod.GET)
    public JsonNode entriesfromlastbalance(@PathVariable("id") Long id, @PathVariable("data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date data) {
        String param = new SimpleDateFormat("yyyy-MM-dd").format(data);
        return (JsonNode) this.getArray(String.format("/api/executionquerys/entriesfromlastbalance/%d/%s", id, param)).getBody();
    }

    @RequestMapping(value = "/conciliatedentriesfromlastbalance/{id}/{data}", method = RequestMethod.GET)
    public JsonNode conciliatedEntriesFromLastBalance(@PathVariable("id") Long id, @PathVariable("data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date data) {
        String param = new SimpleDateFormat("yyyy-MM-dd").format(data);
        return (JsonNode) this.getArray(String.format("/api/executionquerys/conciliatedentriesfromlastbalance/%d/%s", id, param)).getBody();
    }

    @RequestMapping(value = "/conciliatedentriesfromlastbalance/{id}", method = RequestMethod.GET)
    public JsonNode conciliatedEntriesfromlastbalance(@PathVariable("id") Long id) {
        return (JsonNode) this.getArray(String.format("/api/executionquerys/conciliatedentriesfromlastbalance/%d", id)).getBody();
    }

    @RequestMapping(method = RequestMethod.GET, value = "entriesbyfinanceunitandcheckin/{financeUnitId}/{cashCheckinId}")
    public JsonNode getEntriesByFinanceUnitAndCheckin(@PathVariable Long financeUnitId, @PathVariable Long cashCheckinId) {
        return (JsonNode) this.get(String.format("/api/executionquerys/entriesbyfinanceunitandcheckin/%d/%d", financeUnitId, cashCheckinId)).getBody();
    }

    @RequestMapping(method = RequestMethod.GET, value = "entriesbycheckin/{cashCheckinId}")
    public JsonNode getEntriesByCheckin(@PathVariable  Long cashCheckinId) {
        return (JsonNode) this.get(String.format("/api/executionquerys/entriesbycheckin/%d", cashCheckinId)).getBody();
    }

    @RequestMapping(method = RequestMethod.GET, value = "financeUnitBalance")
    public JsonNode getFinanceUnitBalance(@RequestParam Long financeUnitId) {
        return (JsonNode) this.get(String.format("/api/executionquerys/financeUnitBalance?financeUnitId=%d", financeUnitId)).getBody();
    }

}
