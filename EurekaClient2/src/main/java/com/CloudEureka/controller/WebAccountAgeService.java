package com.CloudEureka.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WebAccountAgeService {

	@Autowired        // NO LONGER auto-created by Spring Cloud (see below)
    @LoadBalanced     // Explicitly request the load-balanced template // with Ribbon built-in
    protected RestTemplate restTemplate; 

    protected String serviceUrl = "http://CLIENT-SERVICE";
    
    static Map<String, Integer> map = new HashMap<>();
    
    static {
    	map.put("Tim", 30);
    	map.put("Tim1", 31);
    	map.put("Tim2", 32);
    }
    
    public WebAccountAgeService(){}

    public WebAccountAgeService(String serviceUrl) {
        this.serviceUrl = serviceUrl.startsWith("http") ?
               serviceUrl : "http://" + serviceUrl;
    }

    public Integer getByAccount(String account) {
        return map.get(account);
    }
}
