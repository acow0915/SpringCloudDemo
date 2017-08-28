package com.CloudEureka.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/clent1")
public class EurekaClientController {

	/**使用http client 的請求方式*/
	@Autowired
    private ClientService clientService;
	
	@Autowired
    private DiscoveryClient client;
	
	/**使用 feign 的請求方式*/
	@Autowired
	private ClientFeignClient clientFeignClient;
	
	@RequestMapping(value = "/getUserData/{account}" ,method = RequestMethod.GET)
	public String clientName(@PathVariable("account") String inputAccount){
		ServiceInstance instance = client.getLocalServiceInstance();
		String data = "HOST : " + instance.getHost() + "," +
					  "PORT : " + instance.getPort() + "," +
				      "SERVICE_ID : " + instance.getServiceId();
		
		String address = clientService.getAddress(inputAccount);
//		Integer age = clientService.getAge(inputAccount);
		Integer age = clientFeignClient.getAge(inputAccount);
		
		return "HI : " + inputAccount + ", age : " + age + ", address  " + address + ", hostData : " + data;
	}
	
	@RequestMapping(value = "/testAddress" ,method = RequestMethod.POST)
	public String testAddress(@RequestBody Map<String, String> map){
		return "HI address is here!!!";
	}
	
}
