package br.com.codein.financeproxy.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.gumga.core.GumgaThreadScope;
import io.gumga.core.QueryObject;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public abstract class AbstractClient {

    private HttpHeaders headers;
    private RestTemplate restTemplate;
    private HttpEntity requestEntity;
    protected String url;

    public AbstractClient() {
    }

    public AbstractClient(String url) {
        this.url = url;
    }

    protected ResponseEntity get(String url, Map<String, String> stringObjectMap) {
        this.restTemplate = new RestTemplate();
        this.restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        this.headers = new HttpHeaders();
        this.headers.set("Accept", "application/json, text/plain, */*");
        this.headers.set("Accept-Encoding", "gzip, deflate");
        this.headers.set("Content-Type", "application/json;charset=utf-8");
        this.headers.set("gumgaToken", GumgaThreadScope.gumgaToken.get());
        this.requestEntity = new HttpEntity(this.headers);
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(this.url.concat(url))
                .queryParams(this.mapToMultiValueMap(stringObjectMap)).build();
        return this.restTemplate.exchange(uriComponents.toUriString().replaceAll("[\\[\\]]", ""), HttpMethod.GET, (HttpEntity<?>) this.requestEntity, JsonNode.class, stringObjectMap);
    }

    protected ResponseEntity getArray(String url, Map<String, String> stringObjectMap) {
        this.restTemplate = new RestTemplate();
        this.restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        this.headers = new HttpHeaders();
        this.headers.set("Accept", "application/json, text/plain, */*");
        this.headers.set("Accept-Encoding", "gzip, deflate");
        this.headers.set("Content-Type", "application/json;charset=utf-8");
        this.headers.set("gumgaToken", GumgaThreadScope.gumgaToken.get());
        this.requestEntity = new HttpEntity(this.headers);
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(this.url.concat(url))
                .queryParams(this.mapToMultiValueMap(stringObjectMap)).build();
        return this.restTemplate.exchange(uriComponents.toUriString().replaceAll("[\\[\\]]", ""), HttpMethod.GET, (HttpEntity<?>) this.requestEntity, JsonNode.class, stringObjectMap);
    }

    protected ResponseEntity get(String url) {
        return this.get(url, new HashMap<>());
    }

    protected ResponseEntity getArray(String url) {
        return this.getArray(url, new HashMap<>());
    }


    protected ResponseEntity post(String url, Object object) {
        this.restTemplate = new RestTemplate();
        this.restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        this.headers = new HttpHeaders();
        this.headers.set("Accept", "application/json, text/plain, */*");
        this.headers.set("Accept-Encoding", "gzip, deflate");
        this.headers.set("Content-Type", "application/json;charset=utf-8");
        this.headers.set("gumgaToken", GumgaThreadScope.gumgaToken.get());
        this.requestEntity = new HttpEntity(object, this.headers);
        return this.restTemplate.exchange(this.url.concat(url), HttpMethod.POST, (HttpEntity<?>) this.requestEntity, JsonNode.class);
    }

    protected ResponseEntity put(String url, Object object) {
        this.restTemplate = new RestTemplate();
        this.restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        this.headers = new HttpHeaders();
        this.headers.set("Accept", "application/json, text/plain, */*");
        this.headers.set("Accept-Encoding", "gzip, deflate");
        this.headers.set("Content-Type", "application/json;charset=utf-8");
        this.headers.set("gumgaToken", GumgaThreadScope.gumgaToken.get());
        this.requestEntity = new HttpEntity(object, this.headers);
        return this.restTemplate.exchange(this.url.concat(url), HttpMethod.PUT, (HttpEntity<?>) this.requestEntity, JsonNode.class);
    }

    protected ResponseEntity delete(String url, Object object) {
        this.restTemplate = new RestTemplate();
        this.restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        this.headers = new HttpHeaders();
        this.headers.set("Accept", "application/json, text/plain, */*");
        this.headers.set("Accept-Encoding", "gzip, deflate");
        this.headers.set("Content-Type", "application/json;charset=utf-8");
        this.headers.set("gumgaToken", GumgaThreadScope.gumgaToken.get());
        this.requestEntity = new HttpEntity(object, this.headers);
        return this.restTemplate.exchange(this.url.concat(url), HttpMethod.DELETE, (HttpEntity<?>) this.requestEntity, JsonNode.class);
    }

    protected Map<String, String> queryObjectToMap(QueryObject queryObject) {
        ObjectMapper mapper = new ObjectMapper();
        String queryString = null;
        try {
            queryString = mapper.writeValueAsString(queryObject)
                    .replaceAll("\\\\\\\\\"", "\"");
            Map<String, String> result = mapper.readValue(queryString, Map.class);
            if (queryObject.getSearchFields() == null || queryObject.getSearchFields().length == 0) {
                result.remove("searchFields");
            }
            if(result.containsKey("aqo")){
                result.remove("aqo");
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private <K, V> MultiValueMap<K, V> mapToMultiValueMap(Map<K, V> map) {
        MultiValueMap<K, V> linkedMultiValueMap = new LinkedMultiValueMap<>();

        map.keySet().forEach(key -> {
            linkedMultiValueMap.add(key, map.get(key));
        });
        return linkedMultiValueMap;

    }
}
