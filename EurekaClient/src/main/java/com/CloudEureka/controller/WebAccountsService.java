package com.CloudEureka.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WebAccountsService {

	@Autowired        // NO LONGER auto-created by Spring Cloud (see below)
    @LoadBalanced     // Explicitly request the load-balanced template // with Ribbon built-in
    protected RestTemplate restTemplate; 

    protected String serviceUrl = "http://CLIENT-SERVICE";
    
    public WebAccountsService(){}

    public WebAccountsService(String serviceUrl) {
        this.serviceUrl = serviceUrl.startsWith("http") ?
               serviceUrl : "http://" + serviceUrl;
    }

    public String getByNumber(String account) {
    	Map<String, String> map = new HashMap<>();
    	map.put("account", account);
    	
    	ResponseEntity<String> response = restTemplate.postForEntity(serviceUrl
                + "/test/testName", map, String.class, map);
    	
    	String tt = response.getBody();
    	
//        String tt = restTemplate.getForObject(serviceUrl
//                + "/test/testName", String.class, map);

        return tt;
    }
}
