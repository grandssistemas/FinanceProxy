package br.com.codein.financeproxy.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import io.gumga.core.GumgaValues;
import io.gumga.core.QueryObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

@RestController
@RequestMapping("/api/financeintegration/financereport")
public class ProxyFinanceReportConnectAPI extends AbstractClient {


    private Properties properties;

    @Autowired
    public ProxyFinanceReportConnectAPI(GumgaValues gumgaValues) {
        super();
        this.properties = gumgaValues.getCustomFileProperties();
        this.url = this.properties.getProperty("finance.url");
    }


    @RequestMapping(method = RequestMethod.GET)
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//         process(this.get("",req),resp);
    }

    @RequestMapping(method = RequestMethod.POST)
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        process(req, resp);
    }

    private void process(ResponseEntity entity) {
//        try {
//            entity.
//            byte[] result = new byte[resp.getBufferSize()];
//            resp.getInputStream().
//            resp.setHeader("Access-Control-Allow-Origin", "*");
//            resp.setHeader("Cache-Control", "no-cache");
//            resp.getOutputStream().write(.getBytes("UTF-8"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

}