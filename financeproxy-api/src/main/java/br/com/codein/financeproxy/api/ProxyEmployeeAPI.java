package br.com.codein.financeproxy.api;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.codein.financeproxy.configuration.FinanceProxyProperties;

@RestController
@RequestMapping("/api/financeintegration/employee")
public class ProxyEmployeeAPI extends AbstractClient{

    private Properties properties;

    @Autowired
    public ProxyEmployeeAPI(FinanceProxyProperties fProperties) {
        super();
        this.properties = fProperties.getProperties();
        this.url = this.properties.getProperty("mobiage.finance.host");
    }
}
