package br.com.codein.financeproxy.api;


import io.gumga.core.GumgaValues;
import io.gumga.core.QueryObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

@RestController
@RequestMapping("/api/financeintegration/financereportconnect")
public class ProxyFinanceReportConnectAPI extends AbstractClient {


    private Properties properties;

    @Autowired
    public ProxyFinanceReportConnectAPI(GumgaValues gumgaValues) {
        super();
        this.properties = gumgaValues.getCustomFileProperties();
        this.url = this.properties.getProperty("finance.url");
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