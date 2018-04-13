package br.com.codein.financeproxy.api;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.codein.financeproxy.configuration.FinanceProxyProperties;

@RestController
@RequestMapping("/api/financeintegration/financereportconnect")
public class ProxyFinanceReportConnectAPI extends AbstractClient {


    private Properties properties;

    @Autowired
    public ProxyFinanceReportConnectAPI(FinanceProxyProperties fProperties) {
        super();
        this.properties = fProperties.getProperties();
        this.url = this.properties.getProperty("mobiage.finance.host");
    }

    @RequestMapping(method = RequestMethod.POST)
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        StringBuffer buffer = new StringBuffer();
        BufferedReader r = new BufferedReader(new InputStreamReader(req.getInputStream(), StandardCharsets.UTF_8));
        String str;
        while((str = r.readLine()) != null) {
            buffer.append(str);
        }
        process(this.postAsString("/api/financereportconnect",buffer.toString()), resp);
    }

    private void process(ResponseEntity entity, HttpServletResponse resp) {
        try {
            String body = (String)entity.getBody();
            resp.setHeader("Access-Control-Allow-Origin", "*");
            resp.setHeader("Cache-Control", "no-cache");
            resp.getOutputStream().write(body.getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}