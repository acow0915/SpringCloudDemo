package com.CloudEureka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/client2")
public class EurekaClient2Controller {

	@Autowired
    private WebAccountAgeService webAccountAgeService;
	
	@Autowired
    private DiscoveryClient client;
	
	@RequestMapping(value = "/getAge/{account}" ,method = RequestMethod.POST)
	public Integer getAge(@PathVariable("account") String inputAccount){
		ServiceInstance instance = client.getLocalServiceInstance();
		String data = "HOST : " + instance.getHost() + "," +
					  "PORT : " + instance.getPort() + "," +
				      "SERVICE_ID : " + instance.getServiceId();
		
		System.out.println(data);
		
		Integer age = webAccountAgeService.getByAccount(inputAccount);
		
		return age;
	}
	
}
