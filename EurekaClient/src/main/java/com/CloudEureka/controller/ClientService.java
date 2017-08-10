package com.CloudEureka.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ClientService {

	@Autowired        // NO LONGER auto-created by Spring Cloud (see below)
    @LoadBalanced     // Explicitly request the load-balanced template // with Ribbon built-in
    protected RestTemplate restTemplate; 

	protected String clientServiceUrl = "http://CLIENT-SERVICE";
	
    protected String client2ServiceUrl = "http://CLIENT2-SERVICE";
    
    public ClientService(){}

//    public Client2Service(String serviceUrl) {
//        this.serviceUrl = serviceUrl.startsWith("http") ?
//               serviceUrl : "http://" + serviceUrl;
//    }

    public Integer getAge(String account) {
    	Map<String, String> map = new HashMap<>();
    	map.put("account", account);
    	
    	ResponseEntity<Integer> response = restTemplate.postForEntity(client2ServiceUrl
                + "/client2/getAge/{account}", account, Integer.class, map);
    	
    	Integer age = response.getBody();
    	
        return age;
    }
    
    
    public String getAddress(String account) {
    	Map<String, String> map = new HashMap<>();
    	map.put("account", account);
    	
    	ResponseEntity<String> response = restTemplate.postForEntity(clientServiceUrl
                + "/clent1/testAddress", map, String.class, map);
    	
    	String name = response.getBody();
    	
        return name;
    }
}
