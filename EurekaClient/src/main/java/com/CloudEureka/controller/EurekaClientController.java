package com.CloudEureka.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")
public class EurekaClientController {

	@Autowired
    private WebAccountsService webAccountsService;
	
	@Autowired
    private DiscoveryClient client;
	
	@RequestMapping(value = "/getName/{account}" ,method = RequestMethod.GET)
	public String clientName(@PathVariable("account") String inputAccount){
		ServiceInstance instance = client.getLocalServiceInstance();
		String data = "HOST : " + instance.getHost() + "," +
					  "PORT : " + instance.getPort() + "," +
				      "SERVICE_ID : " + instance.getServiceId();
		
		String account = webAccountsService.getByNumber(inputAccount);
		
		return data + "%%%" + account;
	}
	
	@RequestMapping(value = "/testName" ,method = RequestMethod.POST)
	public String testName(@RequestBody Map<String, String> map){
		return "HI " + map.get("account");
	}
	
}
