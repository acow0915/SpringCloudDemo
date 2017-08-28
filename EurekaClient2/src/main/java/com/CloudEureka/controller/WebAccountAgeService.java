package com.CloudEureka.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

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

    @HystrixCommand(fallbackMethod="getByAccountFallBack")
    public Integer getByAccount(String account) {
    	
    	double randomNum = Math.random() * 100d;
    	
    	if(randomNum > 50d){
    		throw new RuntimeException("故意出錯");
    	} else {
    		return map.get(account);
    	}
    }
    
    public Integer getByAccountFallBack(String account){
    	return -1;
    }
}
